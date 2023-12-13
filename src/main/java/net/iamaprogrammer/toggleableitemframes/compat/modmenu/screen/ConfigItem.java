package net.iamaprogrammer.toggleableitemframes.compat.modmenu.screen;

import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

public class ConfigItem {
    private final Text label;
    private final ClickableWidget widget;

    public ConfigItem(Text label, ClickableWidget widget) {
        this.label = label;
        this.widget = widget;
    }

    public Text getLabel() {
        return label;
    }

    public ClickableWidget getWidget() {
        return widget;
    }
}
