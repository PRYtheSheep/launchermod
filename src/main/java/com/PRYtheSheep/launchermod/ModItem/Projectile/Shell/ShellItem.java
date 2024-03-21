package com.PRYtheSheep.launchermod.ModItem.Projectile.Shell;

import com.PRYtheSheep.launchermod.LauncherMod;
import com.PRYtheSheep.launchermod.ModItem.Projectile.Missile.MissileItemEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.codehaus.plexus.util.cli.shell.Shell;

public class ShellItem extends ArrowItem {

    public ShellItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        return new ShellItemEntity(LauncherMod.SHELL_ITEM_ENTITY.get(), pShooter, pLevel, pStack);
    }

}
