package com.PRYtheSheep.launchermod.ModItem.Projectile.Shell;

import com.PRYtheSheep.launchermod.LauncherMod;
import com.PRYtheSheep.launchermod.Networking.Channel;
import com.PRYtheSheep.launchermod.Networking.LauncherPayloadS2C;
import com.PRYtheSheep.launchermod.Networking.TracerPayloadS2C;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
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

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        if(this.level().isClientSide) return;

        //Explode if it hits an entity
        this.level().explode(
                null,
                this.getX(),
                this.getY(),
                this.getZ(),
                3F,
                Level.ExplosionInteraction.TNT
        );
        this.kill();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if(this.level().isClientSide) return;

        //Explode if it hits a block
        this.level().explode(
                null,
                this.getX(),
                this.getY(),
                this.getZ(),
                3F,
                Level.ExplosionInteraction.TNT
        );
        this.kill();
    }

    public boolean isFoil() {
        return false;
    }

    public float launchVelocityX;
    public float launchVelocityY;
    public float launchVelocityZ;
    int tick = 0;

    @Override
    public void tick() {
        tick++;
        if(this.level().isClientSide) return;
        super.tick();

        this.setDeltaMovement(new Vec3(launchVelocityX, launchVelocityY + tick * -0.05F, launchVelocityZ));
        if(tick>=200) {
            this.kill();
            return;
        }

        //TESTING
        if(tick > 0){
            //Send a payload to the client side
            Channel.sendToServer(new TracerPayloadS2C(new Vec3(this.getX(), this.getY(), this.getZ())));
        }
        //END OF TESTING
    }
}
