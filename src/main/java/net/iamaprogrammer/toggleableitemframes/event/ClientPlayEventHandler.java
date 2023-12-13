package net.iamaprogrammer.toggleableitemframes.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.iamaprogrammer.toggleableitemframes.ToggleableItemFramesClient;
import net.iamaprogrammer.toggleableitemframes.networking.VersionIdentifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class ClientPlayEventHandler implements ClientPlayConnectionEvents.Join {
    @Override
    public void onPlayReady(ClientPlayNetworkHandler handler, PacketSender sender, MinecraftClient client) {
        if (client.getServer() != null) {
            ToggleableItemFramesClient.ALWAYS_INVISIBLE = false;
        } else {
            ClientPlayNetworking.send(VersionIdentifier.version_id, PacketByteBufs.create().writeString(VersionIdentifier.MOD_VERSION));
        }
    }
}
