package org.exidehelper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.exidehelper.exceptions.ConfigFileIOException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    private ConfigModel config;

    private ObjectMapper objectMapper;
    private String configFilePath;

    Config () {
        String userHome = System.getProperty("user.home");
        this.configFilePath = userHome + "/.config/exercism-helper/config.json";
        this.objectMapper = new ObjectMapper();

        load();
    }

    public void load() {
        ConfigModel config;

        try(FileReader file = new FileReader(configFilePath)) {
            config = objectMapper.readValue(file, ConfigModel.class);
        } catch (IOException e) {
            throw new ConfigFileIOException(e);
        }

        this.config = config;
    }

    public Config save() {
        try(FileWriter file = new FileWriter(configFilePath)) {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, config);
        } catch (IOException e) {
            throw new ConfigFileIOException(e);
        }

        return this;
    }

    public Config setHome(String homeFolder) {

        this.config.setHomeFolder(homeFolder);
        return this;
    }

    public String getHome() {
        return config.getHomeFolder();
    }
}
