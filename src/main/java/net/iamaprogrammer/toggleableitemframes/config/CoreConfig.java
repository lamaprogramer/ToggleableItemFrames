package net.iamaprogrammer.toggleableitemframes.config;

import net.iamaprogrammer.toggleableitemframes.ToggleableItemFrames;
import net.iamaprogrammer.toggleableitemframes.config.core.Config;

public class CoreConfig implements Config {
    private boolean showInvisibleFramesWhenHeld;
    private int renderItemModelDistance;

    public CoreConfig() {}
    public CoreConfig(boolean showInvisibleFramesWhenHeld, int renderItemModelDistance) {
        this.showInvisibleFramesWhenHeld = showInvisibleFramesWhenHeld;
        this.renderItemModelDistance = renderItemModelDistance;
    }
    public CoreConfig(CoreConfig copy) {
        this(copy.showInvisibleFramesWhenHeld, copy.renderItemModelDistance);
    }

    public boolean shouldShowInvisibleFramesWhenHeld() {
        return this.showInvisibleFramesWhenHeld;
    }

    public void showInvisibleFramesWhenHeld(boolean showInvisibleFramesWhenHeld) {
        this.showInvisibleFramesWhenHeld = showInvisibleFramesWhenHeld;
    }

    public int getRenderItemModelDistance() {
        return this.renderItemModelDistance;
    }

    public void setRenderItemModelDistance(int renderItemModelDistance) {
        this.renderItemModelDistance = renderItemModelDistance;
    }
    @Override
    public String fileName() {
        return ToggleableItemFrames.MOD_ID;
    }
}
