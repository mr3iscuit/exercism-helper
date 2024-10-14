package org.exidehelper.exercismWrapperService;

import java.io.IOException;

public interface IExercismAPIWrapperService {

    String workspace() throws IOException, InterruptedException;
    String configure(GlobalFlags globalFlags, ConfigCommandFlags configCommandFlags) throws IOException, InterruptedException;

    String runCommand() throws IOException, InterruptedException;

    String download(GlobalFlags gf, DownloadFlags df) throws IOException, InterruptedException;
}
