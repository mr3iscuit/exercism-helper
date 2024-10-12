package org.exidehelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CommandExecutor {

    public static String executeCommand(String... command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true); // Merge standard error with standard output

        Process process = processBuilder.start();
        StringBuilder output = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }
        }

        int exitCode = process.waitFor(); // Wait for the process to finish
        if (exitCode != 0) {
            throw new IOException("Command exited with non-zero status: " + exitCode);
        }

        return output.toString();
    }
}
