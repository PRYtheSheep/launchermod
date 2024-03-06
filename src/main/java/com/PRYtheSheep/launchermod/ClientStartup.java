package com.PRYtheSheep.launchermod;

import com.PRYtheSheep.launchermod.ModBlock.BlockEntityRenderer.LauncherBER;
import com.PRYtheSheep.launchermod.ModBlock.Renderer.MissileRenderer;
import com.PRYtheSheep.launchermod.ModItem.Projectile.MissileModel;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.PRYtheSheep.launchermod.LauncherMod.MISSILE_ITEM_ENTITY;
import static com.PRYtheSheep.launchermod.LauncherMod.MODID;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientStartup {
    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(MISSILE_ITEM_ENTITY.get(), MissileRenderer::new);
    }
    @SubscribeEvent
    public static void initClient(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(LauncherMod.LAUNCHER_BE.get(), LauncherBER::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MissileModel.LAYER_LOCATION, MissileModel::createBodyLayer);
    }
}
