package net.iamaprogrammer.toggleableitemframes.config;

import net.iamaprogrammer.toggleableitemframes.ToggleableItemFrames;
import net.iamaprogrammer.toggleableitemframes.config.core.Config;

public class CoreConfig implements Config {
    private boolean showInvisibleFramesWhenHeld;

    public CoreConfig() {}
    public CoreConfig(boolean showInvisibleFramesWhenHeld) {
        this.showInvisibleFramesWhenHeld = showInvisibleFramesWhenHeld;
    }
    public CoreConfig(CoreConfig copy) {
        this(copy.showInvisibleFramesWhenHeld);
    }

    public boolean shouldShowInvisibleFramesWhenHeld() {
        return this.showInvisibleFramesWhenHeld;
    }

    public void showInvisibleFramesWhenHeld(boolean showInvisibleFramesWhenHeld) {
        this.showInvisibleFramesWhenHeld = showInvisibleFramesWhenHeld;
    }

    @Override
    public String fileName() {
        return ToggleableItemFrames.MOD_ID;
    }
}
