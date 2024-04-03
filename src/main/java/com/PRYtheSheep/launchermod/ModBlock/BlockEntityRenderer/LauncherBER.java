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
import net.minecraft.world.phys.Vec3;
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

    private int currentYaw = -1;
    private int defaultYaw = 0;
    private int defaultPitch = 0;

    @Override
    public void render(LauncherBE pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        //TESTED ON 120 FPS
        BlockRenderDispatcher dispatcher = this.context.getBlockRenderDispatcher();
        Direction direction = pBlockEntity.getBlockState().getValue(FACING);
        BlockPos pos = pBlockEntity.getBlockPos();

        //Set up for launcher
        //Only  manipulate and render the launcher here within push/pop pose
        pPoseStack.pushPose();
        //Set up the position of launcher next
        switch(direction){
            case NORTH -> {
                pPoseStack.translate(0.5,0.625,1.375);
                defaultYaw = 0;
            }
            case EAST -> {
                pPoseStack.rotateAround(Axis.YP.rotationDegrees(270), 0,0,0);
                pPoseStack.translate(0.5,0.625,0.375);
                defaultYaw = 90;
            }
            case WEST -> {
                pPoseStack.rotateAround(Axis.YP.rotationDegrees(90), 0,0,0);
                pPoseStack.translate(-0.5,0.625,1.375);
                defaultYaw = -90;
            }
            case SOUTH -> {
                pPoseStack.rotateAround(Axis.YP.rotationDegrees(180), 0,0,0);
                pPoseStack.translate(-0.5,0.625,0.375);
                defaultYaw = -180;
            }
        }

        //TESTING

        //Initialise currentYaw to defaultYaw if it is -1 (initial value)
        if(currentYaw==-1) currentYaw = defaultYaw;

        //Set it so the block faces the targetPos
        //"Angle" is used by breach and barrel
        float angle = getAngle(pBlockEntity,currentYaw);
        pPoseStack.rotateAround(Axis.YP.rotationDegrees(angle),0,0,0);


        //TESTING

        //End of set up for launcher

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
        pPoseStack.rotateAround(Axis.YP.rotationDegrees(angle),0,0,0);
        pPoseStack.rotateAround(Axis.XP.rotationDegrees(pBlockEntity.elevation),0,0.75F,0.75F);
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
        pPoseStack.rotateAround(Axis.YP.rotationDegrees(angle),0,0,0);
        pPoseStack.rotateAround(Axis.XP.rotationDegrees(pBlockEntity.elevation),0,0.75F,0.75F);
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

    private Vec3 getVectorFromPitchYaw(float pitch, float yaw) {
        float f = (float) Math.cos(-yaw * 0.017453292F - (float)Math.PI);
        float f1 = (float) Math.sin(-yaw * 0.017453292F - (float)Math.PI);
        float f2 = (float) -Math.cos(-pitch * 0.017453292F);
        float f3 = (float) Math.sin(-pitch * 0.017453292F);
        return new Vec3(f1 * f2 * -1, f3, f * f2 * -1);
    }

    private float angleBetween2Vectors(Vec3 v1, Vec3 v2) {
        return (float) (Math.acos(v1.dot(v2) / (v1.length() * v2.length())) * (180 / Math.PI));
    }

    //Takes in a LauncherBE entity and the current yaw. Outputs a float array with 2 entries.
    //First entry is the angle between the currentYaw and targetPos, second entry is the y
    //component of the cross vector (to determine clockwise or anticlockwise rotation
    private float getAngle(LauncherBE pBlockEntity, int currentYaw){
        //Check if targetPos is null
        if(pBlockEntity.targetPos==null) return 0;

        Vec3 launcherPos = null;
        Direction direction = pBlockEntity.getBlockState().getValue(FACING);
        switch (direction) {
            case NORTH -> {
                launcherPos = pBlockEntity.getBlockPos().getCenter().add(0,0,1);
            }
            case EAST -> {
                launcherPos = pBlockEntity.getBlockPos().getCenter().add(-1,0,0);
            }
            case WEST -> {
                launcherPos = pBlockEntity.getBlockPos().getCenter().add(1,0,0);
            }
            case SOUTH -> {
                launcherPos = pBlockEntity.getBlockPos().getCenter().add(0,0,-1);
            }

        }
        Vec3 resultantVectorHorizontal = new Vec3(
                pBlockEntity.targetPos.x - launcherPos.x,
                0,
                pBlockEntity.targetPos.z - launcherPos.z);
        Vec3 xVector = new Vec3(0, 0, 1);
        float angle = (float) (Math.acos(resultantVectorHorizontal.dot(xVector) / (resultantVectorHorizontal.length() * xVector.length())) * (180 / Math.PI));
        if (resultantVectorHorizontal.x > 0) angle *= -1;
        angle += 180;
        Vec3 facingVector = getVectorFromPitchYaw(0, currentYaw);
        Vec3 angleVector = getVectorFromPitchYaw(0, angle);
        Vec3 crossVector = facingVector.cross(angleVector);
        if(crossVector.y > 0) return angleBetween2Vectors(facingVector, angleVector);
        else return -angleBetween2Vectors(facingVector, angleVector);
    }

}
