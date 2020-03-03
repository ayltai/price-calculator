package com.github.ayltai.pricecalculator.util;

import com.github.ayltai.pricecalculator.UnitTest;
import com.github.ayltai.pricecalculator.model.Unit;
import com.github.ayltai.pricecalculator.model.UnitType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public final class ConverterTest extends UnitTest {
    @Test
    public void testIntegerToString() {
        assertEquals("Convert 0 to \"\"", "", Converter.integerToString(0));
        assertEquals("Convert 1 to \"1\"", "1", Converter.integerToString(1));
    }

    @Test
    public void testStringToInteger() {
        assertEquals("Convert \"\" to 0", 0, Converter.stringToInteger(""));
        assertEquals("Convert invalid string to 0", 0, Converter.stringToInteger("a"));
        assertEquals("Convert \"1\" to 1", 1, Converter.stringToInteger("1"));
    }

    @Test
    public void testDoubleToString() {
        assertEquals("Convert 0 to \"\"", "", Converter.doubleToString(0));
        assertEquals("Convert integer value without decimal point", "1", Converter.doubleToString(1));
        assertEquals("Convert double value with decimal point", "1.1", Converter.doubleToString(1.1));
    }

    @Test
    public void testStringToDouble() {
        assertEquals("Convert \"\" to 0", 0, Converter.stringToDouble(""), 0.01);
        assertEquals("Convert \"1\" to 1", 1, Converter.stringToDouble("1"), 0.01);
        assertEquals("Convert \"1.1\" to 1.1", 1.1, Converter.stringToDouble("1.1"), 0.01);
        assertEquals("Convert invalid string to 0", 0, Converter.stringToDouble("a"), 0.01);
    }

    @Test
    public void testUnitToString() {
        assertEquals("Convert gram to \"g\"", "g", Converter.unitToString(Unit.GRAM));
        assertEquals("Convert none to \"\"", "", Converter.unitToString(Unit.NONE));
    }

    @Test
    public void testStringToUnit() {
        assertEquals("Convert \"g\" to gram", Unit.GRAM, Converter.stringToUnit("g"));
        assertEquals("Convert invalid string to none", Unit.NONE, Converter.stringToUnit("a"));
    }

    @Test
    public void testUnitTypeToInteger() {
        assertEquals("Convert none to 0", 0, Converter.unitTypeToInteger(UnitType.NONE));
        assertEquals("Convert area to 1", 1, Converter.unitTypeToInteger(UnitType.AREA));
    }

    @Test
    public void testIntegerToUnitType() {
        assertEquals("Convert 0 to none", UnitType.NONE, Converter.integerToUnitType(0));
        assertEquals("Convert 1 to area", UnitType.AREA, Converter.integerToUnitType(1));
    }
}
