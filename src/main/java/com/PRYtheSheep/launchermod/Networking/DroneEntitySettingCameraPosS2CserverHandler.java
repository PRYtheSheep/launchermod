package com.PRYtheSheep.launchermod.Networking;

import com.PRYtheSheep.launchermod.Blocks.Launcher.LauncherBE;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class DroneEntitySettingCameraPosS2CserverHandler {

    private static final DroneEntitySettingCameraPosS2CserverHandler INSTANCE = new DroneEntitySettingCameraPosS2CserverHandler();

    public static DroneEntitySettingCameraPosS2CserverHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final DroneEntitySettingCameraPosS2C data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    BlockEntity be = Minecraft.getInstance().level.getBlockEntity(data.pos());
                    if(be instanceof LauncherBE){
                        ((LauncherBE) be).droneEntity.cameraTargetPos = data.cameraTargetPos();
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable("launchermod.networking.failed", e.getMessage()));
                    return null;
                });

    }
}
