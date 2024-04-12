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

import static com.PRYtheSheep.launchermod.ModBlock.Launcher.Launcher.PART;

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
                ((LauncherBE) be).elevation = Integer.parseInt(textBlocks[12]);
            }
            else{
                player.displayClientMessage(Component.literal("Invalid launcher coordinates"), true);
            }
        }
    }

    private static void parseText2(String text, Level level, ServerPlayer player){
        String delimiter = " ";
        //Split the text by " ", then do a series of checks to determine the command
        String[] textBlocks = text.split(delimiter);

        //Check if the first 2 blocks joins to SET LAUNCHER @
        String toCheck = textBlocks[0] + delimiter + textBlocks[1] + delimiter + textBlocks[2];
        String patternString = "SET LAUNCHER AT";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(toCheck);
        //Return if it does not match
        if(!matcher.matches()) return;

        //Check if the next 3 blocks are integers
        toCheck = textBlocks[3] + delimiter + textBlocks[4] + delimiter + textBlocks[5];
        patternString = "-?\\d+ -?\\d+ -?\\d+";
        pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(toCheck);
        //Return if it does not match
        if(!matcher.matches()) return;

        //Get the block entity at the coordinate
        BlockPos blockPos = new BlockPos(
                Integer.parseInt(textBlocks[3]),
                Integer.parseInt(textBlocks[4]),
                Integer.parseInt(textBlocks[5]));
        BlockEntity be = level.getBlockEntity(blockPos);

        //Return error message if the block entity is not a LauncherBE and not LauncherPartIndex.P14
        if(!(be instanceof LauncherBE) && be.getBlockState().getValue(PART) != LauncherPartIndex.P14){
            player.displayClientMessage(Component.literal("Invalid launcher coordinates"), true);
        }

        //Check if the next block is TO
        toCheck = textBlocks[6];
        patternString = "TO";
        pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(toCheck);
        //Return if it does not match
        if(!matcher.matches()) return;

        //Check if the next block(s) are coordinates or NULL

    }

}
