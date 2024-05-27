package com.PRYtheSheep.launchermod.Networking;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import static com.PRYtheSheep.launchermod.LauncherMod.MODID;

public record DroneEntitySettingCameraPosS2C(BlockPos pos, Vec3 cameraTargetPos) implements CustomPacketPayload {

    public static final ResourceLocation ID = new ResourceLocation(MODID, "spectate");

    public DroneEntitySettingCameraPosS2C(final FriendlyByteBuf buffer) {
        this(buffer.readBlockPos(), buffer.readVec3());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeBlockPos(pos());
        buffer.writeVec3(cameraTargetPos());
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}
