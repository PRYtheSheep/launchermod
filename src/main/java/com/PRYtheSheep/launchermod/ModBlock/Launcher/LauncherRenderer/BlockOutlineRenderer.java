package com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherRenderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
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

    static double previousX;
    static double previousY;
    static double previousZ;
    public static ArrayList<Vec3> previousPos = new ArrayList<>();
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

        //Rendering the bounding box now
        PoseStack stack = event.getPoseStack();
        //RenderSystem.disableDepthTest();

        //Set up the predicate to get the closest player from 0,0,0 and within 128 blocks, return if no players
        Predicate<Entity> predicate = (i) -> (i instanceof Player);
        Player player1 = Minecraft.getInstance().level.getNearestPlayer(0, 0, 0, 256, predicate);
        if(player1==null) return;

        //Previous pos stores the previous posiiton of the player
        //When the world is first loaded, previous pos is empty, so use a try catch statement to get the previous pos
        //or enter in starting position of 0,0,0
        try{
            previousPos.get(0);
        }
        catch(Exception e){
            previousPos.add( previousPos.size(), new Vec3(0 ,0 ,0));
        }

        //Get the bounding box of the player
        //AABB aabb = player1.getBoundingBox().move(-player1.getX(), -player1.getY(), -player1.getZ());
        AABB aabb = new AABB(new BlockPos(
                Math.round((float)targetPos.x - 0.5F),
                Math.round((float)targetPos.y),
                Math.round((float)targetPos.z - 0.5F))
                ).move(-player1.getX(), -player1.getY(), -player1.getZ()).inflate(scale);

        //???
        RenderSystem.depthMask(true);
        VertexConsumer vertexConsumer = Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.lines());

        //Rendering logic below
        //I got to figure out this shit
        double px = Mth.lerp(event.getPartialTick(), previousPos.get(0).x, player1.getX());
        double py = Mth.lerp(event.getPartialTick(), previousPos.get(0).y, player1.getY());
        double pz = Mth.lerp(event.getPartialTick(), previousPos.get(0).z, player1.getZ());

        previousX = player1.getX();
        previousY = player1.getY();
        previousZ = player1.getZ();
        previousPos.set(0, new Vec3((float) previousX, (float) previousY, (float) previousZ));
        Vec3 camvec = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        double s0 = camvec.x;
        double s1 = camvec.y;
        double s2 = camvec.z;

        stack.pushPose();
        stack.translate(px - s0, py - s1, pz - s2);

        //Render the box
        LevelRenderer.renderLineBox(stack, vertexConsumer, aabb, 0, 1, 1, 1);

        stack.popPose();

        //RenderSystem.enableDepthTest();
    }
}