package org.exidehelper.exercismWrapperService;

import org.apache.commons.cli.MissingArgumentException;
import org.exidehelper.GroupArgs.ConfigCommandFlags;
import org.exidehelper.GroupArgs.DownloadFlags;
import org.exidehelper.GroupArgs.GlobalFlags;
import org.exidehelper.exceptions.NoExercismTrackException;

import java.io.IOException;

public interface IExercismAPIWrapperService {

    String workspace() throws IOException, InterruptedException;
    String configure(GlobalFlags globalFlags, ConfigCommandFlags configCommandFlags) throws IOException, InterruptedException;

    String download(GlobalFlags gf, DownloadFlags df) throws IOException, InterruptedException, MissingArgumentException, NoExercismTrackException;
}
