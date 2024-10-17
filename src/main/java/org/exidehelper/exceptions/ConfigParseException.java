package org.exidehelper.exceptions;

public class ConfigParseException extends Throwable {
    public ConfigParseException(String s, Exception e) {
        super(s, e);
    }
}
