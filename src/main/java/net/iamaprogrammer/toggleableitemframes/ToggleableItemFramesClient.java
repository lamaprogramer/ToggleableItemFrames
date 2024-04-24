package net.iamaprogrammer.toggleableitemframes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.iamaprogrammer.toggleableitemframes.config.CoreConfig;
import net.iamaprogrammer.toggleableitemframes.config.core.ConfigRegistry;
import net.iamaprogrammer.toggleableitemframes.event.ClientDisconnectEventHandler;
import net.iamaprogrammer.toggleableitemframes.event.ClientPlayEventHandler;
import net.iamaprogrammer.toggleableitemframes.networking.VersionIdentifier;
import net.iamaprogrammer.toggleableitemframes.networking.packets.ModVersionPacket;

public class ToggleableItemFramesClient implements ClientModInitializer {
    public static boolean ALWAYS_INVISIBLE = true;
    public static CoreConfig CONFIG;
    @Override
    public void onInitializeClient() {
        CoreConfig defaultConfig = new CoreConfig();
        defaultConfig.setRenderItemModelDistance(75);
        defaultConfig.showInvisibleFramesWhenHeld(true);

        CONFIG = new ConfigRegistry<>(defaultConfig, CoreConfig.class).register();

        ClientPlayConnectionEvents.JOIN.register(new ClientPlayEventHandler());
        ClientPlayConnectionEvents.DISCONNECT.register(new ClientDisconnectEventHandler());

        PayloadTypeRegistry.playS2C().register(ModVersionPacket.PACKET_ID, ModVersionPacket.PACKET_CODEC);

        ClientPlayNetworking.registerGlobalReceiver(ModVersionPacket.PACKET_ID, (payload, context) -> {
            ALWAYS_INVISIBLE = !payload.version().equals(VersionIdentifier.MOD_VERSION);
            System.out.println(ALWAYS_INVISIBLE);
        });
    }
}
