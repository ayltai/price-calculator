package com.github.ayltai.pricecalculator.util;

import java.util.Map;

import android.util.ArrayMap;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.InverseMethod;

import com.github.ayltai.pricecalculator.model.Unit;
import com.github.ayltai.pricecalculator.model.UnitType;

public final class Converter {
    private static final Map<String, Unit>      UNITS      = new ArrayMap<>();
    private static final Map<Integer, UnitType> UNIT_TYPES = new ArrayMap<>();

    private Converter() {
    }

    @NonNull
    @InverseMethod("stringToInteger")
    public static String integerToString(final int value) {
        return Double.compare(0, value) == 0 ? "" : String.valueOf(value);
    }

    public static int stringToInteger(@NonNull final String value) {
        try {
            return value.isEmpty() ? 0 : Integer.parseInt(value);
        } catch (final NumberFormatException e) {
            return 0;
        }
    }

    @NonNull
    @InverseMethod("stringToDouble")
    public static String doubleToString(final double value) {
        if (Double.compare(0, value) == 0) return "";

        return Double.compare(value, Math.floor(value)) == 0 ? String.valueOf((int)value) : String.valueOf(value);
    }

    public static double stringToDouble(@NonNull final String value) {
        if (value.isEmpty()) return 0;

        try {
            final double doubleValue = Double.parseDouble(value);
            if (Double.compare(doubleValue, Math.floor(doubleValue)) == 0) return Integer.parseInt(value);

            return doubleValue;
        } catch (final NumberFormatException e) {
            return 0;
        }
    }

    @NonNull
    @InverseMethod("stringToUnit")
    public static String unitToString(@NonNull final Unit value) {
        return value.getName();
    }

    @NonNull
    public static Unit stringToUnit(@NonNull final String value) {
        if (Converter.UNITS.isEmpty()) {
            Converter.UNITS.put(Unit.SQUARE_METRE.getName(), Unit.SQUARE_METRE);
            Converter.UNITS.put(Unit.SQUARE_KILO_METRE.getName(), Unit.SQUARE_KILO_METRE);
            Converter.UNITS.put(Unit.SQUARE_INCH.getName(), Unit.SQUARE_INCH);
            Converter.UNITS.put(Unit.SQUARE_FOOT.getName(), Unit.SQUARE_FOOT);
            Converter.UNITS.put(Unit.SQUARE_YARD.getName(), Unit.SQUARE_YARD);
            Converter.UNITS.put(Unit.SQUARE_MILE.getName(), Unit.SQUARE_MILE);
            Converter.UNITS.put(Unit.MILLI_METRE.getName(), Unit.MILLI_METRE);
            Converter.UNITS.put(Unit.CENTI_METRE.getName(), Unit.CENTI_METRE);
            Converter.UNITS.put(Unit.METRE.getName(), Unit.METRE);
            Converter.UNITS.put(Unit.KILOMETRE.getName(), Unit.KILOMETRE);
            Converter.UNITS.put(Unit.INCH.getName(), Unit.INCH);
            Converter.UNITS.put(Unit.YARD.getName(), Unit.YARD);
            Converter.UNITS.put(Unit.FOOT.getName(), Unit.FOOT);
            Converter.UNITS.put(Unit.MILE.getName(), Unit.MILE);
            Converter.UNITS.put(Unit.MINUTE.getName(), Unit.MINUTE);
            Converter.UNITS.put(Unit.HOUR.getName(), Unit.HOUR);
            Converter.UNITS.put(Unit.DAY.getName(), Unit.DAY);
            Converter.UNITS.put(Unit.WEEK.getName(), Unit.WEEK);
            Converter.UNITS.put(Unit.MONTH.getName(), Unit.MONTH);
            Converter.UNITS.put(Unit.YEAR.getName(), Unit.YEAR);
            Converter.UNITS.put(Unit.MILLI_LITRE.getName(), Unit.MILLI_LITRE);
            Converter.UNITS.put(Unit.LITRE.getName(), Unit.LITRE);
            Converter.UNITS.put(Unit.GALLON_US.getName(), Unit.GALLON_US);
            Converter.UNITS.put(Unit.GALLON_SI.getName(), Unit.GALLON_SI);
            Converter.UNITS.put(Unit.OUNCE_US.getName(), Unit.OUNCE_US);
            Converter.UNITS.put(Unit.OUNCE_SI.getName(), Unit.OUNCE_SI);
            Converter.UNITS.put(Unit.MILLI_GRAM.getName(), Unit.MILLI_GRAM);
            Converter.UNITS.put(Unit.GRAM.getName(), Unit.GRAM);
            Converter.UNITS.put(Unit.KILO_GRAM.getName(), Unit.KILO_GRAM);
            Converter.UNITS.put(Unit.TONNE_US.getName(), Unit.TONNE_US);
            Converter.UNITS.put(Unit.TONNE_UK.getName(), Unit.TONNE_UK);
            Converter.UNITS.put(Unit.TONNE_SI.getName(), Unit.TONNE_SI);
            Converter.UNITS.put(Unit.OUNCE.getName(), Unit.OUNCE);
            Converter.UNITS.put(Unit.POUND.getName(), Unit.POUND);
        }

        final Unit unit = Converter.UNITS.get(value);

        return unit == null ? Unit.NONE : unit;
    }

    public static int unitTypeToInteger(@NonNull final UnitType value) {
        return value.getId();
    }

    @Nullable
    public static UnitType integerToUnitType(final int value) {
        if (Converter.UNIT_TYPES.isEmpty()) {
            Converter.UNIT_TYPES.put(UnitType.NONE.getId(), UnitType.NONE);
            Converter.UNIT_TYPES.put(UnitType.AREA.getId(), UnitType.AREA);
            Converter.UNIT_TYPES.put(UnitType.LENGTH.getId(), UnitType.LENGTH);
            Converter.UNIT_TYPES.put(UnitType.TIME.getId(), UnitType.TIME);
            Converter.UNIT_TYPES.put(UnitType.VOLUME.getId(), UnitType.VOLUME);
            Converter.UNIT_TYPES.put(UnitType.WEIGHT.getId(), UnitType.WEIGHT);
        }

        return Converter.UNIT_TYPES.get(value);
    }
}
