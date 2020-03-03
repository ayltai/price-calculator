package com.github.ayltai.pricecalculator.util;

import android.os.Build;

import com.github.ayltai.pricecalculator.UnitTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public final class DevUtilsTest extends UnitTest {
    @Test
    public void testIsLoggable() {
        assertFalse("Test environment is not loggable", DevUtils.isLoggable());
    }

    @Test
    public void testIsRunningTests() {
        assertTrue("Tests are running", DevUtils.isRunningTests());
    }

    @Test
    public void testIsRunningUnitTest() {
        assertTrue("Unit tests are running", DevUtils.isRunningUnitTest());
    }

    @Test
    public void testIsRunningInstrumentedTest() {
        assertFalse("Instrumented tests are not running", DevUtils.isRunningInstrumentedTest());
    }

    @Test
    public void testNewThreadPolicy() {
        if (Build.VERSION.SDK_INT >= 26) {
            assertEquals("Thread policy is correct for API 26+", "[StrictMode.ThreadPolicy; mask=1114172]", DevUtils.newThreadPolicy().toString());
        } else if (Build.VERSION.SDK_INT >= 23) {
            assertEquals("Thread policy is correct for API 23 - 25", "[StrictMode.ThreadPolicy; mask=1114140]", DevUtils.newThreadPolicy().toString());
        } else if (Build.VERSION.SDK_INT >= 19) {
            assertEquals("Thread policy is correct for API 19 - 22", "[StrictMode.ThreadPolicy; mask=2076]", DevUtils.newThreadPolicy().toString());
        }
    }

    @Test
    public void testNewVmPolicy() {
        if (Build.VERSION.SDK_INT >= 28) {
            assertEquals("Thread policy is correct for API 28+", "[StrictMode.VmPolicy; mask=1073854208]", DevUtils.newVmPolicy().toString());
        } else if (Build.VERSION.SDK_INT >= 26) {
            assertEquals("Thread policy is correct for API 26 - 27", "[StrictMode.VmPolicy; mask=112384]", DevUtils.newVmPolicy().toString());
        } else if (Build.VERSION.SDK_INT >= 23) {
            assertEquals("Thread policy is correct for API 23 - 25", "[StrictMode.VmPolicy; mask=79616]", DevUtils.newVmPolicy().toString());
        } else if (Build.VERSION.SDK_INT >= 19) {
            assertEquals("Thread policy is correct for API 19 - 22", "[StrictMode.VmPolicy; mask=28176]", DevUtils.newVmPolicy().toString());
        }
    }
}
