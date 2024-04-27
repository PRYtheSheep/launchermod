package com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherRenderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.codehaus.plexus.util.dag.Vertex;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.function.Predicate;

import static com.PRYtheSheep.launchermod.LauncherMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
public class FlightPathRenderer {

    public static ArrayList<Vec3> entityPos = new ArrayList<>();
    public static int size = 0;

    @SubscribeEvent
    public static void onWorldRenderLast(RenderLevelStageEvent event){

        //Set up
        if(event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) return;
        if(Minecraft.getInstance().player == null) return;
        //At least 2 positions in entityPos
        if(entityPos.size() < 1) return;

        //Rendering the bounding box now
        PoseStack stack = event.getPoseStack();
        //RenderSystem.disableDepthTest();

        //???
        RenderSystem.depthMask(true);
        VertexConsumer vertexConsumer = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.lines());

        //Rendering logic below
        //I got to figure out this shit
        Vec3 camvec = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        double s0 = camvec.x;
        double s1 = camvec.y;
        double s2 = camvec.z;

        size = entityPos.size();

        //Render the line(s)
        for(int i=0; i<=entityPos.size()-2; i++){
            Vec3 start = entityPos.get(i);
            Vec3 end = entityPos.get(i+1);
            var tesselator = Tesselator.getInstance();
            var buffer = tesselator.getBuilder();
            VertexBuffer vertexBuffer = new VertexBuffer(VertexBuffer.Usage.STATIC);
            buffer.begin(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.POSITION_COLOR);
            buffer.vertex(start.x, start.y, start.z).color(1f, 0, 1f, 1f).endVertex();
            buffer.vertex(end.x, end.y, end.z).color(1f, 0, 1f, 1f).endVertex();
            vertexBuffer.bind();
            vertexBuffer.upload(buffer.end());

            stack.pushPose();
            stack.translate( - s0,  - s1, - s2);
            var shader = GameRenderer.getPositionColorShader();
            vertexBuffer.drawWithShader(stack.last().pose(), event.getProjectionMatrix(), shader);
            VertexBuffer.unbind();
            stack.popPose();
        }
        //RenderSystem.enableDepthTest();
    }
}