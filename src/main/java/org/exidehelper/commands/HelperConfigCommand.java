package org.exidehelper.commands;

import org.exidehelper.appConfig.IConfigService;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(name = "editorconfig", mixinStandardHelpOptions = true, description = "configure exercism helper")
public class HelperConfigCommand implements Runnable {

    private final IConfigService configService;

    @CommandLine.Option(names = {"--track"}, description = "exercism track ID")
    private String track;

    @CommandLine.Option(names = {"--editor-path"}, description = "Editor executable path")
    private String editorPath;

    @CommandLine.Option(names = {"--on-download"}, description = "Open IDE by default on donwload")
    private Boolean onDownload;

    public HelperConfigCommand(IConfigService configService) {
        this.configService = configService;
    }

    @Override
    public void run() {
        if (track != null && editorPath != null) {
            try {
                configService.setEditorPath(track, editorPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (onDownload != null) {
            try {
                configService.editorOnDownload(onDownload);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}