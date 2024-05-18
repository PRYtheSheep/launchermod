package com.PRYtheSheep.launchermod.Networking;

import net.minecraft.network.chat.Component;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

public class TestEventHandlingC2SserverHandler {

    private static final TestEventHandlingC2SserverHandler INSTANCE = new TestEventHandlingC2SserverHandler();

    public static TestEventHandlingC2SserverHandler getInstance() {
        return INSTANCE;
    }

    public void handleData(final TestEventHandlingC2S data, final PlayPayloadContext context) {
        // Do something with the data, on the network thread

        // Do something with the data, on the main thread
        context.workHandler().submitAsync(() -> {
                    context.player().ifPresent(player -> {
//                        ServerLevel serverLevel = (ServerLevel) player.level();
//                        Parrot parrot = (Parrot) serverLevel.getEntities(player, new AABB(0,0,0,10,10,10)).get(0);
//                        parrot.setNoAi(true);
//                        //Rotate the mob about the x-axis
//                        parrot.setXRot(parrot.getXRot()+1);
//                        //Rotate the mob about the y-axis
//                        parrot.setYHeadRot(parrot.getYHeadRot()-1);
                    });
                })
                .exceptionally(e -> {
                    // Handle exception
                    context.packetHandler().disconnect(Component.translatable("launchermod.networking.failed", e.getMessage()));
                    return null;
                });

    }
}
