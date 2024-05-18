package com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherEventHandler;

import com.PRYtheSheep.launchermod.LauncherMod;
import com.PRYtheSheep.launchermod.Networking.Channel;
import com.PRYtheSheep.launchermod.Networking.TestEventHandlingC2S;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ViewportEvent;
import net.neoforged.neoforge.event.TickEvent;

import static com.PRYtheSheep.launchermod.ClientStartup.*;
import static com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherEventHandler.LauncherBE_EventHandler.parrot;

@Mod.EventBusSubscriber(modid = LauncherMod.MODID, value = Dist.CLIENT)
public class TestEventHandling {

    // Event is on the NeoForge event bus only on the physical client
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) { // Only call code once as the tick event is called twice every tick
            if(parrot != null){
                if (KEY_LEFT_MAPPING.get().isDown()) {
                    // Execute logic when the left key is pressed
                    isSpectating = true;
                    yaw--;
                }
                else if (KEY_RIGHT_MAPPING.get().isDown()) {
                    // Execute logic when the left key is pressed
                    isSpectating = true;
                    yaw++;
                }
                else if (KEY_UP_MAPPING.get().isDown()) {
                    // Execute logic when the left key is pressed
                    // Channel.sendToServer(new TestEventHandlingC2S(parrot.getUUID()));
                    isSpectating = true;
                    pitch--;
                }
                else if (KEY_DOWN_MAPPING.get().isDown()) {
                    // Execute logic when the left key is pressed
                    // Channel.sendToServer(new TestEventHandlingC2S(parrot.getUUID()));
                    isSpectating = true;
                    pitch++;
                }
            }
        }
    }

    private static boolean isSpectating = false;
    private static int pitch;
    private static int yaw;
    private static int roll;

    @SubscribeEvent
    public static void camera(ViewportEvent.ComputeCameraAngles event) {
        if(!isSpectating) return;
        event.setPitch(pitch);
        event.setYaw(yaw);
        event.setRoll(roll);
    }

}

