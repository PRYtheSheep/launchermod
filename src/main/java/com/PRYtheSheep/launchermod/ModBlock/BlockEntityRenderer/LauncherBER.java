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
import net.neoforged.fml.ISystemReportExtender;

import static com.PRYtheSheep.launchermod.ModBlock.Launcher.Launcher.FACING;

public class LauncherBER implements BlockEntityRenderer<LauncherBE> {

    BlockEntityRendererProvider.Context context;
    private double displacement = 0;
    private int flag = 0;

    private int renderLaunchCount = 0;


    public LauncherBER(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    private final BlockState launcher = LauncherMod.LAUNCHER_TURRET_LAUNCHER.get().defaultBlockState();
    private final BlockState breach = LauncherMod.LAUNCHER_TURRET_BREACH.get().defaultBlockState();
    private final BlockState barrel = LauncherMod.LAUNCHER_TURRET_BARREL.get().defaultBlockState();

    @Override
    public void render(LauncherBE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        //TESTED ON 120 FPS
        BlockRenderDispatcher dispatcher = this.context.getBlockRenderDispatcher();
        Direction direction = pBlockEntity.getBlockState().getValue(FACING);
        BlockPos pos = pBlockEntity.getBlockPos();

        //Set up for launcher
        //Only  manipulate and render the launcher here within push/pop pose
        pPoseStack.pushPose();
        switch(direction){
            case EAST -> pPoseStack.rotateAround(Axis.YP.rotationDegrees(270), 0,0,0);
            case WEST -> pPoseStack.rotateAround(Axis.YP.rotationDegrees(90), 0,0,0);
            case SOUTH -> pPoseStack.rotateAround(Axis.YP.rotationDegrees(180), 0,0,0);
        }

        //Set up the position of launcher next
        switch(direction){
            case NORTH -> pPoseStack.translate(0.5,0.625,1.375);
            case EAST -> pPoseStack.translate(0.5,0.625,0.375);
            case WEST -> pPoseStack.translate(-0.5,0.625,1.375);
            case SOUTH -> pPoseStack.translate(-0.5,0.625,0.375);
        }

        //TESTING
        pPoseStack.rotateAround(Axis.YP.rotationDegrees(27),0,0,0);
        //TESTING

        dispatcher.renderSingleBlock(launcher, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        pPoseStack.popPose();

        //Set up for breach
        //Only  manipulate and render the breach here within push/pop pose
        pPoseStack.pushPose();
        switch(direction){
            case EAST -> pPoseStack.rotateAround(Axis.YP.rotationDegrees(270), 0,0,0);
            case WEST -> pPoseStack.rotateAround(Axis.YP.rotationDegrees(90), 0,0,0);
            case SOUTH -> pPoseStack.rotateAround(Axis.YP.rotationDegrees(180), 0,0,0);
        }

        //Set up the position of breach next
        switch(direction){
            case NORTH -> pPoseStack.translate(0.5,0.625,1.375);
            case EAST -> pPoseStack.translate(0.5,0.625,0.375);
            case WEST -> pPoseStack.translate(-0.5,0.625,1.375);
            case SOUTH -> pPoseStack.translate(-0.5,0.625,0.375);
        }

        //TESTING
        pPoseStack.rotateAround(Axis.YP.rotationDegrees(27),0,0,0);
        pPoseStack.rotateAround(Axis.XP.rotationDegrees(27),0,0.75F,0.75F);
        //TESTING

        dispatcher.renderSingleBlock(breach, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        pPoseStack.popPose();

        //Set up for barrel
        //Only  manipulate and render the barrel here within push/pop pose
        pPoseStack.pushPose();
        switch(direction){
            case EAST -> pPoseStack.rotateAround(Axis.YP.rotationDegrees(270), 0,0,0);
            case WEST -> pPoseStack.rotateAround(Axis.YP.rotationDegrees(90), 0,0,0);
            case SOUTH -> pPoseStack.rotateAround(Axis.YP.rotationDegrees(180), 0,0,0);
        }

        //Set up the position of barrel next
        switch(direction){
            case NORTH -> pPoseStack.translate(0.5,0.625,1.375);
            case EAST -> pPoseStack.translate(0.5,0.625,0.375);
            case WEST -> pPoseStack.translate(-0.5,0.625,1.375);
            case SOUTH -> pPoseStack.translate(-0.5,0.625,0.375);
        }

        //TESTING
        pPoseStack.rotateAround(Axis.YP.rotationDegrees(27),0,0,0);
        pPoseStack.rotateAround(Axis.XP.rotationDegrees(27),0,0.75F,0.75F);
        if(flag==0 && renderLaunchCount< pBlockEntity.launchCount){
            displacement = displacement + 0.1;
            pPoseStack.translate(0,0,displacement);
        }
        else if(flag==1){
            displacement = displacement - 0.02;
            pPoseStack.translate(0,0,displacement);
        }
        if(displacement>=0.9 && flag==0) flag = 1;
        if(displacement<=0 && flag==1){
            flag = 0;
            renderLaunchCount++;
        }
        //TESTING

        dispatcher.renderSingleBlock(barrel, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        pPoseStack.popPose();
    }
}
