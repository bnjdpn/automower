package com.x.automower.domain;

import java.util.List;
import java.util.Optional;

public class Mower {

    private final Lawn lawn;
    private Position position;

    public Mower(Lawn lawn, Position position) {
        this.lawn = lawn;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Mower execute(List<Control> controls) {
        controls.forEach(control ->
                position = Optional.of(control.newPositionFunction.apply(position))
                        .filter(this::isPositionIsInLawn).orElse(position));
        return this;
    }

    private boolean isPositionIsInLawn(Position position) {
        return position.x >= 0 && position.x <= lawn.length &&
                position.y >= 0 && position.y <= lawn.height;
    }
}
