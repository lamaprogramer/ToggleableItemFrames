package net.iamaprogrammer.toggleableitemframes.networking.packets;

import io.netty.buffer.ByteBuf;
import net.iamaprogrammer.toggleableitemframes.ToggleableItemFrames;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record ModVersionPacket(String version) implements CustomPayload {
    public static final CustomPayload.Id<ModVersionPacket> PACKET_ID = new CustomPayload.Id<>(Identifier.of(
            ToggleableItemFrames.MOD_ID, ToggleableItemFrames.MOD_ID + "_version_identifier"));
    public static final PacketCodec<ByteBuf, ModVersionPacket> PACKET_CODEC = PacketCodecs.STRING.xmap(ModVersionPacket::new, ModVersionPacket::version).cast();

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}
