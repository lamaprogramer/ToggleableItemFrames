package net.iamaprogrammer.toggleableitemframes.config;

import net.iamaprogrammer.toggleableitemframes.ToggleableItemFrames;
import net.iamaprogrammer.toggleableitemframes.config.core.Config;

public class CoreConfig implements Config {
    private boolean showInvisibleFramesWhenHeld;
    private boolean invisibleIfNotSupportedByServer;

    public CoreConfig() {}
    public CoreConfig(boolean showInvisibleFramesWhenHeld, boolean invisibleIfNotSupportedByServer) {
        this.showInvisibleFramesWhenHeld = showInvisibleFramesWhenHeld;
        this.invisibleIfNotSupportedByServer = invisibleIfNotSupportedByServer;
    }
    public CoreConfig(CoreConfig copy) {
        this(copy.showInvisibleFramesWhenHeld, copy.invisibleIfNotSupportedByServer);
    }

    public boolean shouldShowInvisibleFramesWhenHeld() {
        return this.showInvisibleFramesWhenHeld;
    }

    public void showInvisibleFramesWhenHeld(boolean showInvisibleFramesWhenHeld) {
        this.showInvisibleFramesWhenHeld = showInvisibleFramesWhenHeld;
    }

    public boolean isInvisibleIfNotSupportedByServer() {
        return this.invisibleIfNotSupportedByServer;
    }

    public void setInvisibleIfNotSupportedByServer(boolean invisibleIfNotSupportedByServer) {
        this.invisibleIfNotSupportedByServer = invisibleIfNotSupportedByServer;
    }

    @Override
    public String fileName() {
        return ToggleableItemFrames.MOD_ID;
    }
}
