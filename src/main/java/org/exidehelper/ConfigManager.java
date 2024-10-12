package org.exidehelper;
import java.util.Objects;

public class ConfigManager {
    Config config = new Config();

    public void setHome(String homeFolder) {
        config.setHome(homeFolder).save().load();

        if(!Objects.equals(homeFolder, config.getHome())) {
            throw new RuntimeException("Error: Could not set configuration.");
        }
    }
}
