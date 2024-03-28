package com.PRYtheSheep.launchermod.ModBlock.Launcher;

import com.PRYtheSheep.launchermod.ModItem.Projectile.Shell.ShellItemEntity;
import com.PRYtheSheep.launchermod.Networking.Channel;
import com.PRYtheSheep.launchermod.Networking.LauncherCountPayloadS2C;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.function.Predicate;

import static com.PRYtheSheep.launchermod.LauncherMod.*;
import static com.PRYtheSheep.launchermod.ModBlock.Launcher.Launcher.FACING;

public class LauncherBE extends BlockEntity {
    public LauncherBE(BlockPos pPos, BlockState pBlockState) {
        super(LAUNCHER_BE.get(), pPos, pBlockState);
    }

    //Need to make a custom packet to send to the client side
    public int launchCount = 0;
    public Vec3 targetPos = null;
    public float elevation = 10;
    int count = 0;

    public void tickServer() {
        if(this.level.isClientSide) return;
        //TEST
        count++;
        if(count%40==0 && targetPos!=null && count>20){
            launchCount++;
            ShellItemEntity shellItemEntity = new ShellItemEntity(SHELL_ITEM_ENTITY.get(), this.level);
            shellItemEntity.setPos(getPositionForLaunch(this));
            this.level.addFreshEntity(shellItemEntity);
        }

        Predicate<Entity> predicate = (i) -> (i instanceof Player);
        Player player1 = this.level.getNearestPlayer(0, 0, 0, 128, predicate);
        if(player1!=null) targetPos = player1.getEyePosition();

        if(count>=40){
            Channel.sendToServer(new LauncherCountPayloadS2C(launchCount, this.getBlockPos(), targetPos));
        }
        //END OF TEST
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
}

