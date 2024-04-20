package com.PRYtheSheep.launchermod.Networking;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import static com.PRYtheSheep.launchermod.LauncherMod.MODID;

public record TracerPayloadS2C(Vec3 currentPos) implements CustomPacketPayload {

    public static final ResourceLocation ID = new ResourceLocation(MODID, "tracer");

    public TracerPayloadS2C(final FriendlyByteBuf buffer) {
        this(buffer.readVec3());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeVec3(currentPos());
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}
