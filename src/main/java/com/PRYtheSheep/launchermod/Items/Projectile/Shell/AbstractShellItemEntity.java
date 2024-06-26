package com.PRYtheSheep.launchermod.Items.Projectile.Shell;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractShellItemEntity extends AbstractArrow {
    protected AbstractShellItemEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pLevel, pPickupItemStack);
    }

    protected AbstractShellItemEntity(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pX, pY, pZ, pLevel, pPickupItemStack);
    }

    protected AbstractShellItemEntity(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pOwner, pLevel, pPickupItemStack);
    }

    public Vec3 launchVelocity;

    public Vec3 getVelocity(Vec3 launchVelocity, int tick){
        return launchVelocity.add(0, tick * -0.05F, 0);
    }

}
