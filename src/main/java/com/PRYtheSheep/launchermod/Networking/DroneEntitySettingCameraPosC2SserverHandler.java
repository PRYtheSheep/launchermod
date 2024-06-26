package com.PRYtheSheep.launchermod.Networking;

import com.PRYtheSheep.launchermod.Items.Projectile.Drone.DroneEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class DroneEntitySettingCameraPosC2SserverHandler {

    private static final DroneEntitySettingCameraPosC2SserverHandler INSTANCE = new DroneEntitySettingCameraPosC2SserverHandler();

    public static DroneEntitySettingCameraPosC2SserverHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final DroneEntitySettingCameraPosC2S data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    DroneEntity drone = (DroneEntity) Minecraft.getInstance().getSingleplayerServer().overworld().getEntity(data.uuid());
                    drone.cameraTargetPos = data.vec3();
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable("launchermod.networking.failed", e.getMessage()));
                    return null;
                });

    }
}
