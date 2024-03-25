package com.PRYtheSheep.launchermod.ModBlock.Launcher;

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
        if(count%40==0){
            launchCount++;
            Channel.sendToServer(new LauncherCountPayloadS2C(launchCount, this.getBlockPos()));
//            ShellItemEntity shellItemEntity = new ShellItemEntity(SHELL_ITEM_ENTITY.get(), this.level);
//            shellItemEntity.setPos(this.getBlockPos().getCenter().add(0,1,0));
//            shellItemEntity.setDeltaMovement(0,0.822,0.822);
//            shellItemEntity.launchVelocityX = 0;
//            shellItemEntity.launchVelocityY = 0.822F;
//            shellItemEntity.launchVelocityZ = 0.822F;
//            this.level.addFreshEntity(shellItemEntity);
        }
        //END OF TEST
    }
}
