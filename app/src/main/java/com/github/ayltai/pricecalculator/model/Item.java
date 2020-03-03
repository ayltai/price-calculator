package com.github.ayltai.pricecalculator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import lombok.Getter;
import lombok.Setter;

public final class Item implements Comparable<Item>, Parcelable {
    //region Variables

    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private int quantity = 1;

    @Getter
    @Setter
    private double size;

    @Getter
    @Setter
    @NonNull
    private Unit unit;

    @Getter
    @Setter
    private Item bestItem;

    //endregion

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @NonNull
        @Override
        public Item createFromParcel(@NonNull @lombok.NonNull final Parcel in) {
            return new Item(in);
        }

        @NonNull
        @Override
        public Item[] newArray(final int size) {
            return new Item[size];
        }
    };

    private Item(@NonNull @lombok.NonNull final Unit unit) {
        this.unit = unit;
    }

    protected Item(@NonNull @lombok.NonNull final Parcel in) {
        this.price    = in.readDouble();
        this.quantity = in.readInt();
        this.size     = in.readDouble();
        this.unit     = Objects.requireNonNull(in.readParcelable(Unit.class.getClassLoader()));
        this.bestItem = in.readParcelable(Item.class.getClassLoader());
    }

    public boolean isValid() {
        return this.price > 0 && this.quantity > 0 && this.size > 0;
    }

    public boolean isBest() {
        return this == this.bestItem;
    }

    public double getUnitPrice() {
        return this.quantity * this.size > 0 ? this.price / (this.quantity * this.size / this.unit.getRate()) : 0;
    }

    public double getDiff() {
        if (this.bestItem == null || this.isBest()) return 0;

        final double unitPrice = this.bestItem.getUnitPrice();
        if (unitPrice > 0) return this.getUnitPrice() / unitPrice - 1;

        return 0;
    }

    public void clear() {
        this.price    = 0;
        this.quantity = 1;
        this.size     = 0;
    }

    @Override
    public int compareTo(@Nullable final Item item) {
        return item == null ? -1 : Double.compare(this.getUnitPrice(), item.getUnitPrice());
    }

    @Override
    public boolean equals(@Nullable final Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        return this.compareTo((Item)object) == 0;
    }

    @Override
    public int hashCode() {
        return Double.valueOf(this.getUnitPrice()).hashCode();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull @lombok.NonNull final Parcel dest, final int flags) {
        dest.writeDouble(this.price);
        dest.writeInt(this.quantity);
        dest.writeDouble(this.size);
        dest.writeParcelable(this.unit, flags);
        dest.writeParcelable(this.bestItem, flags);
    }

    @NonNull
    public static Item create() {
        return new Item(Unit.create());
    }

    @Nullable
    public static Item findBest(@NonNull @lombok.NonNull final List<Item> items) {
        if (items.isEmpty()) return null;

        final List<Item> sortedItems = new ArrayList<>();
        sortedItems.clear();
        sortedItems.addAll(items);
        Collections.sort(sortedItems);

        final Item item = sortedItems.get(0);
        return item.isValid() ? item : null;
    }
}
