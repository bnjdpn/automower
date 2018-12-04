package com.x.automower;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class AutoMowerApplicationIT {

    @Test
    void should_print_final_position() throws IOException {
        // Given
        var outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        var tempFile = File.createTempFile("input", ".txt");
        FileUtils.copyInputStreamToFile(AutoMowerApplicationIT.class.getResourceAsStream("/input.txt"), tempFile);

        // When
        AutoMowerApplication.main(new String[]{tempFile.getPath()});

        // Then
        var positions = outputStream.toString().split("\n");
        assertThat(positions).hasSize(2);
        assertThat(positions[0]).contains("1 3 N");
        assertThat(positions[1]).contains("5 1 E");
    }

}
