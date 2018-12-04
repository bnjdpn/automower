package com.x.automower;

import com.x.automower.domain.Position;

public class AutoMowerApplication {

    public static void main(String[] args) {
        new InstructionsParser(args[0])
                .readInstructions()
                .getControlsByMower()
                .forEach((mower, controls) -> {
                    Position finalPosition = mower.execute(controls).getPosition();
                    System.out.println(finalPosition.toString());
                });
    }
}
