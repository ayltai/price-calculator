package com.github.ayltai.pricecalculator.util;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.annotation.NonNull;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public final class MoreViewMatchers {
    private MoreViewMatchers() {
    }

    @NonNull
    public static Matcher<View> childAtPosition(@NonNull final Matcher<View> parentMatcher, final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(@NonNull final Description description) {
                parentMatcher.describeTo(description.appendText("child at position " + position + " in parent"));
            }

            @Override
            public boolean matchesSafely(@NonNull final View view) {
                final ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
}
