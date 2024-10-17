package org.exidehelper.appConfig;

import org.exidehelper.exceptions.NoExercismTrackException;

import java.io.IOException;

public interface IConfigService {
    String getExercismExecutable();
    void setEditorPath(String track, String editorPath) throws IOException;

    String getEditorPath(String track) throws NoExercismTrackException;

    void editorOnDownload(Boolean onDownload) throws IOException;
}