package com.PRYtheSheep.launchermod.Blocks.Launcher.LauncherEventHandler;

import com.PRYtheSheep.launchermod.Items.Projectile.Drone.DroneEntity;
import com.PRYtheSheep.launchermod.LauncherMod;
import com.PRYtheSheep.launchermod.Networking.Channel;
import com.PRYtheSheep.launchermod.Networking.DroneEntitySettingCameraPosC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ChatScreen;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.event.TickEvent;

import static com.PRYtheSheep.launchermod.ClientStartup.*;

@Mod.EventBusSubscriber(modid = LauncherMod.MODID, value = Dist.CLIENT)
public class DroneCameraEventHandler {

    public static DroneEntity droneEntity;

    public static boolean isSpectating = false;
    public static String launcherPos;
    public static float pitch;
    public static float yaw;

    public static float yawOld;
    public static float pitchOld;

    public static float roll;
    public static float addedYaw;
    public static float addedPitch;
    public static float addedRoll;

    // Event is on the NeoForge event bus only on the physical client
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) { // Only call code once as the tick event is called twice every tick
            if(droneEntity != null){
                if(KEY_LEFT_MAPPING.get().isDown()) {
                    // Execute logic when the left key is pressed
                    addedYaw--;
                }
                else if(KEY_RIGHT_MAPPING.get().isDown()) {
                    // Execute logic when the right key is pressed
                    addedYaw++;
                }
                else if(KEY_UP_MAPPING.get().isDown()) {
                    // Execute logic when the up key is pressed
                    addedPitch--;
                }
                else if(KEY_DOWN_MAPPING.get().isDown()) {
                    // Execute logic when the down key is pressed
                    addedPitch++;
                }
                else if(KEY_K_MAPPING.get().isDown()){
                    //Minecraft.getInstance().setScreen(new ChatScreen("testing"));
                    Vec3 vec3 = getVectorFromPitchYaw(pitch + addedPitch, yaw + addedYaw).scale(99999);
                    Vec3 vec31 = droneEntity.getPosition(Minecraft.getInstance().getPartialTick());
                    BlockHitResult result = Minecraft.getInstance().level.clip(
                            new ClipContext(
                                    vec31,
                                    vec3,
                                    ClipContext.Block.OUTLINE,
                                    ClipContext.Fluid.NONE,
                                    Minecraft.getInstance().player)
                    );
                    //send a packet from client to server to set the drone's cameraTargetPos
                    Channel.sendToServer(new DroneEntitySettingCameraPosC2S(
                            droneEntity.getUUID(),
                            result.getBlockPos().getCenter()
                    ));
                    addedYaw = 0;
                    addedPitch = 0;
                }
                else if(KEY_J_MAPPING.get().isDown()){
                    Vec3 vec3 = getVectorFromPitchYaw(pitch + addedPitch, yaw + addedYaw).scale(99999);
                    Vec3 vec31 = droneEntity.getPosition(Minecraft.getInstance().getPartialTick());
                    BlockHitResult result = Minecraft.getInstance().level.clip(
                            new ClipContext(
                                    vec31,
                                    vec3,
                                    ClipContext.Block.OUTLINE,
                                    ClipContext.Fluid.NONE,
                                    Minecraft.getInstance().player)
                    );
                    String targetPos =
                            String.valueOf(result.getBlockPos().getX()) + " " +
                            String.valueOf(result.getBlockPos().getY()) + " " + String.valueOf(result.getBlockPos().getZ());
                    String mes = "SET LAUNCHER AT " + launcherPos + " TO " + targetPos + " WITH ANGLE ";
                    Minecraft.getInstance().setScreen(new ChatScreen(mes));
                }
            }
        }
    }

    @SubscribeEvent
    public static void camera(ViewportEvent.ComputeCameraAngles event) {
        if(!isSpectating) return;

        event.setPitch(pitch + addedPitch);
        event.setYaw(yaw + addedYaw);
        event.setRoll(roll + addedRoll);
    }

    private static Vec3 getVectorFromPitchYaw(float pitch, float yaw) {
        float f = (float) Math.cos(yaw * 0.017453292F - (float)Math.PI);
        float f1 = (float) Math.sin(yaw * 0.017453292F - (float)Math.PI);
        float f2 = (float) -Math.cos(-pitch * 0.017453292F);
        float f3 = (float) Math.sin(-pitch * 0.017453292F);
        return new Vec3(f1 * f2 * -1, f3, f * f2);
    }

    private static float getYawFromVector(Vec3 vec3) {
        double dx = vec3.x;
        double dz = vec3.z;
        double yaw = 0;
        // Set yaw
        if (dx != 0) {
            // Set yaw start value based on dx
            if (dx < 0) {
                yaw = 1.5 * Math.PI;
            } else {
                yaw = 0.5 * Math.PI;
            }
            yaw -= Math.atan(dz / dx);
        } else if (dz < 0) {
            yaw = Math.PI;
        }
        yaw = (float) (-yaw * 180 / Math.PI - 90) + 90;
        if(yaw<-180) yaw += 360;
        return (float) yaw;
    }

    private static float getPitchFromVector(Vec3 vec3){
        double currentDeltaZ = vec3.z;
        double currentDeltaY = vec3.y;
        double currentDeltaX = vec3.x;

        double currentDistance = Math.sqrt(currentDeltaZ * currentDeltaZ + currentDeltaX * currentDeltaX);
        return (float) ((float) Math.atan2(currentDeltaY, currentDistance) * (180 / Math.PI)) * -1;
    }
    
}

