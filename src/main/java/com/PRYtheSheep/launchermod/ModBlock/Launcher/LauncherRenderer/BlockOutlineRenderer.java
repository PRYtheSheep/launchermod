package com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherRenderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;


import java.util.ArrayList;
import java.util.function.Predicate;

import static com.PRYtheSheep.launchermod.LauncherMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class BlockOutlineRenderer {

    public static Vec3 targetPos = null;

    private static int renderCount = 0;
    private static double scale = 1;

    @SubscribeEvent
    public static void onWorldRenderLast(RenderLevelStageEvent event){

        //Set up
        if(event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) return;
        if(Minecraft.getInstance().player == null) return;
        if(targetPos == null) return;
        //Cycle the scale
        if(0 <= renderCount && renderCount <= 29){
            scale = 0.2;
            renderCount++;
        } else if (30 <= renderCount && renderCount <= 59) {
            scale = 0;
            renderCount++;
        } else if (60 <= renderCount && renderCount <= 88){
            scale = -0.2;
            renderCount++;
        } else {
            scale = -0.2;
            renderCount = 0;
        }

        //Prepare the stack
        PoseStack stack = event.getPoseStack();
        //RenderSystem.disableDepthTest();

        //???
        RenderSystem.depthMask(true);

        //Rendering logic below
        //I got to figure out this shit
        Vec3 camvec = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        double s0 = camvec.x;
        double s1 = camvec.y;
        double s2 = camvec.z;

        //Get the coordinates to render the lines
        Vec3[] coordinateList = getBoxCoordinates(targetPos);

        //Render the lines
        //Set up
        var tesselator = Tesselator.getInstance();
        var buffer = tesselator.getBuilder();
        VertexBuffer vertexBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
        buffer.begin(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.POSITION_COLOR);

        //TESTING
        Vec3 start = coordinateList[0];
        Vec3 end = coordinateList[1];
        buffer.vertex(start.x, start.y, start.z).color(0, 1f, 1f, 1f).endVertex();
        buffer.vertex(end.x, end.y, end.z).color(0, 1f, 1f, 1f).endVertex();
        vertexBuffer.bind();
        vertexBuffer.upload(buffer.end());
        //TESTING

        stack.pushPose();
        stack.translate( - s0,  - s1, - s2);
        var shader = GameRenderer.getPositionColorShader();
        vertexBuffer.drawWithShader(stack.last().pose(), event.getProjectionMatrix(), shader);
        VertexBuffer.unbind();
        stack.popPose();
    }

    private static Vec3[] getBoxCoordinates(Vec3 targetPos){
        targetPos.add(-0.5F, 0.5F, -0.5F);

        Vec3 point0 = new Vec3(targetPos.x, targetPos.y, targetPos.z);

        targetPos.add(1,0,0);
        Vec3 point1 = new Vec3(targetPos.x, targetPos.y, targetPos.z);

        targetPos.add(0,0,1);
        Vec3 point2 = new Vec3(targetPos.x, targetPos.y, targetPos.z);

        targetPos.add(-1,0,0);
        Vec3 point3 = new Vec3(targetPos.x, targetPos.y, targetPos.z);

        return new Vec3[]{
                point0,
                point1,
                point2,
                point3
        };
    }

}