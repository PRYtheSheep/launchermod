package com.PRYtheSheep.launchermod;

import com.PRYtheSheep.launchermod.ModBlock.BlockEntityRenderer.LauncherBER;
import com.PRYtheSheep.launchermod.ModBlock.Renderer.ShellRenderer;
import com.PRYtheSheep.launchermod.ModItem.Projectile.Shell.ShellModel;
import com.PRYtheSheep.launchermod.Networking.LauncherPayloadS2CclientHandler;
import com.PRYtheSheep.launchermod.Networking.LauncherPayloadS2C;
import com.PRYtheSheep.launchermod.ModBlock.Renderer.MissileRenderer;
import com.PRYtheSheep.launchermod.ModItem.Projectile.Missile.MissileModel;
import com.PRYtheSheep.launchermod.Networking.TracerPayloadS2C;
import com.PRYtheSheep.launchermod.Networking.TracerS2CclientHandler;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.world.chunk.RegisterTicketControllersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;

import static com.PRYtheSheep.launchermod.LauncherMod.*;
import static com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherBE.LAUNCHER_TICKET_CONTROLLER;
import static com.PRYtheSheep.launchermod.ModItem.Projectile.Shell.ShellItemEntity.SHELL_TICKET_CONTROLLER;

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
    public static void registerPayload(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(MODID);
        registrar.play(LauncherPayloadS2C.ID, LauncherPayloadS2C::new, handler -> handler
                .server(LauncherPayloadS2CclientHandler.getInstance()::handleData));
        registrar.play(TracerPayloadS2C.ID, TracerPayloadS2C::new, handler -> handler
                .server(TracerS2CclientHandler.getInstance()::handleData));
    }

    @SubscribeEvent
    public static void registerChunkTicketController(RegisterTicketControllersEvent event){
        event.register(LAUNCHER_TICKET_CONTROLLER);
        event.register(SHELL_TICKET_CONTROLLER);
    }
}
