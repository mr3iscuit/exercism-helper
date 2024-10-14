package org.exidehelper.commands;

import lombok.SneakyThrows;
import org.exidehelper.appConfig.IConfigService;
import org.exidehelper.exercismWrapperService.IExercismAPIWrapperService;
import picocli.CommandLine;

@CommandLine.Command(name = "workspace", description = "print exercism workspace")
public class WorkspaceCommand implements Runnable {

    IConfigService configService;
    IExercismAPIWrapperService exercismAPIWrapperService;

    public WorkspaceCommand(IExercismAPIWrapperService exercismAPIWrapperService) {
        this.exercismAPIWrapperService = exercismAPIWrapperService;
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.print(
                exercismAPIWrapperService.workspace()
        );
    }
}
