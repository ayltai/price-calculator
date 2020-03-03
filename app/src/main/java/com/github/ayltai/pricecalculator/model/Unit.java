package com.github.ayltai.pricecalculator.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import com.github.ayltai.pricecalculator.Components;
import com.github.ayltai.pricecalculator.Constants;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public final class Unit implements Parcelable {
    //region Constants

    public static final Unit NONE              = new Unit(UnitType.NONE, "", 1);
    public static final Unit SQUARE_METRE      = new Unit(UnitType.AREA, "sq. m", 1);
    public static final Unit SQUARE_KILO_METRE = new Unit(UnitType.AREA, "sq. km", 0.000001);
    public static final Unit SQUARE_INCH       = new Unit(UnitType.AREA, "sq. in", 1_550);
    public static final Unit SQUARE_FOOT       = new Unit(UnitType.AREA, "sq. ft", 10.7639);
    public static final Unit SQUARE_YARD       = new Unit(UnitType.AREA, "sq. yd", 1.19599);
    public static final Unit SQUARE_MILE       = new Unit(UnitType.AREA, "sq. mi", 0.0000003861);
    public static final Unit MILLI_METRE       = new Unit(UnitType.LENGTH, "mm", 1_000);
    public static final Unit CENTI_METRE       = new Unit(UnitType.LENGTH, "cm", 100);
    public static final Unit METRE             = new Unit(UnitType.LENGTH, "m", 1);
    public static final Unit KILOMETRE         = new Unit(UnitType.LENGTH, "km", 0.001);
    public static final Unit INCH              = new Unit(UnitType.LENGTH, "in", 39.3701);
    public static final Unit FOOT              = new Unit(UnitType.LENGTH, "ft", 3.28084);
    public static final Unit YARD              = new Unit(UnitType.LENGTH, "yd", 1.09361);
    public static final Unit MILE              = new Unit(UnitType.LENGTH, "mi", 0.000621371);
    public static final Unit MINUTE            = new Unit(UnitType.TIME, "min", 1_440);
    public static final Unit HOUR              = new Unit(UnitType.TIME, "hour", 24);
    public static final Unit DAY               = new Unit(UnitType.TIME, "day", 1);
    public static final Unit WEEK              = new Unit(UnitType.TIME, "week", 0.142857);
    public static final Unit MONTH             = new Unit(UnitType.TIME, "month", 0.0328767);
    public static final Unit YEAR              = new Unit(UnitType.TIME, "year", 0.00273971);
    public static final Unit MILLI_LITRE       = new Unit(UnitType.VOLUME, "mL", 1_000);
    public static final Unit LITRE             = new Unit(UnitType.VOLUME, "L", 1);
    public static final Unit OUNCE_US          = new Unit(UnitType.VOLUME, "oz (US)", 33.814);
    public static final Unit OUNCE_SI          = new Unit(UnitType.VOLUME, "oz (SI)", 35.1951);
    public static final Unit GALLON_US         = new Unit(UnitType.VOLUME, "gal (US)", 0.264172);
    public static final Unit GALLON_SI         = new Unit(UnitType.VOLUME, "gal", 0.219969);
    public static final Unit MILLI_GRAM        = new Unit(UnitType.WEIGHT, "mg", 1_000_000);
    public static final Unit GRAM              = new Unit(UnitType.WEIGHT, "g", 1_000);
    public static final Unit KILO_GRAM         = new Unit(UnitType.WEIGHT, "kg", 1);
    public static final Unit POUND             = new Unit(UnitType.WEIGHT, "lb", 2.20462);
    public static final Unit OUNCE             = new Unit(UnitType.WEIGHT, "oz", 35.274);
    public static final Unit TONNE_US          = new Unit(UnitType.WEIGHT, "ton (US)", 0.00110231);
    public static final Unit TONNE_UK          = new Unit(UnitType.WEIGHT, "ton (SI)", 0.000984207);
    public static final Unit TONNE_SI          = new Unit(UnitType.WEIGHT, "ton (UK)", 0.001);

    //endregion

    @Getter
    private final UnitType type;

    @Getter
    private final String name;

    @Getter
    private final double rate;

    public static final Creator<Unit> CREATOR = new Creator<Unit>() {
        @NonNull
        @Override
        public Unit createFromParcel(@NonNull @lombok.NonNull final Parcel in) {
            return new Unit(in);
        }

        @NonNull
        @Override
        public Unit[] newArray(final int size) {
            return new Unit[size];
        }
    };

    protected Unit(@NonNull @lombok.NonNull final Parcel in) {
        this.type = UnitType.valueOf(in.readString());
        this.name = in.readString();
        this.rate = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull @lombok.NonNull final Parcel dest, final int flags) {
        dest.writeString(this.type.name());
        dest.writeString(this.name);
        dest.writeDouble(this.rate);
    }

    public static Unit create() {
        final UnitType unitType = Components.getInstance()
            .getConfigComponent()
            .userConfigurations()
            .getUnitType();

        switch (unitType) {
            case AREA:
                return Constants.DEFAULT_AREA;

            case LENGTH:
                return Constants.DEFAULT_LENGTH;

            case TIME:
                return Constants.DEFAULT_TIME;

            case VOLUME:
                return Constants.DEFAULT_VOLUME;

            case WEIGHT:
                return Constants.DEFAULT_WEIGHT;

            case NONE:
            default:
                return Unit.NONE;
        }
    }
}
