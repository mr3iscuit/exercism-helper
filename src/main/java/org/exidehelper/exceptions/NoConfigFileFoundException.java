package org.exidehelper.exceptions;

public class NoConfigFileFoundException extends Exception {
    public NoConfigFileFoundException(String formatted, Exception e) {
        super(formatted, e);
    }
}
