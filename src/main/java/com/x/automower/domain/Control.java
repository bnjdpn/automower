package com.x.automower.domain;

import java.util.function.Function;

public enum Control {

    L(position -> new Position(position.x, position.y, position.orientation.getNextOrientationCounterclockwise())),

    R(position -> new Position(position.x, position.y, position.orientation.getNextOrientationClockwise())),

    F(position -> position.orientation.moveForwardPositionFunction.apply(position));

    public final Function<Position, Position> newPositionFunction;

    Control(Function<Position, Position> newPositionFunction) {
        this.newPositionFunction = newPositionFunction;
    }
}
