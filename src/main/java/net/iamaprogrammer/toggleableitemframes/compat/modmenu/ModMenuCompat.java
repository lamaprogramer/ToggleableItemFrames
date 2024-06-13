package net.iamaprogrammer.toggleableitemframes.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.iamaprogrammer.toggleableitemframes.ToggleableItemFramesClient;
import net.iamaprogrammer.toggleableitemframes.compat.modmenu.screen.ConfigScreen;
import net.iamaprogrammer.toggleableitemframes.config.CoreConfig;
import net.minecraft.text.Text;

import java.util.List;

public class ModMenuCompat implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return (ConfigScreenFactory<ConfigScreen<?>>) screen -> ConfigScreen.builder(screen, new CoreConfig(ToggleableItemFramesClient.CONFIG),
                        CoreConfig.class,
                        (config) -> ToggleableItemFramesClient.CONFIG = config)
                .addCyclingButtonWidget(
                        Text.translatable("toggleableitemframes.option.desc.showinvisibleframeswhenheld"),
                        List.of(true, false),
                        CoreConfig::showInvisibleFramesWhenHeld,
                        CoreConfig::shouldShowInvisibleFramesWhenHeld
                )
                .build();
    }
}
