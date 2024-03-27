package com.PRYtheSheep.launchermod.Networking;

import com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherBE;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class LauncherCountPayloadS2CclientHandler {

    private static final LauncherCountPayloadS2CclientHandler INSTANCE = new LauncherCountPayloadS2CclientHandler();

    public static LauncherCountPayloadS2CclientHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final LauncherCountPayloadS2C data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    BlockEntity be = Minecraft.getInstance().level.getBlockEntity(data.pos());
                    if(be instanceof LauncherBE){
                        ((LauncherBE) be).launchCount = data.launcherCount();
                        ((LauncherBE) be).targetPos = data.targetPos();
                    }
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable("my_mod.networking.failed", e.getMessage()));
                    return null;
                });
    }

}
