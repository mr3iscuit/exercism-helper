package org.exidehelper;
import org.apache.commons.cli.*;
import org.exidehelper.exceptions.NoArgumentsException;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();
        ConfigManager configManager = new ConfigManager();

        options.addOption(
                Option.builder("h")
                        .longOpt("set-home")
                        .argName("exercism home folder")
                        .hasArg()
                        .desc("Set home folder. Usually /home/<user>/exercism")
                        .build()
        );

        options.addOption(
                Option.builder("t")
                        .longOpt("default-track")
                        .argName("default track")
                        .hasArg()
                        .desc("Set default exercism track, For example java")
                        .build()
        );

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);

            if(args.length == 0) {
                throw new NoArgumentsException();
            }

            if(cmd.hasOption("set-home")) {
                String homeFolder = cmd.getOptionValue("set-home");
                configManager.setHome(homeFolder);
            }
        }
        catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("exercism-helper", options);
            System.exit(1);
        }
        catch (NoArgumentsException e) {
            formatter.printHelp("exercism-helper", options);
            System.exit(1);
        }
        catch (RuntimeException e) {
            System.out.println("Some error occured: " + e.getMessage());
        }
    }
}