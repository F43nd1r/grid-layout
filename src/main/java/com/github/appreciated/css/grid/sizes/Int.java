package com.github.appreciated.css.grid.sizes;

import com.github.appreciated.css.grid.interfaces.MinMaxUnit;
import com.github.appreciated.css.grid.interfaces.RowOrColUnit;
import com.github.appreciated.css.grid.interfaces.TemplateRowsAndColsUnit;

public class Int implements RowOrColUnit, MinMaxUnit, TemplateRowsAndColsUnit {
    private int value;

    public Int(int value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return String.valueOf(value);
    }
}
