package com.github.ayltai.pricecalculator.app;

import android.os.Bundle;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.github.ayltai.pricecalculator.Components;

public abstract class ThemedActivity extends AppCompatActivity {
    @CallSuper
    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(Components.getInstance().getConfigComponent().userConfigurations().isDarkTheme() ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

        super.onCreate(savedInstanceState);
    }
}
