package org.exidehelper.exceptions;

import java.io.IOException;

public class ConfigFileReadException extends Throwable {
    public ConfigFileReadException(String s, IOException e) {
        super(s, e);
    }
}
