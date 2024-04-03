package com.PRYtheSheep.launchermod.Networking;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

import static com.PRYtheSheep.launchermod.LauncherMod.MODID;

public record LauncherCountPayloadS2C(int launcherCount, BlockPos pos, Vec3 targetPos, float elevation) implements CustomPacketPayload {

    public static final ResourceLocation ID = new ResourceLocation(MODID, "launchcount");

    public LauncherCountPayloadS2C(final FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readBlockPos(), buffer.readVec3(), buffer.readFloat());
    }

    @Override
    public void write(final FriendlyByteBuf buffer) {
        buffer.writeInt(launcherCount());
        buffer.writeBlockPos(pos());
        buffer.writeVec3(targetPos());
        buffer.writeFloat(elevation());
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

}
