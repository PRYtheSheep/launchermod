package com.PRYtheSheep.launchermod.Blocks.Launcher.LauncherEventHandler;

import com.PRYtheSheep.launchermod.Items.Projectile.Drone.DroneEntity;
import com.PRYtheSheep.launchermod.LauncherMod;
import com.PRYtheSheep.launchermod.Blocks.Launcher.LauncherBE;
import com.PRYtheSheep.launchermod.Blocks.Launcher.LauncherPartIndex;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.ServerChatEvent;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.PRYtheSheep.launchermod.Blocks.Launcher.Launcher.PART;

@Mod.EventBusSubscriber(modid = LauncherMod.MODID)
public class LauncherBE_EventHandler {

    public static DroneEntity arrow = null;

    @SubscribeEvent
    public static void startSpectate(ServerChatEvent event) {
        if(event.getPlayer().level().isClientSide) return;
        if(event.getRawText().matches("start spectate")){
            ServerPlayer serverPlayer = event.getPlayer();
            List<Entity> entityList = serverPlayer.level().getEntities(serverPlayer, new AABB(0,0,0,10,10,10));
            arrow = (DroneEntity) entityList.get(0);
            serverPlayer.setCamera(entityList.get(0));
            //set is spectating to true
            TestEventHandling.isSpectating = true;
        }
    }

    @SubscribeEvent
    public static void endSpectate(ServerChatEvent event) {
        if(event.getPlayer().level().isClientSide) return;
        if(event.getRawText().matches("end spectate")){
            ServerPlayer serverPlayer = event.getPlayer();
            serverPlayer.setCamera(null);
            //set is spectating to false
            TestEventHandling.isSpectating = false;
        }
    }

    @SubscribeEvent
    public static void setDrone(ServerChatEvent event) {
        if(event.getPlayer().level().isClientSide) return;
        String text = event.getRawText();
        parseText2(text, event.getPlayer().level(), event.getPlayer());
    }

    @SubscribeEvent
    public static void setLauncher(ServerChatEvent event) {
        if(event.getPlayer().level().isClientSide) return;
        String text = event.getRawText();
        parseText(text, event.getPlayer().level(), event.getPlayer());
    }

    private static void parseText(String text, Level level, ServerPlayer player){
        String delimiter = " ";
        //Split the text by " ", then do a series of checks to determine the command
        //e.g. SET LAUNCHER AT 11 -60 4 TO 15 -61 24 WITH ANGLE 15
        String[] textBlocks = text.split(delimiter);

        if(textBlocks.length < 3) return;

        //Check if the first 2 blocks joins to SET LAUNCHER AT
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
        toCheck = textBlocks[7];
        patternString = "NULL";
        pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(toCheck);
        if(matcher.matches()){
            //Text block is NULL, set the target blockPos to null and return
            ((LauncherBE) be).targetPos = null;
            ((LauncherBE) be).elevation = 0;
            ((LauncherBE) be).canFire = true;
            player.displayClientMessage(Component.literal("Launcher target set to NULL"), true);
            return;
        }

        toCheck = textBlocks[7] + delimiter + textBlocks[8] + delimiter + textBlocks[9] + delimiter +
                textBlocks[10] + delimiter + textBlocks[11] + delimiter + textBlocks[12];
        patternString = "-?\\d+ -?\\d+ -?\\d+ WITH ANGLE (?:[0-9]|[1-8][0-9]|90)(\\\\.\\\\d+)?$";
        pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(toCheck);
        if(matcher.matches()){
            //Text blocks are valid coordinates and angle
            Vec3 targetPos = new Vec3(
                    Integer.parseInt(textBlocks[7])+0.5,
                    Integer.parseInt(textBlocks[8]),
                    Integer.parseInt(textBlocks[9])+0.5);
            ((LauncherBE) be).targetPos = targetPos;
            ((LauncherBE) be).elevation = Integer.parseInt(textBlocks[12]);
        }
        else{
            player.displayClientMessage(Component.literal("Invalid target coordinates or angle"), true);
        }
    }

    private static void parseText2(String text, Level level, ServerPlayer player){
        String delimiter = " ";
        //Split the text by " ", then do a series of checks to determine the command
        //e.g. SET LAUNCHER AT 11 -60 4 SPAWN DRONE

        String[] textBlocks = text.split(delimiter);

        if(textBlocks.length < 3) return;

        //Check if the first 2 blocks joins to SET LAUNCHER AT
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

        //Check if the next 2 blocks joins to SPAWN DRONE
        toCheck = textBlocks[6] + delimiter + textBlocks[7];
        patternString = "SPAWN DRONE";
        pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(toCheck);

        //Spawn a drone entity and return
        if(matcher.matches()){
            //Create a DroneEntity and spawn it 20 blocks above launcher
            DroneEntity droneEntity = new DroneEntity(LauncherMod.DRONE_ENTITY.get(), level);
            droneEntity.setPos(((LauncherBE) be).getBlockPos().getCenter().add(0, 20, 0));
            //Default spawn velocity is 1,0,0
            droneEntity.setDeltaMovement(1,0,0);
            //Set the DroneEntity to launcher
            ((LauncherBE) be).droneEntity = droneEntity;
            droneEntity.setNoPhysics(false);
            level.addFreshEntity(droneEntity);
            return;
        }

        //Check if the next 3 blocks joins to SET DRONE TO X Y Z
        toCheck = textBlocks[6] + delimiter
                + textBlocks[7] + delimiter
                + textBlocks[8] + delimiter
                + textBlocks[9] + delimiter
                + textBlocks[10] + delimiter
                + textBlocks[11];
        patternString = "SET DRONE TO -?\\d+ -?\\d+ -?\\d+";
        pattern = Pattern.compile(patternString);
        matcher = pattern.matcher(toCheck);

        //Return if it does not match
        if(!matcher.matches()) return;
        //Get the DroneEntity and set the arrivedAtTargetPos to false
        DroneEntity droneEntity = ((LauncherBE) be).droneEntity;
        droneEntity.arrivedAtTargetPos = false;
        //Convert the string into vec3
        Vec3 targetPos = new Vec3(
                Integer.parseInt(textBlocks[9])+0.5,
                Integer.parseInt(textBlocks[10]),
                Integer.parseInt(textBlocks[11])+0.5);
        droneEntity.targetPos = targetPos;
    }

}
