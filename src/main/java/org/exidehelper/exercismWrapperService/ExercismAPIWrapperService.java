package org.exidehelper.exercismWrapperService;

import org.exidehelper.CommandExecutor;
import org.exidehelper.appConfig.ConfigService;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ExercismAPIWrapperService implements IExercismAPIWrapperService {

    ConfigService configService;
    private ArrayList<String> command;

    public ExercismAPIWrapperService(ConfigService configService) {
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
    public String download(GlobalFlags gf, DownloadFlags df) throws IOException, InterruptedException {
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

        /*
        //TODO open IDE by default if setting is predefined.
        CommandExecutor.executeCommand(List.of(new String[]{
                "idea-community", //TODO load IDE from configuration for specific track
                exercisePath
        }));
        */

        return exercisePath;
    }

    @Override
    public String configure(GlobalFlags gf, ConfigCommandFlags cf) throws IOException, InterruptedException {
        List<String> command = List.of(new String[]{
                configService.getExercismExecutable(),
                gf.unmaskToken ? "--unmask-token" : "",
                gf.verbose ? "--verbose" : "",

                gf.timeout != null ? "--timeout" : "",
                gf.timeout != null ? String.valueOf(gf.timeout) : "",

                "configure",

                cf.help ? "--help" : "",
                cf.noVerify ? "--no-verify" : "",
                cf.show ? "--show" : "",

                cf.api != null ? "--api" : "",
                cf.api != null ? cf.api : "",

                cf.token != null ? "--token" : "",
                cf.token != null ? cf.token : "",

                cf.workspace != null ? "--workspace" : "",
                cf.workspace != null ? cf.workspace : "",

        }).stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());

        return CommandExecutor.executeCommand(command);
    }
}
