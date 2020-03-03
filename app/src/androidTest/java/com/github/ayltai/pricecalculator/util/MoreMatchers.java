package com.github.ayltai.pricecalculator.util;

import androidx.annotation.NonNull;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public final class MoreMatchers {
    private MoreMatchers() {
    }

    @NonNull
    public static <T> Matcher<T> first(@NonNull final Matcher<T> matcher) {
        return MoreMatchers.at(matcher, 0);
    }

    @NonNull
    public static <T> Matcher<T> at(@NonNull final Matcher<T> matcher, final int position) {
        return new BaseMatcher<T>() {
            private int count;

            @Override
            public void describeTo(@NonNull final Description description) {
                description.appendText(position == 0 ? "the first matched element" : String.format("the %s-th matched element", position + 1));
            }

            @Override
            public boolean matches(@NonNull final Object item) {
                if (matcher.matches(item)) {
                    if (count == position) {
                        count++;

                        return true;
                    }

                    count++;
                }

                return false;
            }
        };
    }
}
