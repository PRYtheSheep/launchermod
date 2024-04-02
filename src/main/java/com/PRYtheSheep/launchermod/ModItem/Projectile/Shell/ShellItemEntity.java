package com.PRYtheSheep.launchermod.ModItem.Projectile.Shell;

import com.PRYtheSheep.launchermod.LauncherMod;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ShellItemEntity extends AbstractArrow{

    public ShellItemEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel){
        super(pEntityType, pLevel, new ItemStack(LauncherMod.SHELL_ITEM.asItem()));
    }

    protected ShellItemEntity(EntityType<? extends AbstractArrow> pEntityType, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pLevel, pPickupItemStack);
    }

    protected ShellItemEntity(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pX, pY, pZ, pLevel, pPickupItemStack);
    }

    protected ShellItemEntity(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pOwner, Level pLevel, ItemStack pPickupItemStack) {
        super(pEntityType, pOwner, pLevel, pPickupItemStack);
    }

    public boolean isFoil() {
        return false;
    }

    public float launchVelocityX;
    public float launchVelocityY;
    public float launchVelocityZ;
    int count = 0;

    @Override
    public void tick() {
        count++;
        if(this.level().isClientSide) return;
        super.tick();
        //TEST
        this.setDeltaMovement(new Vec3(launchVelocityX, launchVelocityY + count * -0.05F, launchVelocityZ));
        if(count>=200) this.kill();
        //END OF TEST
    }
}
