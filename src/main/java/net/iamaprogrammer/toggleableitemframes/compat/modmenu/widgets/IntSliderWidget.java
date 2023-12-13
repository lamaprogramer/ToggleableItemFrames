package net.iamaprogrammer.toggleableitemframes.compat.modmenu.widgets;

import net.iamaprogrammer.toggleableitemframes.compat.modmenu.screen.ConfigScreen;
import net.iamaprogrammer.toggleableitemframes.config.core.Config;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;

public class IntSliderWidget<C extends Config> extends SliderWidget {
    private final int min;
    private final int max;
    private int intValue;
    private final ConfigScreen.UpdateCallback<C, Integer> callback;
    private final C config;
    protected IntSliderWidget(int x, int y, int width, int height, int min, int max, double value, C config, ConfigScreen.UpdateCallback<C, Integer> callback) {
        super(x, y, width, height, Text.of(String.valueOf((int)value)), translateValue(value, min, max));
        this.callback = callback;
        this.config = config;
        this.min = min;
        this.max = max;
    }
    private static double translateValue(double value, int min, int max) {
        return (value / (max-min)) - min;
    }

    @Override
    protected void updateMessage() {
        this.setMessage(Text.of(String.valueOf(this.intValue)));
    }

    @Override
    protected void applyValue() {
        this.intValue = (int) Math.floor((this.value * (this.max-this.min)) + this.min);
        this.callback.modifyConfigCallback(this.config, this.intValue);
    }
    public static <C extends Config> Builder<C> builder(C config) {
        return new Builder<>(config);
    }

    public static class Builder<C extends Config> {
        private int x = 0;
        private int y = 0;
        private int width = 150;
        private int height = 20;
        private int min = 0;
        private int max = 1;
        private int value = 1;
        private final C config;

        private Builder(C config) {
            this.config = config;
        }

        public Builder<C> setX(int x) {
            this.x = x;
            return this;
        }
        public Builder<C> setY(int y) {
            this.y = y;
            return this;
        }
        public Builder<C> setPosition(int x, int y) {
            this.x = x;
            this.y = y;
            return this;
        }
        public Builder<C> setWidth(int width) {
            this.width = width;
            return this;
        }
        public Builder<C> setHeight(int height) {
            this.height = height;
            return this;
        }
        public Builder<C> setSize(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }
        public Builder<C> setMin(int min) {
            this.min = min;
            return this;
        }
        public Builder<C> setMax(int max) {
            this.max = max;
            return this;
        }
        public Builder<C> setBounds(int min, int max) {
            this.min = min;
            this.max = max;
            return this;
        }
        public Builder<C> defaultValue(int value) {
            this.value = value;
            return this;
        }
        public IntSliderWidget<C> build(ConfigScreen.UpdateCallback<C, Integer> callback) {
            return new IntSliderWidget<>(this.x, this.y, this.width, this.height, this.min, this.max, this.value, this.config, callback);
        }
    }
}
