package com.github.ayltai.pricecalculator.util;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import org.hamcrest.Matcher;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public final class MoreViewAssertions {
    public static ViewAssertion withItemCount(final int count) {
        return MoreViewAssertions.withItemCount(is(count));
    }

    public static ViewAssertion withItemCount(@NonNull final Matcher<Integer> matcher) {
        return new ViewAssertion() {
            @Override
            public void check(@NonNull final View view, @Nullable final NoMatchingViewException noViewFoundException) {
                if (noViewFoundException != null) throw noViewFoundException;

                if (view instanceof RecyclerView) {
                    final RecyclerView.Adapter adapter = ((RecyclerView)view).getAdapter();
                    if (adapter == null) {
                        fail("RecyclerView has no adapter");
                    } else {
                        assertThat("Item count does not match", adapter.getItemCount(), matcher);
                    }
                } else {
                    fail("withItemCount is applicable to RecyclerView only");
                }
            }
        };
    }
}
