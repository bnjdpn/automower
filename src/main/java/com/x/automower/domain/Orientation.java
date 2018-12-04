package com.x.automower.domain;

import java.util.List;
import java.util.function.Function;

public enum Orientation {
    N(position -> new Position(position.x, position.y + 1, position.orientation)),
    S(position -> new Position(position.x, position.y - 1, position.orientation)),
    E(position -> new Position(position.x + 1, position.y, position.orientation)),
    W(position -> new Position(position.x - 1, position.y, position.orientation));

    public static final List<Orientation> ORIENTATIONS_BY_CLOCKWISE = List.of(N, E, S, W);

    protected final Function<Position, Position> moveForwardPositionFunction;

    Orientation(Function<Position, Position> moveForwardPositionFunction) {
        this.moveForwardPositionFunction = moveForwardPositionFunction;
    }

    public Orientation getNextOrientationClockwise() {
        var indexOfOrientation = ORIENTATIONS_BY_CLOCKWISE.indexOf(this);
        var newOrientationIndex = indexOfOrientation == ORIENTATIONS_BY_CLOCKWISE.size() - 1 ? 0 : indexOfOrientation + 1;
        return ORIENTATIONS_BY_CLOCKWISE.get(newOrientationIndex);
    }

    public Orientation getNextOrientationCounterclockwise() {
        var indexOfOrientation = ORIENTATIONS_BY_CLOCKWISE.indexOf(this);
        var newOrientationIndex = indexOfOrientation == 0 ? ORIENTATIONS_BY_CLOCKWISE.size() - 1 : indexOfOrientation - 1;
        return ORIENTATIONS_BY_CLOCKWISE.get(newOrientationIndex);
    }
}
