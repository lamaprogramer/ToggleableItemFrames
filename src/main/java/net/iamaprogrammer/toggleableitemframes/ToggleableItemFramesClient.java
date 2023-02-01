package net.iamaprogrammer.toggleableitemframes;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.iamaprogrammer.toggleableitemframes.event.ClientTickHandler;

public class ToggleableItemFramesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.START_CLIENT_TICK.register(new ClientTickHandler());
    }
}
