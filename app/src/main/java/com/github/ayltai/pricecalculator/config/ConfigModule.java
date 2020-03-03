package com.github.ayltai.pricecalculator.config;

import javax.inject.Singleton;

import android.content.Context;
import androidx.annotation.NonNull;

import dagger.Module;
import dagger.Provides;

@Module
public final class ConfigModule {
    private ConfigModule() {
    }

    public static void init(@NonNull @lombok.NonNull final Context context) {
        PreferenceUserConfigurations.init(context);
    }

    @Singleton
    @NonNull
    @Provides
    static UserConfigurations provideUserConfigurations() {
        return PreferenceUserConfigurations.getInstance();
    }
}
