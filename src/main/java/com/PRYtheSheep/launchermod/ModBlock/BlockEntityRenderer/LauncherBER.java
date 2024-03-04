package com.PRYtheSheep.launchermod.ModBlock.BlockEntityRenderer;

import com.PRYtheSheep.launchermod.LauncherMod;
import com.PRYtheSheep.launchermod.ModBlock.Launcher.Launcher;
import com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherBE;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

import static com.PRYtheSheep.launchermod.ModBlock.Launcher.Launcher.FACING;

public class LauncherBER implements BlockEntityRenderer<LauncherBE> {

    BlockEntityRendererProvider.Context context;

    public LauncherBER(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(LauncherBE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        //Get the dispatcher
        BlockRenderDispatcher dispatcher = this.context.getBlockRenderDispatcher();


    }
}
