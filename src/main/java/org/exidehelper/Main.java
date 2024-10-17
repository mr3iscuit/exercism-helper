package org.exidehelper;
import org.exidehelper.appConfig.ConfigService;
import org.exidehelper.appConfig.IConfigService;
import org.exidehelper.commands.*;
import org.exidehelper.exercismWrapperService.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;


@Command()
public class Main implements Runnable {

    @Override
    public void run() {

    }
    public static void main(String[] args) {

        IConfigService configService = new ConfigService();
        IExercismAPIWrapperService exercismAPIWrapperService = new ExercismAPIWrapperService(configService);

        ConfigCommand configCommand = new ConfigCommand(exercismAPIWrapperService);
        DownloadCommand downloadcommand = new DownloadCommand(exercismAPIWrapperService);

        WorkspaceCommand workspaceCommand = new WorkspaceCommand(exercismAPIWrapperService);
        HelperConfigCommand helperConfigCommand = new HelperConfigCommand(configService);

        int exitCode = new CommandLine(new Main())
                .addSubcommand("workspace", workspaceCommand)
                .addSubcommand("configure", configCommand)
                .addSubcommand("download", downloadcommand)
                .addSubcommand("helper-config", helperConfigCommand)
                .execute(args);

        System.exit(exitCode);
    }
}