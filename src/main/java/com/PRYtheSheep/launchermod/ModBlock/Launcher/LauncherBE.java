package com.PRYtheSheep.launchermod.ModBlock.Launcher;

import com.PRYtheSheep.launchermod.ModItem.Projectile.MissileItemEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.PRYtheSheep.launchermod.LauncherMod.LAUNCHER_BE;
import static com.PRYtheSheep.launchermod.LauncherMod.MISSILE_ITEM_ENTITY;

public class LauncherBE extends BlockEntity {
    public LauncherBE(BlockPos pPos, BlockState pBlockState) {
        super(LAUNCHER_BE.get(), pPos, pBlockState);
    }

    int count = 0;
    public void tickServer() {
        if(this.level.isClientSide) return;
        //TEST
        count++;
        if(count%60==0){
            MissileItemEntity missileItemEntity = new MissileItemEntity(MISSILE_ITEM_ENTITY.get(), this.level);
            missileItemEntity.setPos(this.getBlockPos().getCenter().add(0,2,0));
            this.level.addFreshEntity(missileItemEntity);
        }
        //END OF TEST
    }
}
