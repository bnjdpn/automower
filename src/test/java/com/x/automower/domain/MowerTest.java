package com.x.automower.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static com.x.automower.domain.Control.F;
import static com.x.automower.domain.Control.L;
import static com.x.automower.domain.Orientation.N;
import static org.assertj.core.api.Assertions.assertThat;

class MowerTest {

    @Test
    void should_move_to_expected_position() {
        // Given
        var lawn = new Lawn(5, 5);
        var position = new Position(1, 2, N);
        var mower = new Mower(lawn, position);
        var controls = List.of(L, F, L, F, L, F, L, F, F);

        // When
        var finalPosition = mower.execute(controls).getPosition();

        // Then
        assertThat(finalPosition).extracting(p -> p.x, p -> p.y, p -> p.orientation).containsExactly(1, 3, N);
    }

    @Test
    void should_not_move_outside_lawn() {
        // Given
        var lawn = new Lawn(0, 0);
        var position = new Position(0, 0, N);
        var mower = new Mower(lawn, position);
        var controls = List.of(F);

        // When
        var finalPosition = mower.execute(controls).getPosition();

        // Then
        assertThat(finalPosition).extracting(p -> p.x, p -> p.y, p -> p.orientation).containsExactly(0, 0, N);
    }
}
