package com.PRYtheSheep.launchermod.ModBlock.Launcher;

import com.PRYtheSheep.launchermod.LauncherMod;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import static com.PRYtheSheep.launchermod.LauncherMod.LAUNCHER_BE;
import static com.PRYtheSheep.launchermod.ModBlock.Launcher.Launcher.PART;

public class LauncherBE extends BlockEntity {
    public LauncherBE(BlockPos pPos, BlockState pBlockState) {
        super(LAUNCHER_BE.get(), pPos, pBlockState);
    }

    public void tickServer() {
    }
}
