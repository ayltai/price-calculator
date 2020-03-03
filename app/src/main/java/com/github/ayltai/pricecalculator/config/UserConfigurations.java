package com.github.ayltai.pricecalculator.config;

import com.github.ayltai.pricecalculator.model.UnitType;

public interface UserConfigurations {
    boolean isDarkTheme();

    void setIsDarkTheme(boolean isDarkTheme);

    UnitType getUnitType();

    void setUnitType(UnitType unitType);
}
