package com.PRYtheSheep.launchermod.Items.Projectile.Drone;

import com.PRYtheSheep.launchermod.Blocks.Launcher.LauncherEventHandler.DroneCameraEventHandler;
import com.PRYtheSheep.launchermod.LauncherMod;
import net.minecraft.client.Minecraft;
import net.minecraft.core.SectionPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.world.chunk.TicketController;

public class DroneEntity extends AbstractArrow {

    public DroneEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel){
        super(pEntityType, pLevel, new ItemStack(LauncherMod.SHELL_ITEM.asItem()));
    }

    protected DroneEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pLevel, pPickupItemStack);
    }

    protected DroneEntity(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pX, pY, pZ, pLevel, pPickupItemStack);
    }

    protected DroneEntity(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pOwner, pLevel, pPickupItemStack);
    }

    public static final TicketController DRONE_TICKET_CONTROLLER = new TicketController(new ResourceLocation(LauncherMod.MODID, "drone"));
    public boolean arrivedAtTargetPos = true;
    public Vec3 targetPos;
    public Vec3 cameraTargetPos;

    @Override
    public void tick() {
        super.tick();

        //TESTING TICKET CONTROLLER
        if(!this.level().isClientSide){
            DRONE_TICKET_CONTROLLER.forceChunk(
                    (ServerLevel) this.level(),
                    this.getOnPos(),
                    SectionPos.blockToSectionCoord(
                            this.getOnPos().getX()),
                    SectionPos.blockToSectionCoord(
                            this.getOnPos().getZ()),
                    true,
                    true);
        }
        //END OF TESTING

        //Main logic
        if(arrivedAtTargetPos){
            flyCircle();
        }
        else{
            //Calculate distance from drone to targetPos
            //Drone is considered to be at targetPos if distance is less than 0.5 blocks
            double distance = targetPos.distanceTo(this.getEyePosition());
            if(distance < 0.5){
                arrivedAtTargetPos = true;
            }
            else{
                //Drone not at targetPos, fly towards it
                flyTowardsTarget();
            }
        }

        //Logic to set up the camera
        if(!this.level().isClientSide){
            if(cameraTargetPos == null){
                DroneCameraEventHandler.yaw = getYawFromVector(this.getDeltaMovement());
                DroneCameraEventHandler.yawOld = DroneCameraEventHandler.yaw;
                DroneCameraEventHandler.pitch = 0;
            }
            else{
                //Make the camera point towards cameraTargetPos
                Vec3 resultant = cameraTargetPos.subtract(this.getEyePosition());
                DroneCameraEventHandler.yaw = getYawFromVector(resultant);
                DroneCameraEventHandler.pitch = getPitchFromVector(resultant);

            }
        }
    }

    public boolean isFoil() {
        return false;
    }

    private float getYawFromVector(Vec3 vec3) {
        double dx = vec3.x;
        double dz = vec3.z;
        double yaw = 0;
        // Set yaw
        if (dx != 0) {
            // Set yaw start value based on dx
            if (dx < 0) {
                yaw = 1.5 * Math.PI;
            } else {
                yaw = 0.5 * Math.PI;
            }
            yaw -= Math.atan(dz / dx);
        } else if (dz < 0) {
            yaw = Math.PI;
        }
        yaw = (float) (-yaw * 180 / Math.PI - 90) + 90;
        if(yaw<-180) yaw += 360;
        return (float) yaw;
    }

    private float getPitchFromVector(Vec3 vec3){
        double currentDeltaZ = vec3.z;
        double currentDeltaY = vec3.y;
        double currentDeltaX = vec3.x;

        double currentDistance = Math.sqrt(currentDeltaZ * currentDeltaZ + currentDeltaX * currentDeltaX);
        return (float) ((float) Math.atan2(currentDeltaY, currentDistance) * (180 / Math.PI)) * -1;
    }

    private Vec3 getVectorFromPitchYaw(float pitch, float yaw) {
        float f = (float) Math.cos(yaw * 0.017453292F - (float)Math.PI);
        float f1 = (float) Math.sin(yaw * 0.017453292F - (float)Math.PI);
        float f2 = (float) -Math.cos(-pitch * 0.017453292F);
        float f3 = (float) Math.sin(-pitch * 0.017453292F);
        return new Vec3(f1 * f2 * -1, f3, f * f2);
    }

    private void flyCircle(){
        float currentYaw = getYawFromVector(this.getDeltaMovement());
        currentYaw -= 1;
        if(currentYaw>180) currentYaw -=360;
        if(currentYaw<-180) currentYaw +=360;
        Vec3 newDeltaMovement = getVectorFromPitchYaw(0, currentYaw);
        this.setDeltaMovement(newDeltaMovement.normalize().scale(0.45));
    }

    private void flyTowardsTarget(){
        Vec3 resultantVector = new Vec3((targetPos.x - this.getX()),
                (targetPos.y - this.getY()),
                (targetPos.z - this.getZ()));

        this.setDeltaMovement(resultantVector.normalize());
    }

}
