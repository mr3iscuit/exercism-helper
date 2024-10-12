package org.exidehelper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private ConfigModel config;

    private ObjectMapper objectMapper;
    private File configFile;

    ConfigManager() {
        String userHome = System.getProperty("user.home");
        this.configFile = new File(userHome, ".config/exercism-helper/config.json");

        load();
    }

    public void load() {
        ConfigModel config;

        try {
            config = objectMapper.readValue(configFile, ConfigModel.class);
        } catch (IOException e) {
            throw new ConfigFileIOException(e);
        }

        this.config = config;
    }

    public ConfigManager save() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(configFile, config);
        } catch (IOException e) {
            throw new ConfigFileIOException(e);
        }

        return this;
    }

    public ConfigManager setHome(String homeFolder) {

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(configFile, config);
        } catch (IOException e) {
            throw new ConfigFileIOException(e);
        }

        return this;
    }
}
