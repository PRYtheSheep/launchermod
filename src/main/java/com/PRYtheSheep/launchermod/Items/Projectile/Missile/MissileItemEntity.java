package com.PRYtheSheep.launchermod.Items.Projectile.Missile;

import com.PRYtheSheep.launchermod.LauncherMod;
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

    @Override
    public void tick() {
        super.tick();
        //TEST
        this.setDeltaMovement(new Vec3(0,1,0));
        if(this.position().y >= 0){
            this.kill();
        }
        //END OF TEST
    }
}
