package com.github.ayltai.pricecalculator.model;

import java.util.Arrays;
import java.util.Collections;

import androidx.annotation.CallSuper;
import androidx.test.core.app.ApplicationProvider;

import com.github.ayltai.pricecalculator.UnitTest;
import com.github.ayltai.pricecalculator.config.ConfigModule;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class ItemTest extends UnitTest {
    @CallSuper
    @Override
    public void setUp() throws Exception {
        super.setUp();

        ConfigModule.init(ApplicationProvider.getApplicationContext());
    }

    @Test
    public void testIsValid() {
        final Item item = Item.create();
        Assert.assertFalse("Default item is invalid", item.isValid());

        item.setPrice(1);
        Assert.assertFalse("Item is invalid if size is 0", item.isValid());

        item.setSize(1);
        Assert.assertTrue("Item is valid if price, quantity and size larger than zero", item.isValid());
    }

    @Test
    public void testIsBest() {
        final Item item = Item.create();
        Assert.assertFalse("Item is not the best", item.isBest());

        item.setBestItem(item);
        Assert.assertTrue("Item is the best", item.isBest());
    }

    @Test
    public void testClear() {
        final Item item = Item.create();
        item.setPrice(1);
        item.setQuantity(2);
        item.setSize(3);

        Assert.assertNotEquals("Item does not equal to its default", Item.create(), item);

        item.clear();
        assertEquals("Item equals to its default", Item.create(), item);
    }

    @Test
    public void testGetDiff() {
        final Item item = Item.create();
        assertEquals("Default diff is 0", 0, item.getDiff(), 0.01);

        final Item bestItem = Item.create();
        item.setBestItem(bestItem);
        assertEquals("Diff is 0 if price is 0", 0, item.getDiff(), 0.01);

        item.setPrice(1);
        item.setSize(1);
        bestItem.setPrice(1);
        bestItem.setSize(2);
        assertEquals("Diff is 1", 1, item.getDiff(), 0.01);
    }

    @Test(expected = NullPointerException.class)
    public void testFindBestForNull() {
        Item.findBest(null);
    }

    @Test
    public void testFindBest() {
        Assert.assertNull("The best item is null", Item.findBest(Collections.emptyList()));

        final Item item = Item.create();
        item.setPrice(1);
        item.setSize(1);

        final Item bestItem = Item.create();
        Assert.assertNull("The best item is null", Item.findBest(Arrays.asList(item, bestItem)));

        bestItem.setPrice(1);
        bestItem.setSize(2);
        assertEquals("Find the best item correctly", bestItem, Item.findBest(Arrays.asList(item, bestItem)));
    }
}
