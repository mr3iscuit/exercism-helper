package org.exidehelper.commands;

import org.exidehelper.appConfig.IConfigService;
import org.exidehelper.exercismWrapperService.IExercismAPIWrapperService;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@CommandLine.Command(name = "workspace", description = "print exercism workspace")
public class WorkspaceCommand implements Runnable {
    IConfigService configService;
    IExercismAPIWrapperService exercismAPIWrapperService;

    public WorkspaceCommand(IExercismAPIWrapperService exercismAPIWrapperService) {
        this.exercismAPIWrapperService = exercismAPIWrapperService;
    }

    @Override
    public void run() {
        System.out.println(exercismAPIWrapperService.getWorkspace());
    }
}
