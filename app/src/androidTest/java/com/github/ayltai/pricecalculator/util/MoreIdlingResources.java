package com.github.ayltai.pricecalculator.util;

public final class MoreIdlingResources {
    public static void sleep(final int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
