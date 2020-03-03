package com.github.ayltai.pricecalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import com.github.ayltai.pricecalculator.model.Unit;
import com.github.ayltai.pricecalculator.util.MoreViewAssertions;
import org.junit.Assert;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.github.ayltai.pricecalculator.util.MoreIdlingResources.sleep;
import static com.github.ayltai.pricecalculator.util.MoreMatchers.at;
import static com.github.ayltai.pricecalculator.util.MoreMatchers.first;
import static org.hamcrest.Matchers.is;

public final class MainActivityTest extends InstrumentedTest {
    @Test
    public void testToggleTheme() {
        final int nightMode = AppCompatDelegate.getDefaultNightMode();

        onView(withId(R.id.action_theme)).perform(click());

        this.rule.getScenario().recreate();
        this.rule.getScenario().moveToState(Lifecycle.State.RESUMED);

        Assert.assertNotEquals("Default night mode is on", nightMode, AppCompatDelegate.getDefaultNightMode());
    }

    @Test
    public void testAdd() {
        // Checks if item count is 2
        this.getItemList().check(MoreViewAssertions.withItemCount(2));

        // Clicks Add button
        onView(withId(R.id.actionButton)).perform(click());

        // Checks if item count is 3
        this.getItemList().check(MoreViewAssertions.withItemCount(3));
    }

    @Test
    public void testClear() {
        // Fills in "1" in price view
        this.getPriceView().perform(replaceText("1"));

        // Checks if "1" is displayed in price view
        this.getPriceView().check(matches(withText("1")));

        // Clicks Clear button
        onView(first(withText(R.string.action_clear))).perform(click());

        // Checks if price view is blank
        this.getPriceView().check(matches(withText("")));
    }

    @Test
    public void testClearAll() {
        // Fills in "1" in price view
        this.getPriceView().perform(replaceText("1"));

        // Checks if "1" is displayed in price view
        this.getPriceView().check(matches(withText("1")));

        // Clicks Clear All button
        onView(withId(R.id.action_clear_all)).perform(click());

        // Checks if price view is blank
        this.getPriceView().check(matches(withText("")));
    }

    @Test
    public void testRemove() {
        // Checks if item count is 2
        this.getItemList().check(MoreViewAssertions.withItemCount(2));

        // Clicks Remove button
        onView(first(withText(R.string.action_remove))).perform(click());

        // Checks if item count is 1
        this.getItemList().check(MoreViewAssertions.withItemCount(1));
    }

    @Test
    public void testChangeUnit() {
        // Checks if unit view is showing the default unit
        this.getUnitView().check(matches(withText(Unit.GRAM.getName())));

        // Clicks unit view
        this.getUnitView().perform(click());

        // Selects another unit
        onView(withText(Unit.KILO_GRAM.getName())).inRoot(isDialog()).perform(click());

        // Checks if unit view is showing another unit
        this.getUnitView().check(matches(withText(Unit.KILO_GRAM.getName())));
    }

    @Test
    public void testChangeUnitType() {
        // Checks if unit view is showing the default unit
        this.getUnitView().check(matches(withText(Unit.GRAM.getName())));

        // Clicks unit type button
        this.getUnitTypeButton().perform(click());

        // Selects another unit type
        onView(withText(R.string.label_volume)).inRoot(isDialog()).perform(click());
        sleep(1);

        // Checks if unit view is showing the another unit
        this.getUnitView().check(matches(withText(Unit.MILLI_LITRE.getName())));

        // Clicks unit type button
        this.getUnitTypeButton().perform(click());

        // Selects default unit type
        onView(withText(R.string.label_weight)).inRoot(isDialog()).perform(click());
        sleep(1);

        // Checks if unit view is showing the default unit
        this.getUnitView().check(matches(withText(Unit.GRAM.getName())));
    }

    @Test
    public void testBestPrice() {
        // Fills in the first item details
        this.getPriceView().perform(replaceText("1"));
        onView(first(withHint(R.string.label_weight))).perform(replaceText("1"));

        // Fills in the second item details
        onView(at(withHint(R.string.label_price), 1)).perform(replaceText("1"));
        onView(at(withHint(R.string.label_weight), 1)).perform(replaceText("2"));

        // Checks if favorite icon is hidden
        this.getFirstFavoriteIcon().check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        this.getSecondFavoriteIcon().check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));

        // Finds the best item
        onView(withId(R.id.action_done)).perform(click());
        sleep(1);

        // Checks if favorite icon is shown
        this.getFirstFavoriteIcon().check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        this.getSecondFavoriteIcon().check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @NonNull
    private ViewInteraction getItemList() {
        return onView(withClassName(is(RecyclerView.class.getName())));
    }

    @NonNull
    private ViewInteraction getPriceView() {
        return onView(first(withHint(R.string.label_price)));
    }

    @NonNull
    private ViewInteraction getUnitView() {
        return onView(first(withId(R.id.unit)));
    }

    @NonNull
    private ViewInteraction getUnitTypeButton() {
        return onView(withId(R.id.action_unit_type));
    }

    @NonNull
    private ViewInteraction getFirstFavoriteIcon() {
        return onView(first(withId(R.id.favorite)));
    }

    @NonNull
    private ViewInteraction getSecondFavoriteIcon() {
        return onView(at(withId(R.id.favorite), 1));
    }
}
