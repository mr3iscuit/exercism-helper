package org.exidehelper.commands;

import org.exidehelper.exercismWrapperService.ConfigCommandFlags;
import org.exidehelper.exercismWrapperService.GlobalFlags;
import org.exidehelper.exercismWrapperService.IExercismAPIWrapperService;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.IOException;

@Command(name = "configure", description = "Configuration related operations")
public class ConfigCommand implements Runnable {
    private final IExercismAPIWrapperService exercismAPIWrapperService;

    // Global Flags
    @CommandLine.Option(names = {"--timeout"}, description = "override the default HTTP timeout (seconds)")
    private Integer timeout;

    @CommandLine.Option(names = {"--unmask-token"}, description = "will unmask the API during a request/response dump")
    private boolean unmaskToken;

    @CommandLine.Option(names = {"--verbose"}, description = "verbose output")
    private boolean verbose;


    // Flags
    @CommandLine.Option(names = {"--show", "-s"}, description = "show the current configuration")
    private boolean show;

    @CommandLine.Option(names = {"--help", "-h"}, description = "help for configure")
    private boolean help;

    @CommandLine.Option(names = {"--no-verify"}, description = "skip online token authorization check")
    private boolean noVerify;


    @CommandLine.Option(names = {"--workspace", "-w"}, description = "directory for exercism workspace")
    private String workspace;

    @CommandLine.Option(names = {"--token", "-t"}, description = "authentication token used to connect to the site")
    private String token;

    @CommandLine.Option(names = {"--api", "-a"}, description = "API base url")
    private String api;


    public ConfigCommand(IExercismAPIWrapperService apiWrapperService) {
        this.exercismAPIWrapperService = apiWrapperService;
    }

    @Override
    public void run() {

        GlobalFlags globalFlags = new GlobalFlags(
                timeout,
                unmaskToken,
                verbose
        );

        ConfigCommandFlags configCommandFlags = new ConfigCommandFlags(
                show,
                help,
                noVerify,

                workspace,
                token,
                api
        );

        try {
            System.out.print(
                    exercismAPIWrapperService.configure(globalFlags, configCommandFlags)
            );
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
