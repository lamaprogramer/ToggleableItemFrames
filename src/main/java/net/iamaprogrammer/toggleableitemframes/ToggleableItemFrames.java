package net.iamaprogrammer.toggleableitemframes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.iamaprogrammer.toggleableitemframes.networking.VersionIdentifier;
import net.iamaprogrammer.toggleableitemframes.networking.packets.ModVersionPacket;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToggleableItemFrames implements ModInitializer {
	public static final String MOD_ID = "toggleableitemframes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		PayloadTypeRegistry.playC2S().register(ModVersionPacket.PACKET_ID, ModVersionPacket.PACKET_CODEC);

		ServerPlayNetworking.registerGlobalReceiver(ModVersionPacket.PACKET_ID, (payload, context) ->
				context.responseSender().sendPacket(new ModVersionPacket(VersionIdentifier.MOD_VERSION)));
	}
}
