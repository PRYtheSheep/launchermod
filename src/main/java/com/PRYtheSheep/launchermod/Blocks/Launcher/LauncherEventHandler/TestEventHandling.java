package com.PRYtheSheep.launchermod.Blocks.Launcher.LauncherEventHandler;

import com.PRYtheSheep.launchermod.Items.Projectile.Drone.DroneEntity;
import com.PRYtheSheep.launchermod.LauncherMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.event.TickEvent;

import static com.PRYtheSheep.launchermod.ClientStartup.*;

@Mod.EventBusSubscriber(modid = LauncherMod.MODID, value = Dist.CLIENT)
public class TestEventHandling {

    public static DroneEntity droneEntity;
    public static boolean isSpectating = false;
    public static float pitch;
    public static float yaw;
    public static float roll;
    private static float addedYaw;
    private static float addedPitch;
    private static float addedRoll;

    // Event is on the NeoForge event bus only on the physical client
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) { // Only call code once as the tick event is called twice every tick
            if(droneEntity != null){
                if (KEY_LEFT_MAPPING.get().isDown()) {
                    // Execute logic when the left key is pressed
                    addedYaw--;
                }
                else if (KEY_RIGHT_MAPPING.get().isDown()) {
                    // Execute logic when the right key is pressed
                    addedYaw++;
                }
                else if (KEY_UP_MAPPING.get().isDown()) {
                    // Execute logic when the up key is pressed
                    addedPitch--;
                }
                else if (KEY_DOWN_MAPPING.get().isDown()) {
                    // Execute logic when the down key is pressed
                    addedPitch++;
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

}

