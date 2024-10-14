package org.exidehelper.exercismWrapperService;

import java.io.IOException;

public interface IExercismAPIWrapperService {

    IExercismAPIWrapperService workspace();
    String configure(GlobalFlags globalFlags, ConfigCommandFlags configCommandFlags) throws IOException, InterruptedException;

    String runCommand() throws IOException, InterruptedException;
}
