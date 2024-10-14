package org.exidehelper.exceptions;

import java.io.IOException;

public class ConfigFileIOException extends RuntimeException {
    public ConfigFileIOException(IOException e) {
        super(e);
    }
}
