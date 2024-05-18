package com.PRYtheSheep.launchermod.Networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

import static com.PRYtheSheep.launchermod.LauncherMod.MODID;

public record TestEventHandlingC2S(UUID uuid) implements CustomPacketPayload {

    public static final ResourceLocation ID = new ResourceLocation(MODID, "spectate");

    public TestEventHandlingC2S(final FriendlyByteBuf buffer) {
        this(buffer.readUUID());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeUUID(uuid());
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}
