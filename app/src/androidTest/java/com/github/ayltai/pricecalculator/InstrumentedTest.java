package com.github.ayltai.pricecalculator;

import java.io.File;

import androidx.annotation.CallSuper;
import androidx.lifecycle.Lifecycle;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.github.ayltai.pricecalculator.app.MainActivity;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public abstract class InstrumentedTest {
    @Rule
    public final ActivityScenarioRule<MainActivity> rule = new ActivityScenarioRule<>(MainActivity.class);

    @CallSuper
    @BeforeClass
    public static void init() {
        final File file = new File("/sdcard/tmp/code-coverage/connected");
        if (!file.exists()) file.mkdirs();
    }

    @CallSuper
    @Before
    public void setUp() {
        this.rule.getScenario().moveToState(Lifecycle.State.RESUMED);
    }

    @CallSuper
    @After
    public void tearDown() {
        this.rule.getScenario().moveToState(Lifecycle.State.DESTROYED);
    }
}
