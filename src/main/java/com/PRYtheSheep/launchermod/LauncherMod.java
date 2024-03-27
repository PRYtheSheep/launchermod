package com.PRYtheSheep.launchermod;

import com.PRYtheSheep.launchermod.ModBlock.Launcher.Launcher;
import com.PRYtheSheep.launchermod.ModBlock.Launcher.LauncherBE;
import com.PRYtheSheep.launchermod.ModBlock.LauncherTurret.LauncherTurretBarrel;
import com.PRYtheSheep.launchermod.ModBlock.LauncherTurret.LauncherTurretBreach;
import com.PRYtheSheep.launchermod.ModBlock.LauncherTurret.LauncherTurretLauncher;
import com.PRYtheSheep.launchermod.ModItem.Projectile.Missile.MissileItem;
import com.PRYtheSheep.launchermod.ModItem.Projectile.Missile.MissileItemEntity;
import com.PRYtheSheep.launchermod.ModItem.Projectile.Shell.ShellItem;
import com.PRYtheSheep.launchermod.ModItem.Projectile.Shell.ShellItemEntity;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(LauncherMod.MODID)
public class LauncherMod
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "launchermod";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "launchermod" namespace
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    // Create a Deferred Register to hold Block Entities
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MODID);
    // Create a Deferred Register to hold Items which will all be registered under the "launchermod" namespace
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "launchermod" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    // Creates a new Block with the id "launchermod:example_block", combining the namespace and path
    public static final DeferredBlock<Block> EXAMPLE_BLOCK = BLOCKS.registerSimpleBlock("example_block", BlockBehaviour.Properties.of().mapColor(MapColor.STONE));
    // Create a Deferred Register to hold Item Entites
    public static final DeferredRegister<EntityType<?>> ITEM_ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);

    public static final DeferredBlock<Launcher> LAUNCHER = BLOCKS.register("launcher", ()->
            new Launcher(BlockBehaviour.Properties.of()
                    .destroyTime(2.0f)
                    .explosionResistance(10.0f)
                    .sound(SoundType.GRAVEL)
                    .lightLevel(state -> 7))
    );

    public static final Supplier<BlockEntityType<LauncherBE>> LAUNCHER_BE = BLOCK_ENTITIES.register("launcher",
            () -> BlockEntityType.Builder.of(LauncherBE::new, LAUNCHER.get()).build(null));

    public static final DeferredBlock<LauncherTurretBreach> LAUNCHER_TURRET_BREACH = BLOCKS.register("launcher_turret_breach", ()->
            new LauncherTurretBreach(BlockBehaviour.Properties.of()
                    .destroyTime(2.0f)
                    .explosionResistance(10.0f)
                    .sound(SoundType.GRAVEL)
                    .lightLevel(state -> 7))
    );
    public static final DeferredBlock<LauncherTurretLauncher> LAUNCHER_TURRET_LAUNCHER = BLOCKS.register("launcher_turret_launcher", ()->
            new LauncherTurretLauncher(BlockBehaviour.Properties.of()
                    .destroyTime(2.0f)
                    .explosionResistance(10.0f)
                    .sound(SoundType.GRAVEL)
                    .lightLevel(state -> 7))
    );
    public static final DeferredBlock<LauncherTurretBarrel> LAUNCHER_TURRET_BARREL = BLOCKS.register("launcher_turret_barrel", ()->
            new LauncherTurretBarrel(BlockBehaviour.Properties.of()
                    .destroyTime(2.0f)
                    .explosionResistance(10.0f)
                    .sound(SoundType.GRAVEL)
                    .lightLevel(state -> 7))
    );
    // Creates a new BlockItem with the id "launchermod:example_block", combining the namespace and path
    public static final DeferredItem<BlockItem> EXAMPLE_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("example_block", EXAMPLE_BLOCK);

    public static final DeferredItem<BlockItem> LAUNCHER_ITEM = ITEMS.registerSimpleBlockItem("launcher_item", LAUNCHER);

    // Creates a new food item with the id "launchermod:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat().nutrition(1).saturationMod(2f).build()));
    // Creates a new missile item with the id "launchermod:missile"
    public static final DeferredItem<Item> MISSILE_ITEM = ITEMS.registerItem("missile", MissileItem::new, new Item.Properties());
    // Register Missile Item entity here
    public static final Supplier<EntityType<MissileItemEntity>> MISSILE_ITEM_ENTITY = ITEM_ENTITIES.register("missile",
            () -> EntityType.Builder.of(MissileItemEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(1)
                    .build("missile"));

    // Register a new shell item with the id "launchermod:shell"
    public static final DeferredItem<Item> SHELL_ITEM = ITEMS.registerItem("shell", ShellItem::new, new Item.Properties());

    // Register Shell Item entity here
    public static final Supplier<EntityType<ShellItemEntity>> SHELL_ITEM_ENTITY = ITEM_ENTITIES.register("shell",
            () -> EntityType.Builder.of(ShellItemEntity::new, MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(1)
                    .build("shell"));

    // Creates a creative tab with the id "launchermod:example_tab" for the example item, that is placed after the combat tab
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.launchermod")) //The language key for the title of your CreativeModeTab
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EXAMPLE_ITEM.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
                output.accept(EXAMPLE_BLOCK_ITEM.get());
                output.accept(LAUNCHER_ITEM.get());
            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public LauncherMod(IEventBus modEventBus)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so Block Entities get registered
        BLOCK_ENTITIES.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);
        // Register the Deferred register to the mod event bus so Item Entities get registered
        ITEM_ENTITIES.register(modEventBus);


        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (launchermod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(EXAMPLE_BLOCK_ITEM);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
