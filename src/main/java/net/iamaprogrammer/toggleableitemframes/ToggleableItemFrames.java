package net.iamaprogrammer.toggleableitemframes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.iamaprogrammer.toggleableitemframes.event.ServerTickHandler;
import net.iamaprogrammer.toggleableitemframes.event.UseEntityHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ToggleableItemFrames implements ModInitializer {

	public static final String MOD_ID = "toggleableitemframes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		UseEntityCallback.EVENT.register(new UseEntityHandler());
		ServerTickEvents.START_SERVER_TICK.register(new ServerTickHandler());
	}
}
