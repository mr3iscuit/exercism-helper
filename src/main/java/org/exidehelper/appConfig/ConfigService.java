package org.exidehelper.appConfig;

import java.util.Objects;

public class ConfigService implements IConfigService {
    Config config = new Config();

    @Override
    public String getExercismExecutable() {
        return config.getExercismExecutablePath();
    }

    @Override
    public void setExercismExecutablePath(String path) {
        config.setExercismExecutablePath(path).save().load();

        if(!Objects.equals(path, config.getExercismExecutablePath())) {
            throw new RuntimeException("Error: Could not set path to exercism binary.");
        }
    }
}
