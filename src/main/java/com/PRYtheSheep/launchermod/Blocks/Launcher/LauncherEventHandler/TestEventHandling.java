package com.PRYtheSheep.launchermod.Blocks.Launcher.LauncherEventHandler;

import com.PRYtheSheep.launchermod.LauncherMod;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.event.TickEvent;

import static com.PRYtheSheep.launchermod.ClientStartup.*;
import static com.PRYtheSheep.launchermod.Blocks.Launcher.LauncherEventHandler.LauncherBE_EventHandler.arrow;

@Mod.EventBusSubscriber(modid = LauncherMod.MODID, value = Dist.CLIENT)
public class TestEventHandling {

    // Event is on the NeoForge event bus only on the physical client
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) { // Only call code once as the tick event is called twice every tick
            if(arrow != null){
                if (KEY_LEFT_MAPPING.get().isDown()) {
                    // Execute logic when the left key is pressed
                    yaw--;
                }
                else if (KEY_RIGHT_MAPPING.get().isDown()) {
                    // Execute logic when the right key is pressed
                    yaw++;
                }
                else if (KEY_UP_MAPPING.get().isDown()) {
                    // Execute logic when the up key is pressed
                    pitch--;
                }
                else if (KEY_DOWN_MAPPING.get().isDown()) {
                    // Execute logic when the down key is pressed
                    pitch++;
                }
            }
        }
    }

    public static boolean isSpectating = false;
    public static int pitch;
    public static int yaw;
    public static int roll;

    @SubscribeEvent
    public static void camera(ViewportEvent.ComputeCameraAngles event) {
        if(!isSpectating) return;
        event.setPitch(pitch);
        event.setYaw(yaw);
        event.setRoll(roll);
    }

}

