package com.PRYtheSheep.launchermod.ModItem.Projectile.Missile;

import com.PRYtheSheep.launchermod.LauncherMod;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class MissileItem extends ArrowItem {
    public MissileItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        return new MissileItemEntity(LauncherMod.MISSILE_ITEM_ENTITY.get(), pShooter, pLevel, pStack);
    }
}
