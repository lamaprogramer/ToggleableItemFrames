package net.iamaprogrammer.toggleableitemframes.event;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.iamaprogrammer.toggleableitemframes.ToggleableItemFramesClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;

public class ClientDisconnectEventHandler implements ClientPlayConnectionEvents.Disconnect {
    @Override
    public void onPlayDisconnect(ClientPlayNetworkHandler handler, MinecraftClient client) {
        ToggleableItemFramesClient.ALWAYS_INVISIBLE = true;
    }
}
