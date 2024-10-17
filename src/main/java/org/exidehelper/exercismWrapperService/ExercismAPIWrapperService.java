package org.exidehelper.exercismWrapperService;

import org.apache.commons.cli.MissingArgumentException;
import org.exidehelper.CommandExecutor;
import org.exidehelper.GroupArgs.ConfigCommandFlags;
import org.exidehelper.GroupArgs.DownloadFlags;
import org.exidehelper.GroupArgs.GlobalFlags;
import org.exidehelper.appConfig.ConfigService;
import org.exidehelper.appConfig.IConfigService;
import org.exidehelper.exceptions.NoExercismTrackException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExercismAPIWrapperService implements IExercismAPIWrapperService {

    IConfigService configService;
    private ArrayList<String> command;

    public ExercismAPIWrapperService(IConfigService configService) {
        this.configService = configService;
        this.command = new ArrayList<String>(List.of(configService.getExercismExecutable()));
    }

    @Override
    public String workspace() throws IOException, InterruptedException {

        List<String> command = List.of(new String[]{
                configService.getExercismExecutable(),
                "workspace",
        }).stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());

        return CommandExecutor.executeCommand(command);
    }

    @Override
    public String runCommand() throws IOException, InterruptedException {

        String res;

        try {
            res = CommandExecutor.executeCommand(command);
        }

        catch (IOException e) {
            throw new RuntimeException(
                    "Could not execute command %s".formatted(
                        command
                            .stream()
                            .collect(
                                    Collectors.joining(" ")
                            )
                    ),
                    e
            );
        }

        command.clear();
        return res;
    }

    @Override
    public String download(GlobalFlags gf, DownloadFlags df) throws IOException, InterruptedException, MissingArgumentException, NoExercismTrackException {

        if(df.getExercise() == null && df.getUuid() == null) {
            throw new MissingArgumentException("Error: need an --exercise name or a solution --uuid");
        }

        List<String> command = List.of(new String[]{
                configService.getExercismExecutable(),

                gf.getUnmaskToken() ? "--unmask-token" : "",
                gf.getVerbose() ? "--verbose" : "",

                gf.getTimeout() != null ? "--timeout" : "",
                gf.getTimeout() != null ? String.valueOf(gf.getTimeout()) : "",

                "download",

                df.getExercise() != null ? "--exercise" : "",
                df.getExercise() != null ? df.getExercise() : "",

                df.getForce() ? "--force" : "",
                df.getForce() ? "--help" : "",

                df.getTeam() != null ? "--team" : "",
                df.getTeam() != null ? df.getTeam() : "",

                df.getTrack() != null ? "--track" : "",
                df.getTrack() != null ? df.getTrack() : "",

                df.getUuid() != null ? "--track" : "",
                df.getUuid() != null ? df.getUuid() : "",

        }).stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());

        System.out.println(
            CommandExecutor.executeCommand(command)
        );

        String exercisePath = "%s/%s/%s".formatted(this.workspace().strip(), df.getTrack(), df.getExercise());

        CommandExecutor.executeCommand(List.of(new String[]{
                configService.getEditorPath(df.getTrack()),
                exercisePath
        }));

        return exercisePath;
    }

    @Override
    public String configure(GlobalFlags gf, ConfigCommandFlags cf) throws IOException, InterruptedException {

        List<String> command = List.of(new String[]{
                configService.getExercismExecutable(),
                gf.getUnmaskToken() ? "--unmask-token" : "",
                gf.getVerbose() ? "--verbose" : "",

                gf.getTimeout() != null ? "--timeout" : "",
                gf.getTimeout() != null ? String.valueOf(gf.getTimeout()) : "",

                "configure",

                cf.getHelp() ? "--help" : "",
                cf.getNoVerify() ? "--no-verify" : "",
                cf.getShow() ? "--show" : "",

                cf.getApi() != null ? "--api" : "",
                cf.getApi() != null ? cf.getApi() : "",

                cf.getToken() != null ? "--token" : "",
                cf.getToken() != null ? cf.getToken() : "",

                cf.getWorkspace() != null ? "--workspace" : "",
                cf.getWorkspace() != null ? cf.getWorkspace() : "",

        }).stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());

        return CommandExecutor.executeCommand(command);
    }
}
