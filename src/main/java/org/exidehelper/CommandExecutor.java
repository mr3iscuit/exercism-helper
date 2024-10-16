package org.exidehelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;

public class CommandExecutor {

    public static String executeCommand(List<String> command) throws IOException, InterruptedException {
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
            throw new IOException("Error: CommandExecutor error - %s with exit code: %d".formatted(output.toString(), exitCode));
        }

        return output.toString();
    }
}
