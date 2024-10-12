package org.exidehelper.exercismWrapperService;

import org.exidehelper.CommandExecutor;
import org.exidehelper.appConfig.ConfigService;

import java.io.IOException;

public class ExercismAPIWrapperService implements IExercismAPIWrapperService {

    ConfigService configService;

    public ExercismAPIWrapperService(ConfigService configService) {
        this.configService = configService;
    }

    @Override
    public String getWorkspace() {
        try {
            return CommandExecutor.executeCommand(new String[]{configService.getExercismExecutable(), "workspace"});
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String setWorkspace(String path) {
        try {
            return CommandExecutor.executeCommand(new String[]{configService.getExercismExecutable(), "configure", "--workspace", path});
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
