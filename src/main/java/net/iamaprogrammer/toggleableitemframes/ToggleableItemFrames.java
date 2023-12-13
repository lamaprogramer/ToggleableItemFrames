package net.iamaprogrammer.toggleableitemframes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.iamaprogrammer.toggleableitemframes.networking.VersionIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToggleableItemFrames implements ModInitializer {
	public static final String MOD_ID = "toggleableitemframes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ServerPlayNetworking.registerGlobalReceiver(VersionIdentifier.version_id, (server, player, handler, buf, responseSender) ->
				responseSender.sendPacket(VersionIdentifier.version_id, PacketByteBufs.create().writeString(VersionIdentifier.MOD_VERSION)));
	}
}
