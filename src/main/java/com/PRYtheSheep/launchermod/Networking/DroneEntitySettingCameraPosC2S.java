package com.PRYtheSheep.launchermod.Networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

import static com.PRYtheSheep.launchermod.LauncherMod.MODID;

public record DroneEntitySettingCameraPosC2S(UUID uuid, Vec3 vec3) implements CustomPacketPayload {

    public static final ResourceLocation ID = new ResourceLocation(MODID, "spectate");

    public DroneEntitySettingCameraPosC2S(final FriendlyByteBuf buffer) {
        this(buffer.readUUID(), buffer.readVec3());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {

    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}
