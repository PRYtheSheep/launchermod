package com.PRYtheSheep.launchermod.ModBlock.Launcher;

import com.PRYtheSheep.launchermod.LauncherMod;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.ServerChatEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mod.EventBusSubscriber(modid = LauncherMod.MODID)
public class LauncherBE_EventHandler {
    @SubscribeEvent
    public static void onChatMessage(ServerChatEvent event) {
        if(event.getPlayer().level().isClientSide) return;
        String text = event.getRawText();
        parseText(text, event.getPlayer().level(), event.getPlayer());
    }

    private static void parseText(String text, Level level, ServerPlayer player){
        //Text structure is
        //SET LAUNCHER @ x y z TO targetX targetY targetZ WITH ANGLE a
        String patternString = "^SET LAUNCHER @ (-?\\d+(\\.\\d+)?) (-?\\d+(\\.\\d+)?) (-?\\d+(\\.\\d+)?) TO (-?\\d+(\\.\\d+)?) (-?\\d+(\\.\\d+)?) (-?\\d+(\\.\\d+)?) WITH ANGLE (?:[0-9]|[1-8][0-9]|90)(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(text);
        if(matcher.matches()){
            //Text matches pattern
            String[] textBlocks = text.split(" ");
            BlockPos launcherBEpos = new BlockPos(
                    Integer.parseInt(textBlocks[3]),
                    Integer.parseInt(textBlocks[4]),
                    Integer.parseInt(textBlocks[5]));
            Vec3 targetPos = new Vec3(
                    Integer.parseInt(textBlocks[7])+0.5,
                    Integer.parseInt(textBlocks[8]),
                    Integer.parseInt(textBlocks[9])+0.5);
            BlockEntity be = level.getBlockEntity(launcherBEpos);

            //Check if be is launcherBE
            if(be instanceof LauncherBE){
                ((LauncherBE) be).targetPos = targetPos;
            }
            else{
                player.displayClientMessage(Component.literal("Invalid launcher coordinates"), true);
            }
        }
    }

}
