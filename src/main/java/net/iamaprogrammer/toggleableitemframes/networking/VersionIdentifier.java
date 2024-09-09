package net.iamaprogrammer.toggleableitemframes.networking;

import io.netty.buffer.ByteBuf;
import net.iamaprogrammer.toggleableitemframes.ToggleableItemFrames;
import net.iamaprogrammer.toggleableitemframes.networking.packets.ModVersionPacket;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

public class VersionIdentifier {
    public static final String MOD_VERSION = "5.1.0-1.20.6";
}
