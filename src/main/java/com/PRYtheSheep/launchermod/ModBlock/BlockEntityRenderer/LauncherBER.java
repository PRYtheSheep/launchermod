package com.PRYtheSheep.launchermod.ModBlock.BlockEntityRenderer;

import com.PRYtheSheep.launchermod.LauncherMod;
import com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherBE;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

import static com.PRYtheSheep.launchermod.ModBlock.Launcher.Launcher.FACING;

public class LauncherBER implements BlockEntityRenderer<LauncherBE> {

    BlockEntityRendererProvider.Context context;

    public LauncherBER(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    private final BlockState launcher = LauncherMod.LAUNCHER_TURRET_LAUNCHER.get().defaultBlockState();
    private final BlockState breach = LauncherMod.LAUNCHER_TURRET_BREACH.get().defaultBlockState();
    private final BlockState barrel = LauncherMod.LAUNCHER_TURRET_BARREL.get().defaultBlockState();

    @Override
    public void render(LauncherBE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        BlockRenderDispatcher dispatcher = this.context.getBlockRenderDispatcher();
        Direction direction = pBlockEntity.getBlockState().getValue(FACING);
        BlockPos pos = pBlockEntity.getBlockPos();
        PoseStack launcherStack = pPoseStack;
        PoseStack barrelStack = pPoseStack;

        //Set up the rotation of launcher_turret_launcher first
        launcherStack.pushPose();
        switch(direction){
            case EAST -> launcherStack.rotateAround(Axis.YP.rotationDegrees(270), 0,0,0);
            case WEST -> launcherStack.rotateAround(Axis.YP.rotationDegrees(90), 0,0,0);
            case SOUTH -> launcherStack.rotateAround(Axis.YP.rotationDegrees(180), 0,0,0);
        }
        launcherStack.popPose();

        //Set up the position of launcher_turret_launcher next
        launcherStack.pushPose();
        switch(direction){
            case NORTH -> launcherStack.translate(0.5,0.625,1.375);
        }
        dispatcher.renderSingleBlock(launcher, launcherStack, pBuffer, pPackedLight, pPackedOverlay);
        launcherStack.popPose();

        //TESTING
        barrelStack.pushPose();
        barrelStack.rotateAround(Axis.YP.rotationDegrees(45), 0,0,0);
        dispatcher.renderSingleBlock(barrel, barrelStack, pBuffer, pPackedLight, pPackedOverlay);
        barrelStack.popPose();
    }
}
