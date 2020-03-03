package com.github.ayltai.pricecalculator.model;

import java.util.Map;

import androidx.annotation.ArrayRes;
import androidx.annotation.StringRes;
import androidx.collection.ArrayMap;

import com.github.ayltai.pricecalculator.R;

public enum UnitType {
    NONE(0),
    AREA(1),
    LENGTH(2),
    TIME(3),
    VOLUME(4),
    WEIGHT(5);

    private static final Map<Integer, Integer> RESOURCE_IDS = new ArrayMap<>();
    private static final Map<Integer, Integer> ITEM_IDS     = new ArrayMap<>();

    private int id;

    UnitType(final int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    @StringRes
    public int getResId() {
        if (UnitType.RESOURCE_IDS.isEmpty()) {
            UnitType.RESOURCE_IDS.put(UnitType.AREA.id, R.string.label_area);
            UnitType.RESOURCE_IDS.put(UnitType.LENGTH.id, R.string.label_length);
            UnitType.RESOURCE_IDS.put(UnitType.TIME.id, R.string.label_time);
            UnitType.RESOURCE_IDS.put(UnitType.VOLUME.id, R.string.label_volume);
            UnitType.RESOURCE_IDS.put(UnitType.WEIGHT.id, R.string.label_weight);
        }

        final Integer resId = UnitType.RESOURCE_IDS.get(this.id);
        return resId == null ? 0 : resId;
    }

    @ArrayRes
    public int getItemsResId() {
        if (UnitType.ITEM_IDS.isEmpty()) {
            UnitType.ITEM_IDS.put(UnitType.AREA.id, R.array.label_areas);
            UnitType.ITEM_IDS.put(UnitType.LENGTH.id, R.array.label_lengths);
            UnitType.ITEM_IDS.put(UnitType.TIME.id, R.array.label_times);
            UnitType.ITEM_IDS.put(UnitType.VOLUME.id, R.array.label_volumes);
            UnitType.ITEM_IDS.put(UnitType.WEIGHT.id, R.array.label_weights);
        }

        final Integer itemId = UnitType.ITEM_IDS.get(this.id);
        return itemId == null ? 0 : itemId;
    }
}
