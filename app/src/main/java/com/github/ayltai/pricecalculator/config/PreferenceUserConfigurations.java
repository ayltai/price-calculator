package com.github.ayltai.pricecalculator.config;

import javax.inject.Singleton;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.preference.PreferenceManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.ayltai.pricecalculator.Constants;
import com.github.ayltai.pricecalculator.model.UnitType;
import com.github.ayltai.pricecalculator.util.Converter;

@Singleton
public final class PreferenceUserConfigurations implements UserConfigurations {
    private static final String KEY_IS_DARK_THEME = "IS_DARK_THEME";
    private static final String KEY_UNIT_TYPE     = "UNIT_TYPE";

    @SuppressLint("StaticFieldLeak")
    private static PreferenceUserConfigurations instance;

    @NonNull
    private final Context context;

    @NonNull
    private final SharedPreferences preferences;

    static void init(@NonNull @lombok.NonNull final Context context) {
        if (PreferenceUserConfigurations.instance == null) PreferenceUserConfigurations.instance = new PreferenceUserConfigurations(context.getApplicationContext());
    }

    @NonNull
    static UserConfigurations getInstance() {
        if (PreferenceUserConfigurations.instance == null) throw new NullPointerException("Did you forget to call init()?");

        return PreferenceUserConfigurations.instance;
    }

    private PreferenceUserConfigurations(@NonNull @lombok.NonNull final Context context) {
        this.context     = context;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isDarkTheme() {
        return this.preferences.getBoolean(PreferenceUserConfigurations.KEY_IS_DARK_THEME, (this.context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES);
    }

    public void setIsDarkTheme(final boolean isDarkTheme) {
        this.preferences
            .edit()
            .putBoolean(PreferenceUserConfigurations.KEY_IS_DARK_THEME, isDarkTheme)
            .apply();
    }

    @Nullable
    public UnitType getUnitType() {
        return Converter.integerToUnitType(this.preferences.getInt(PreferenceUserConfigurations.KEY_UNIT_TYPE, Constants.DEFAULT_UNIT_TYPE));
    }

    public void setUnitType(@NonNull final UnitType unitType) {
        this.preferences
            .edit()
            .putInt(PreferenceUserConfigurations.KEY_UNIT_TYPE, unitType.getId())
            .apply();
    }
}
