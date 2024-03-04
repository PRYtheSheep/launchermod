package com.PRYtheSheep.launchermod;

import com.PRYtheSheep.launchermod.ModBlock.BlockEntityRenderer.LauncherBER;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.PRYtheSheep.launchermod.LauncherMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientStartup {
    @SubscribeEvent
    public static void initClient(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(LauncherMod.LAUNCHER_BE.get(), LauncherBER::new);
    }
}
