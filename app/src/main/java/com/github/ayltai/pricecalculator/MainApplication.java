package com.github.ayltai.pricecalculator;

import androidx.annotation.CallSuper;

import com.github.ayltai.pricecalculator.config.ConfigModule;
import com.github.ayltai.pricecalculator.util.DevUtils;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

public final class MainApplication extends BaseApplication {
    @CallSuper
    @Override
    public void onCreate() {
        super.onCreate();

        DevUtils.applyDevMode();

        ConfigModule.init(this);

        if (!DevUtils.isLoggable() && !DevUtils.isRunningTests()) FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true);
    }
}
