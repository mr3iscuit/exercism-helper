package org.exidehelper.commands;

import org.apache.commons.cli.MissingArgumentException;
import org.exidehelper.GroupArgs.DownloadFlags;
import org.exidehelper.GroupArgs.GlobalFlags;
import org.exidehelper.exceptions.NoExercismTrackException;
import org.exidehelper.exercismWrapperService.IExercismAPIWrapperService;
import picocli.CommandLine;

import java.io.IOException;

@CommandLine.Command(name = "download", mixinStandardHelpOptions = true,  description = "Download and exercise")
public class DownloadCommand implements Runnable {
    private final IExercismAPIWrapperService exercismAPIWrapperService;

    // Global Flags
    //TODO Use Commandline.ArgGroup with heading.
    @CommandLine.Option(names = {"--timeout"}, description = "override the default HTTP timeout (seconds)")
    private Integer timeout;

    @CommandLine.Option(names = {"--unmask-token"}, description = "will unmask the API during a request/response dump")
    private boolean unmaskToken;

    @CommandLine.Option(names = {"--verbose"}, description = "verbose output")
    private boolean verbose;

    // Download flagGroupArgumentss
    @CommandLine.Option(names = {"--force", "-F"}, description = "overwrite existing exercise directory")
    private boolean force;

//    @CommandLine.Option(names = {"--help", "-h"}, description = "help for download")
    private boolean help;

    @CommandLine.Option(names = {"--exercise", "-e"}, description = "the exercise slug")
    private String exercise;

    @CommandLine.Option(names = {"--track", "-t"}, description = "the track ID")
    private String track;

    @CommandLine.Option(names = {"--team", "-T"}, description = "the team slug")
    private String team;

    @CommandLine.Option(names = {"--uuid", "-u"}, description = "the solution UUID")
    private String uuid;

    public DownloadCommand(IExercismAPIWrapperService exercismAPIWrapperService) {
        this.exercismAPIWrapperService = exercismAPIWrapperService;
    }


    @Override
    public void run() {

        GlobalFlags gf = new GlobalFlags(
                timeout,
                unmaskToken,
                verbose
        );

        DownloadFlags df = new DownloadFlags(
            force,
            help,
            exercise,
            track,
            team,
            uuid
        );

        try {
            System.out.print(
                    exercismAPIWrapperService.download(gf, df)
            );
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (MissingArgumentException e) {
            System.out.println(e.getMessage());
        }
        catch (NoExercismTrackException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
