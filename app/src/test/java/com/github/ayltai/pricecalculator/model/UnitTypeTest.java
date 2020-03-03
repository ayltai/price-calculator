package com.github.ayltai.pricecalculator.model;

import com.github.ayltai.pricecalculator.R;
import com.github.ayltai.pricecalculator.UnitTest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class UnitTypeTest extends UnitTest {
    @Test
    public void testGetResId() {
        assertEquals("Resource ID of none is 0", 0, UnitType.NONE.getResId());
        assertEquals(String.format("Resource ID of area is %s", R.string.label_area), R.string.label_area, UnitType.AREA.getResId());
    }

    @Test
    public void testGetItemsResId() {
        assertEquals("Item resource ID of none is 0", 0, UnitType.NONE.getItemsResId());
        assertEquals(String.format("Item resource ID of area is %s", R.array.label_areas), R.array.label_areas, UnitType.AREA.getItemsResId());
    }
}
