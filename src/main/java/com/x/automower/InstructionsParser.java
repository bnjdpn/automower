package com.x.automower;

import com.x.automower.domain.*;
import com.x.automower.exception.BadInstructionException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.StringUtils.SPACE;

public class InstructionsParser {

    private final String pathToInstructionFile;
    private Map<Mower, List<Control>> controlsByMower = new LinkedHashMap<>();

    public InstructionsParser(String pathToInstructionFile) {
        this.pathToInstructionFile = pathToInstructionFile;
    }

    public Map<Mower, List<Control>> getControlsByMower() {
        return controlsByMower;
    }

    public InstructionsParser readInstructions() {
        var instructions = getInstructionsStrings();

        var lawn = buildLawn(instructions.get(0));

        IntStream.iterate(1, i -> i < instructions.size(), i -> i + 2).forEach(i -> {
            var mower = buildMower(lawn, instructions.get(i));
            var controls = buildControls(instructions.get(i + 1));
            controlsByMower.put(mower, controls);
        });

        return this;
    }

    private List<String> getInstructionsStrings() {
        try {
            return FileUtils.readLines(new File(pathToInstructionFile), Charset.defaultCharset());
        } catch (IOException e) {
            throw new BadInstructionException(e);
        }
    }

    private Lawn buildLawn(String instruction) {
        var splitted = instruction.split(SPACE);
        var length = Integer.parseInt(splitted[0]);
        var height = Integer.parseInt(splitted[1]);
        return new Lawn(length, height);
    }

    private Mower buildMower(Lawn lawn, String instruction) {
        var splitted = instruction.split(SPACE);
        var x = Integer.parseInt(splitted[0]);
        var y = Integer.parseInt(splitted[1]);
        var orientation = Orientation.valueOf(splitted[2]);
        return new Mower(lawn, new Position(x, y, orientation));
    }

    private List<Control> buildControls(String instruction) {
        return instruction.chars()
                .mapToObj(control -> Control.valueOf(String.valueOf((char) control)))
                .collect(Collectors.toList());
    }
}
