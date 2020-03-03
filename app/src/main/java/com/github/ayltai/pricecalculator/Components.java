package com.github.ayltai.pricecalculator;

import androidx.annotation.NonNull;

import com.github.ayltai.pricecalculator.config.ConfigComponent;
import com.github.ayltai.pricecalculator.config.DaggerConfigComponent;

public final class Components {
    private static Components instance = new Components();

    private ConfigComponent configComponent;

    public static Components getInstance() {
        return Components.instance;
    }

    @NonNull
    public ConfigComponent getConfigComponent() {
        if (this.configComponent == null) this.configComponent = DaggerConfigComponent.create();

        return this.configComponent;
    }
}
