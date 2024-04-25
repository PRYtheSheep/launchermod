package com.PRYtheSheep.launchermod.Networking;

import com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherBE;
import com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherRenderer.BlockOutlineRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class LauncherPayloadS2CclientHandler {

    private static final LauncherPayloadS2CclientHandler INSTANCE = new LauncherPayloadS2CclientHandler();

    public static LauncherPayloadS2CclientHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final LauncherPayloadS2C data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    BlockEntity be = Minecraft.getInstance().level.getBlockEntity(data.pos());
                    if(be instanceof LauncherBE){
                        ((LauncherBE) be).launchCount = data.launcherCount();
                        ((LauncherBE) be).targetPos = data.targetPos();
                        ((LauncherBE) be).elevation = data.elevation();
                        BlockOutlineRenderer.targetPos = data.targetPos();
                        BlockOutlineRenderer.currentPos = data.pos();
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable("launchermod.networking.failed", e.getMessage()));
                    return null;
                });
    }

}
