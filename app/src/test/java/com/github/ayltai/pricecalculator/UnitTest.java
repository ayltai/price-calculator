package com.github.ayltai.pricecalculator;

import android.os.Build;
import androidx.annotation.CallSuper;

import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.multidex.ShadowAndroidXMultiDex;

@RunWith(RobolectricTestRunner.class)
@Config(
    minSdk  = Build.VERSION_CODES.KITKAT,
    maxSdk  = Build.VERSION_CODES.P,
    shadows = {
        ShadowAndroidXMultiDex.class
    }
)
public abstract class UnitTest {
    @BeforeClass
    public static void setUpClass() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setSingleSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @CallSuper
    @Before
    public void setUp() throws Exception {
    }

    @CallSuper
    @After
    public void tearDown() throws Exception {
    }
}
