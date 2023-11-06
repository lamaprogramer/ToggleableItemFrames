package net.iamaprogrammer.toggleableitemframes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.iamaprogrammer.toggleableitemframes.networking.VersionIdentifier;

public class ToggleableItemFrames implements ModInitializer {
	public static final String MOD_ID = "toggleableitemframes";

	@Override
	public void onInitialize() {
		ServerPlayNetworking.registerGlobalReceiver(VersionIdentifier.version_id, (server, player, handler, buf, responseSender) -> {
			responseSender.sendPacket(VersionIdentifier.version_id, PacketByteBufs.create().writeString(VersionIdentifier.MOD_VERSION));
		});
	}
}
