package com.PRYtheSheep.launchermod.ModBlock.Launcher;

import com.PRYtheSheep.launchermod.LauncherMod;
import com.PRYtheSheep.launchermod.ModItem.Projectile.Shell.ShellItemEntity;
import com.PRYtheSheep.launchermod.Networking.Channel;
import com.PRYtheSheep.launchermod.Networking.LauncherPayloadS2C;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.SectionPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.ticket.ChunkTicketManager;
import net.neoforged.neoforge.common.world.chunk.TicketController;

import static com.PRYtheSheep.launchermod.LauncherMod.*;
import static com.PRYtheSheep.launchermod.ModBlock.Launcher.Launcher.FACING;

public class LauncherBE extends BlockEntity{
    public LauncherBE(BlockPos pPos, BlockState pBlockState) {
        super(LAUNCHER_BE.get(), pPos, pBlockState);
    }

    public static final TicketController LAUNCHER_TICKET_CONTROLLER = new TicketController(new ResourceLocation(LauncherMod.MODID, "launcher"));

    //Need to make a custom packet to send to the client side
    public int launchCount = 0;
    public Vec3 targetPos = null;
    public float elevation = 0;
    int count = 0;

    public void tickServer() {
        if(this.level.isClientSide) return;
        count++;

        //TESTING TICKET CONTROLLER
        LAUNCHER_TICKET_CONTROLLER.forceChunk(
                (ServerLevel) this.level,
                this.getBlockPos(),
                SectionPos.blockToSectionCoord(
                        this.getBlockPos().getX()),
                SectionPos.blockToSectionCoord(
                        this.getBlockPos().getZ()),
                true,
                true);
        //END OF TESTING

        if(count%40==0 && targetPos!=null && count>20){

            //Increment launchCount to send to client side to play recoil animation
            launchCount++;

            //Create a shellItemEntity and set its position, velocity
            ShellItemEntity shellItemEntity = new ShellItemEntity(SHELL_ITEM_ENTITY.get(), this.level);
            Vec3 launchPos = getPositionForLaunch(this);
            shellItemEntity.setPos(launchPos);
            Vec3 v = getVelocityForLaunch(launchPos, targetPos, elevation);
            shellItemEntity.setDeltaMovement(v);
            shellItemEntity.launchVelocityX = (float) v.x;
            shellItemEntity.launchVelocityY = (float) v.y;
            shellItemEntity.launchVelocityZ = (float) v.z;

            //Add smoke particles at launchPos
            //Cast this.level to ServerLevel, should be safe as we return at the top if it is client side
            ServerLevel serverLevel = (ServerLevel) this.level;
            serverLevel.sendParticles(ParticleTypes.EXPLOSION, launchPos.x, launchPos.y, launchPos.z, 0,
                    launchPos.x, launchPos.y,launchPos.z, 0);

            //Add the shellItemEnitity to the level
            this.level.addFreshEntity(shellItemEntity);
        }

        if(count>=40){
            //Send a payload to the client side to update client side launchCount, targetPos and elevation
            Channel.sendToServer(new LauncherPayloadS2C(launchCount, this.getBlockPos(), targetPos, elevation));
        }
    }

    private Vec3 getPositionForLaunch(LauncherBE be) {

        Vec3 launcherPos = null;
        int currentYaw = 0;
        Direction direction = be.getBlockState().getValue(FACING);
        switch (direction) {
            case NORTH -> {
                currentYaw = 0;
                launcherPos = be.getBlockPos().getCenter().add(0,0,1);
            }
            case EAST -> {
                currentYaw = 90;
                launcherPos = be.getBlockPos().getCenter().add(-1,0,0);
            }
            case WEST -> {
                currentYaw = -90;
                launcherPos = be.getBlockPos().getCenter().add(1,0,0);
            }
            case SOUTH -> {
                currentYaw = -180;
                launcherPos = be.getBlockPos().getCenter().add(0,0,-1);
            }

        }
        Vec3 resultantVectorHorizontal = new Vec3(
                this.targetPos.x - launcherPos.x,
                0,
                this.targetPos.z - launcherPos.z);
        Vec3 xVector = new Vec3(0, 0, 1);
        float angle = (float) (Math.acos(resultantVectorHorizontal.dot(xVector) / (resultantVectorHorizontal.length() * xVector.length())) * (180 / Math.PI));
        if (resultantVectorHorizontal.x > 0) angle *= -1;
        angle += 180;

        Vec3 difference = getVectorFromPitchYaw(0, angle).scale(-.75).add(0,1.05,0);
        launcherPos = launcherPos.add(difference);
        return  launcherPos.add(getVectorFromPitchYaw(-elevation, angle).scale(5.5));
    }

    private Vec3 getVectorFromPitchYaw ( float pitch, float yaw){
        float f = (float) Math.cos(-yaw * 0.017453292F - (float) Math.PI);
        float f1 = (float) Math.sin(-yaw * 0.017453292F - (float) Math.PI);
        float f2 = (float) -Math.cos(-pitch * 0.017453292F);
        float f3 = (float) Math.sin(-pitch * 0.017453292F);
        return new Vec3(f1 * f2 * -1, f3, f * f2 * -1);
    }

    private float angleBetween2Vectors (Vec3 v1, Vec3 v2){
        return (float) (Math.acos(v1.dot(v2) / (v1.length() * v2.length())) * (180 / Math.PI));
    }

    private Vec3 getVelocityForLaunch(Vec3 launchPos, Vec3 targetPos, float elevation){
        //Calculate the horizontal resultant vector
        Vec3 resultantHorizontal = new Vec3(
                targetPos.x - launchPos.x,
                0,
                targetPos.z - launchPos.z
        );

        //Set up the variables and convert angle to radians
        float d = (float) resultantHorizontal.length();
        float h = (float) (launchPos.y - targetPos.y);
        float elevation_radian = (float) (elevation *  Math.PI/180);
        //Calculate the velocity needed
        float v = (float) Math.sqrt(
                ( 0.025 * (Math.pow(d,2) / Math.pow(Math.cos(elevation_radian), 2)) ) / ( d * Math.tan(elevation_radian) + h )
        );
        //Get the yaw from the resultantHorizontal
        Vec3 xVector = new Vec3(0, 0, 1);
        float angle = angleBetween2Vectors(resultantHorizontal, xVector);
        if (resultantHorizontal.x > 0) angle *= -1;
        angle += 180;

        Vec3 velocity = getVectorFromPitchYaw(-elevation, angle).normalize();
        return velocity.scale(v);
    }

}

