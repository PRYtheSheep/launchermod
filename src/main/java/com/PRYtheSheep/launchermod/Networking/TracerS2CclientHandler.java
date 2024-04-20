package com.PRYtheSheep.launchermod.Networking;

import com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherBE;
import com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherRenderer.BlockOutlineRenderer;
import com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherRenderer.FlightPathRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class TracerS2CclientHandler {

    private static final TracerS2CclientHandler INSTANCE = new TracerS2CclientHandler();

    public static TracerS2CclientHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final TracerPayloadS2C data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
            FlightPathRenderer.entityPos.add(data.currentPos());
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable("launchermod.networking.failed", e.getMessage()));
                    return null;
                });
    }

}
