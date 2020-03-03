package com.github.ayltai.pricecalculator;

import com.github.ayltai.pricecalculator.model.Unit;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Constants {
    public static final int DEFAULT_UNIT_TYPE = 5;

    public static final Unit DEFAULT_AREA   = Unit.SQUARE_METRE;
    public static final Unit DEFAULT_LENGTH = Unit.METRE;
    public static final Unit DEFAULT_TIME   = Unit.DAY;
    public static final Unit DEFAULT_VOLUME = Unit.MILLI_LITRE;
    public static final Unit DEFAULT_WEIGHT = Unit.GRAM;

    public static final int DEBOUNCE_CHANGES = 300;
}
