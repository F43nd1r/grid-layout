package com.github.appreciated.grid.vaadin;

import com.vaadin.flow.component.*;
import com.vaadin.flow.component.orderedlayout.ThemableLayout;

import java.util.Arrays;

@Tag("div")
public class GridLayout extends Component implements HasStyle, HasOrderedComponents<Component>, HasSize, GridLayoutComponent, ThemableLayout {

    private static final String spacing = "10px";
    private String[] rows;
    private String[] columns;

    /**
     * @param components
     */
    public GridLayout(Component... components) {
        this();
        this.add(components);
    }

    public GridLayout() {
        getStyle()
                .set("display", "grid")
                .set("grid-gap", spacing);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        getElement().callFunction("setAttribute", "style", "display:-ms-grid; " + getElement().getAttribute("style"));
    }

    /**
     * @param columns
     */
    public void setGridColumns(String... columns) {
        this.columns = columns;
        getStyle()
                .set("grid-auto-rows", "200px")
                .set("-ms-grid-columns", Arrays.stream(columns).reduce((s, s2) -> s + " " + spacing + " " + s2).orElse("")) // adding extra rows under ie to support spacing
                .set("grid-template-columns", Arrays.stream(columns).reduce((s, s2) -> s + " " + s2).orElse(""));
    }

    /**
     * @param rows
     */
    public void setGridRows(String... rows) {
        this.rows = rows;
        getStyle()
                .set("grid-auto-rows", "200px")
                .set("-ms-grid-rows", Arrays.stream(rows).reduce((s, s2) -> s + " " + spacing + " " + s2).orElse(""));
    }

    @Override
    public void setSpacing(boolean spacing) {
        ThemableLayout.super.setSpacing(spacing);
    }

    public void setSpan(Component component, int colFrom, int rowFrom, int colTo, int rowTo) {
        setColumnSpan(component, colFrom, colTo);
        setRowSpan(component, rowFrom, rowTo);
    }

    public void setColumnSpan(Component component, int from, int to) {
        component.getElement().getStyle()
                .set("grid-column", from + "/" + to)
                .set("-ms-grid-column", String.valueOf(from + (from - 1))) // adding extra columns under IE for spacing
                .set("-ms-grid-column-span", String.valueOf((to - from) + ((to - from) - 1))); // adding extra columns under IE for spacing
    }

    public void setRowSpan(Component component, int from, int to) {
        component.getElement().getStyle()
                .set("grid-row", from + "/" + to)
                .set("-ms-grid-row", String.valueOf(from + (from - 1))) // adding extra rows under IE for spacing
                .set("-ms-grid-row-span", String.valueOf((to - from) + ((to - from) - 1))); // adding extra rows under IE for spacing
    }

    public void setPosition(Component component, int row, int col) {
        setColumnSpan(component, col, col);
        setRowSpan(component, row, row);
    }
}
