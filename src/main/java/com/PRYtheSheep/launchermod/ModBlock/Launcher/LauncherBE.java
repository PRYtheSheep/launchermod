package com.PRYtheSheep.launchermod.ModBlock.Launcher;

import com.PRYtheSheep.launchermod.ModItem.Projectile.Missile.MissileItemEntity;
import com.PRYtheSheep.launchermod.ModItem.Projectile.Shell.ShellItemEntity;
import com.PRYtheSheep.launchermod.Networking.Channel;
import com.PRYtheSheep.launchermod.Networking.LauncherCountPayloadS2C;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.PRYtheSheep.launchermod.LauncherMod.*;

public class LauncherBE extends BlockEntity {
    public LauncherBE(BlockPos pPos, BlockState pBlockState) {
        super(LAUNCHER_BE.get(), pPos, pBlockState);
    }

    //Need to make a custom packet to send to the client side
    public int launchCount = 0;
    int count = 0;

    public void tickServer() {
        if(this.level.isClientSide) return;
        //TEST
        count++;
        if(count%60==0){
            ShellItemEntity shellItemEntity = new ShellItemEntity(SHELL_ITEM_ENTITY.get(), this.level);
            shellItemEntity.setPos(this.getBlockPos().getCenter().add(0,2,0));
            shellItemEntity.setDeltaMovement(0,1,1);
            this.level.addFreshEntity(shellItemEntity);
        }
        if(count%40==0 && launchCount < 3){
            launchCount++;
            Channel.sendToServer(new LauncherCountPayloadS2C(launchCount, this.getBlockPos()));
        }
        //END OF TEST
    }
}
