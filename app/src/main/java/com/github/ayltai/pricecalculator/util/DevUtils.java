package com.github.ayltai.pricecalculator.util;

import java.util.concurrent.atomic.AtomicBoolean;

import android.os.Build;
import android.os.StrictMode;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;

import com.github.ayltai.pricecalculator.BuildConfig;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DevUtils {
    private AtomicBoolean isRunningUnitTest;
    private AtomicBoolean isRunningInstrumentedTest;

    public boolean isLoggable() {
        return BuildConfig.DEBUG && !DevUtils.isRunningTests();
    }

    public boolean isRunningTests() {
        return DevUtils.isRunningUnitTest() || DevUtils.isRunningInstrumentedTest();
    }

    public synchronized boolean isRunningUnitTest() {
        if (DevUtils.isRunningUnitTest == null) {
            boolean isRunningUnitTest = false;

            try {
                Class.forName("org.robolectric.RobolectricTestRunner");
                isRunningUnitTest = true;
            } catch (final ClassNotFoundException e) {
                // Ignored
            }

            DevUtils.isRunningUnitTest = new AtomicBoolean(isRunningUnitTest);
        }

        return DevUtils.isRunningUnitTest.get();
    }

    public synchronized boolean isRunningInstrumentedTest() {
        if (DevUtils.isRunningInstrumentedTest == null) {
            boolean isRunningInstrumentedTest = false;

            try {
                Class.forName("androidx.test.espresso.Espresso");
                isRunningInstrumentedTest = true;
            } catch (final ClassNotFoundException e) {
                // Ignored
            }

            DevUtils.isRunningInstrumentedTest = new AtomicBoolean(isRunningInstrumentedTest);
        }

        return DevUtils.isRunningInstrumentedTest.get();
    }

    public void applyDevMode() {
        if (!BuildConfig.DEBUG && !DevUtils.isRunningTests()) {
            StrictMode.setThreadPolicy(DevUtils.newThreadPolicy());
            StrictMode.setVmPolicy(DevUtils.newVmPolicy());
        }
    }

    @VisibleForTesting
    @NonNull
    protected StrictMode.ThreadPolicy newThreadPolicy() {
        final StrictMode.ThreadPolicy.Builder builder = new StrictMode.ThreadPolicy.Builder()
            .detectCustomSlowCalls()
            .detectNetwork()
            .penaltyLog()
            .penaltyFlashScreen();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) builder.detectResourceMismatches();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) builder.detectUnbufferedIo();

        return builder.build();
    }

    @VisibleForTesting
    @NonNull
    protected StrictMode.VmPolicy newVmPolicy() {
        final StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder()
            .detectActivityLeaks()
            .detectFileUriExposure()
            .detectLeakedClosableObjects()
            .detectLeakedRegistrationObjects()
            .detectLeakedSqlLiteObjects()
            .penaltyLog();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) builder.detectContentUriWithoutPermission();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) builder.detectNonSdkApiUsage();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) builder.detectImplicitDirectBoot();

        return builder.build();
    }
}
