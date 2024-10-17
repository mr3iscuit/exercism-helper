package org.exidehelper.appConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.exidehelper.appConfig.AppConfigModels.ConfigModel;
import org.exidehelper.appConfig.AppConfigModels.EditorPathConfigModel;
import org.exidehelper.exceptions.NoExercismTrackException;

import java.io.File;
import java.io.IOException;

public class ConfigService implements IConfigService {
    @Override
    public String getExercismExecutable() {
        ConfigModel config = loadConfig();

        return config.getExercismExecutablePath();
    }

    @SneakyThrows
    private ConfigModel loadConfig() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(
                    new File(System.getProperty("user.home") + "/.config/exercism-helper/config.json"), ConfigModel.class);
        } catch (JsonProcessingException e) {
            return new ConfigModel();
        }
    }

    @Override
    public void setExercismExecutablePath(String path) {
        ConfigModel configModel = loadConfig();
        configModel.setExercismExecutablePath(path);
    }

    @Override
    public void setEditorPath(String track, String editorPath) throws IOException {
        ConfigModel configModel = loadConfig();
        EditorPathConfigModel editorPathConfig = configModel.getEditorConfig().getEditorPathConfig();

        switch (track) {
            case "java":
                editorPathConfig.setJava(editorPath);
                break;
            case "cpp":
                editorPathConfig.setCpp(editorPath);
                break;
            case "python":
                editorPathConfig.setPython(editorPath);
                break;
            case "rust":
                editorPathConfig.setRust(editorPath);
                break;
        }

        writeConfig(configModel);
    }

    @Override
    public String getEditorPath(String track) throws NoExercismTrackException {
        ConfigModel configModel = loadConfig();
        EditorPathConfigModel editorPath = loadConfig().getEditorConfig().getEditorPathConfig();

        switch (track) {
            case "java" : return editorPath.getJava();
            case "cpp" : return editorPath.getCpp();
            case "python" : return editorPath.getPython();
            case "rust" : return editorPath.getRust();
            default: throw new NoExercismTrackException("There no exercism track: " + track);
        }

    }

    @Override
    public void editorOnDownload(Boolean onDownload) throws IOException {

        ConfigModel configModel = loadConfig();
        configModel.getEditorConfig().setOpenOnDownload(onDownload);
        writeConfig(configModel);
    }

    private void writeConfig(ConfigModel configModel) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(System.getProperty("user.home") + "/.config/exercism-helper/config.json"), configModel);
    }
}