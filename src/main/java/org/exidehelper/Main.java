package org.exidehelper;
import org.exidehelper.appConfig.ConfigService;
import org.exidehelper.commands.ConfigCommand;
import org.exidehelper.commands.WorkspaceCommand;
import org.exidehelper.exercismWrapperService.ExercismAPIWrapperService;
import picocli.CommandLine;
import picocli.CommandLine.Command;


@Command()
public class Main implements Runnable {
    @Override
    public void run() {

    }
    public static void main(String[] args) {
        ConfigService configService = new ConfigService();
        ExercismAPIWrapperService exercismAPIWrapperService = new ExercismAPIWrapperService(configService);
        ConfigCommand configCommand = new ConfigCommand(exercismAPIWrapperService);

        WorkspaceCommand workspaceCommand = new WorkspaceCommand(exercismAPIWrapperService);

        int exitCode = new CommandLine(new Main())
                .addSubcommand("configure", configCommand)
                .addSubcommand("workspace", workspaceCommand)
                .execute(args);
        System.exit(exitCode);
    }
}