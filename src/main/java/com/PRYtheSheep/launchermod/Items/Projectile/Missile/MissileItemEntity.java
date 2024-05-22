package com.PRYtheSheep.launchermod.Items.Projectile.Missile;

import com.PRYtheSheep.launchermod.LauncherMod;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class MissileItemEntity extends AbstractArrow {

    public MissileItemEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel){
        super(pEntityType, pLevel, new ItemStack(LauncherMod.MISSILE_ITEM.asItem()));
    }

    protected MissileItemEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pLevel, pPickupItemStack);
    }

    protected MissileItemEntity(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pX, pY, pZ, pLevel, pPickupItemStack);
    }

    protected MissileItemEntity(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pOwner, pLevel, pPickupItemStack);
    }

    public boolean isFoil() {
        return false;
    }

    private Vec3 vec3 = new Vec3(0.1,0,0);
    private int tick = 0;
    public float currentYaw = 0;

    @Override
    public void tick() {
        super.tick();
        //TEST
        if(tick == 0){
            this.setDeltaMovement(vec3);
            tick = 1;
        }

        currentYaw = getYawFromVector(this.getDeltaMovement());
        currentYaw -= 1;
        if(currentYaw>180) currentYaw -=360;
        if(currentYaw<-180) currentYaw +=360;
        Vec3 newDeltaMovement = getVectorFromPitchYaw(0, currentYaw);
        this.setDeltaMovement(newDeltaMovement.normalize().scale(0.3));
        //END OF TEST
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

    private Vec3 getVectorFromPitchYaw(float pitch, float yaw) {
        float f = (float) Math.cos(yaw * 0.017453292F - (float)Math.PI);
        float f1 = (float) Math.sin(yaw * 0.017453292F - (float)Math.PI);
        float f2 = (float) -Math.cos(-pitch * 0.017453292F);
        float f3 = (float) Math.sin(-pitch * 0.017453292F);
        return new Vec3(f1 * f2 * -1, f3, f * f2);
    }

}
