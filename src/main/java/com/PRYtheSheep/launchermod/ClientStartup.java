package com.PRYtheSheep.launchermod;

import com.PRYtheSheep.launchermod.Blocks.Launcher.BlockEntityRenderer.LauncherBER;
import com.PRYtheSheep.launchermod.Items.Projectile.Drone.DroneModel;
import com.PRYtheSheep.launchermod.Items.Projectile.ProjectileRenderer.DroneRenderer;
import com.PRYtheSheep.launchermod.Items.Projectile.ProjectileRenderer.ShellRenderer;
import com.PRYtheSheep.launchermod.Items.Projectile.Shell.ShellModel;
import com.PRYtheSheep.launchermod.Networking.*;
import com.PRYtheSheep.launchermod.Items.Projectile.ProjectileRenderer.MissileRenderer;
import com.PRYtheSheep.launchermod.Items.Projectile.Missile.MissileModel;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.jarjar.nio.util.Lazy;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.world.chunk.RegisterTicketControllersEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlerEvent;
import net.neoforged.neoforge.network.registration.IPayloadRegistrar;
import org.lwjgl.glfw.GLFW;

import static com.PRYtheSheep.launchermod.Items.Projectile.Drone.DroneEntity.DRONE_TICKET_CONTROLLER;
import static com.PRYtheSheep.launchermod.LauncherMod.*;
import static com.PRYtheSheep.launchermod.Blocks.Launcher.LauncherBE.LAUNCHER_TICKET_CONTROLLER;
import static com.PRYtheSheep.launchermod.Items.Projectile.Shell.ShellItemEntity.SHELL_TICKET_CONTROLLER;

@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientStartup {

    @SubscribeEvent
    public static void doSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(MISSILE_ITEM_ENTITY.get(), MissileRenderer::new);
        EntityRenderers.register(SHELL_ITEM_ENTITY.get(), ShellRenderer::new);
        EntityRenderers.register(DRONE_ENTITY.get(), DroneRenderer::new);
    }

    @SubscribeEvent
    public static void initClient(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(LauncherMod.LAUNCHER_BE.get(), LauncherBER::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MissileModel.LAYER_LOCATION, MissileModel::createBodyLayer);
        event.registerLayerDefinition(ShellModel.LAYER_LOCATION, ShellModel::createBodyLayer);
        event.registerLayerDefinition(DroneModel.LAYER_LOCATION, DroneModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerPayload(final RegisterPayloadHandlerEvent event) {
        final IPayloadRegistrar registrar = event.registrar(MODID);
        registrar.play(LauncherPayloadS2C.ID, LauncherPayloadS2C::new, handler -> handler
                .client(LauncherPayloadS2CclientHandler.getInstance()::handleData));
        registrar.play(TracerPayloadS2C.ID, TracerPayloadS2C::new, handler -> handler
                .client(TracerS2CclientHandler.getInstance()::handleData));
        registrar.play(DroneEntitySettingCameraPosC2S.ID, DroneEntitySettingCameraPosC2S::new, handler -> handler
                .server(DroneEntitySettingCameraPosC2SserverHandler.getInstance()::handleData));
    }

    @SubscribeEvent
    public static void registerChunkTicketController(RegisterTicketControllersEvent event){
        event.register(LAUNCHER_TICKET_CONTROLLER);
        event.register(SHELL_TICKET_CONTROLLER);
        event.register(DRONE_TICKET_CONTROLLER);
    }

    // Key mapping is lazily initialized so it doesn't exist until it is registered
    public static final Lazy<KeyMapping> KEY_LEFT_MAPPING = Lazy.of(() ->
            new KeyMapping(
                    "key.launchermod.traverse_left", // Will be localized using this translation key
                    InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
                    GLFW.GLFW_KEY_LEFT,
                    "key.categories.launchermod.launchermod" // Mapping will be in the misc category
            )

    );
    public static final Lazy<KeyMapping> KEY_RIGHT_MAPPING = Lazy.of(() ->
            new KeyMapping(
                    "key.launchermod.traverse_right", // Will be localized using this translation key
                    InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
                    GLFW.GLFW_KEY_RIGHT,
                    "key.categories.launchermod.launchermod" // Mapping will be in the misc category
            )

    );
    public static final Lazy<KeyMapping> KEY_UP_MAPPING = Lazy.of(() ->
            new KeyMapping(
                    "key.launchermod.traverse_up", // Will be localized using this translation key
                    InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
                    GLFW.GLFW_KEY_UP,
                    "key.categories.launchermod.launchermod" // Mapping will be in the misc category
            )

    );
    public static final Lazy<KeyMapping> KEY_DOWN_MAPPING = Lazy.of(() ->
            new KeyMapping(
                    "key.launchermod.traverse_down", // Will be localized using this translation key
                    InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
                    GLFW.GLFW_KEY_DOWN,
                    "key.categories.launchermod.launchermod" // Mapping will be in the misc category
            )

    );

    public static final Lazy<KeyMapping> KEY_K_MAPPING = Lazy.of(() ->
            new KeyMapping(
                    "key.launchermod.lock_drone_camera", // Will be localized using this translation key
                    InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
                    GLFW.GLFW_KEY_K,
                    "key.categories.launchermod.launchermod" // Mapping will be in the misc category
            )

    );

    public static final Lazy<KeyMapping> KEY_J_MAPPING = Lazy.of(() ->
            new KeyMapping(
                    "key.launchermod.set_target_from_drone", // Will be localized using this translation key
                    InputConstants.Type.KEYSYM, // Default mapping is on the keyboard
                    GLFW.GLFW_KEY_J,
                    "key.categories.launchermod.launchermod" // Mapping will be in the misc category
            )

    );

    // Event is on the mod event bus only on the physical client
    @SubscribeEvent
    public static void registerBindings(RegisterKeyMappingsEvent event) {
        event.register(KEY_LEFT_MAPPING.get());
        event.register(KEY_RIGHT_MAPPING.get());
        event.register(KEY_UP_MAPPING.get());
        event.register(KEY_DOWN_MAPPING.get());
        event.register(KEY_K_MAPPING.get());
        event.register(KEY_J_MAPPING.get());
    }
}
