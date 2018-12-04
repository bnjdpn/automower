package com.x.automower.domain;

import static org.apache.commons.lang3.StringUtils.SPACE;

public class Position {

    public final int x;
    public final int y;
    public final Orientation orientation;

    public Position(int x, int y, Orientation orientation) {
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return x + SPACE + y + SPACE + orientation;
    }
}
