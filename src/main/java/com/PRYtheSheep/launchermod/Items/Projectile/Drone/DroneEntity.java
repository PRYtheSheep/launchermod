package com.PRYtheSheep.launchermod.Items.Projectile.Drone;

import com.PRYtheSheep.launchermod.Blocks.Launcher.LauncherEventHandler.TestEventHandling;
import com.PRYtheSheep.launchermod.LauncherMod;
import com.sun.jna.platform.win32.OaIdl;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

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

    public boolean arrivedAtTargetPos = true;
    public Vec3 targetPos;
    public Vec3 cameraTargetPos;

    @Override
    public void tick() {
        super.tick();

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

        //Logic to set up the
        if(!this.level().isClientSide){
            if(cameraTargetPos == null){
                TestEventHandling.yaw = getYawFromVector(this.getDeltaMovement());
                TestEventHandling.pitch = 0;
            }
            else{
                //Make the camera point towards targetPos
                Vec3 resultant = cameraTargetPos.subtract(this.getEyePosition());
                TestEventHandling.yaw = getYawFromVector(resultant);
                TestEventHandling.pitch = getPitchFromVector(resultant);

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
