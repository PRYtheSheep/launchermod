package com.PRYtheSheep.launchermod;

import com.PRYtheSheep.launchermod.ModBlock.BlockEntityRenderer.LauncherBER;
import com.PRYtheSheep.launchermod.ModBlock.Renderer.ShellRenderer;
import com.PRYtheSheep.launchermod.ModItem.Projectile.Shell.ShellModel;
import com.PRYtheSheep.launchermod.Networking.LauncherCountPayloadS2CclientHandler;
import com.PRYtheSheep.launchermod.Networking.LauncherCountPayloadS2C;
import com.PRYtheSheep.launchermod.ModBlock.Renderer.MissileRenderer;
import com.PRYtheSheep.launchermod.ModItem.Projectile.Missile.MissileModel;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

import static com.PRYtheSheep.launchermod.LauncherMod.*;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientStartup {
    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(MISSILE_ITEM_ENTITY.get(), MissileRenderer::new);
        EntityRenderers.register(SHELL_ITEM_ENTITY.get(), ShellRenderer::new);
    }
    @SubscribeEvent
    public static void initClient(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(LauncherMod.LAUNCHER_BE.get(), LauncherBER::new);
    }
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MissileModel.LAYER_LOCATION, MissileModel::createBodyLayer);
        event.registerLayerDefinition(ShellModel.LAYER_LOCATION, ShellModel::createBodyLayer);
    }
    @SubscribeEvent
    public static void register(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(MODID);
        registrar.play(LauncherCountPayloadS2C.ID, LauncherCountPayloadS2C::new, handler -> handler
                .server(LauncherCountPayloadS2CclientHandler.getInstance()::handleData));
    }
}
