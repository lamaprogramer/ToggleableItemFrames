package net.iamaprogrammer.toggleableitemframes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.iamaprogrammer.toggleableitemframes.event.ClientDisconnectEventHandler;
import net.iamaprogrammer.toggleableitemframes.event.ClientPlayEventHandler;
import net.iamaprogrammer.toggleableitemframes.networking.VersionIdentifier;

public class ToggleableItemFramesClient implements ClientModInitializer {
    public static boolean ALWAYS_INVISIBLE = true;
    @Override
    public void onInitializeClient() {
        ClientPlayConnectionEvents.JOIN.register(new ClientPlayEventHandler());
        ClientPlayConnectionEvents.DISCONNECT.register(new ClientDisconnectEventHandler());

        ClientPlayNetworking.registerGlobalReceiver(VersionIdentifier.version_id, (client, handler, buf, responseSender) -> {
            ALWAYS_INVISIBLE = !buf.readString().equals(VersionIdentifier.MOD_VERSION);
            System.out.println(ALWAYS_INVISIBLE);
        });
    }
}
