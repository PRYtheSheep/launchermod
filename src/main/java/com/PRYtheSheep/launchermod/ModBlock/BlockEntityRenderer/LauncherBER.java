package com.PRYtheSheep.launchermod.ModBlock.BlockEntityRenderer;

import com.PRYtheSheep.launchermod.LauncherMod;
import com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherBE;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class LauncherBER implements BlockEntityRenderer<LauncherBE> {

    BlockEntityRendererProvider.Context context;

    public LauncherBER(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    BlockState state = LauncherMod.LAUNCHER_TURRET_LAUNCHER.get().defaultBlockState();

    @Override
    public void render(LauncherBE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        pPoseStack.translate(0, 1, 0);
        this.context.getBlockRenderDispatcher().renderSingleBlock(state, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        pPoseStack.pushPose();
        pPoseStack.popPose();
    }
}
