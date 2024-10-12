package org.exidehelper.commands;

import org.exidehelper.exercismWrapperService.IExercismAPIWrapperService;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "configure", description = "Configuration related operations")
public class ConfigCommand implements Runnable {

    private final IExercismAPIWrapperService exercismAPIWrapperService;
    @CommandLine.Option(names = {"--workspace", "-w"}, description = "Set the exercism workspace directory")
    private String workspace;


    public ConfigCommand(IExercismAPIWrapperService apiWrapperService) {
        this.exercismAPIWrapperService = apiWrapperService;
    }

    @Override
    public void run() {
        if (workspace != null) {
            exercismAPIWrapperService.setWorkspace(workspace);
        }
    }
}
