package com.github.ayltai.pricecalculator.util;

import java.util.Map;

import android.util.ArrayMap;
import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.github.ayltai.pricecalculator.R;
import com.github.ayltai.pricecalculator.model.UnitType;
import com.google.android.material.textfield.TextInputLayout;

public final class DataBindingUtils {
    private static final Map<UnitType, Integer> UNIT_TYPE_NAMES = new ArrayMap<>();

    private DataBindingUtils() {
    }

    @BindingAdapter("android:hint")
    public static void setHint(@NonNull final TextInputLayout view, @NonNull final UnitType value) {
        if (DataBindingUtils.UNIT_TYPE_NAMES.isEmpty()) {
            DataBindingUtils.UNIT_TYPE_NAMES.put(UnitType.NONE, 0);
            DataBindingUtils.UNIT_TYPE_NAMES.put(UnitType.AREA, R.string.label_area);
            DataBindingUtils.UNIT_TYPE_NAMES.put(UnitType.LENGTH, R.string.label_length);
            DataBindingUtils.UNIT_TYPE_NAMES.put(UnitType.TIME, R.string.label_time);
            DataBindingUtils.UNIT_TYPE_NAMES.put(UnitType.VOLUME, R.string.label_volume);
            DataBindingUtils.UNIT_TYPE_NAMES.put(UnitType.WEIGHT, R.string.label_weight);
        }

        final Integer resId = DataBindingUtils.UNIT_TYPE_NAMES.get(value);
        view.setHint(resId == null ? null : view.getResources().getText(resId));
    }
}
