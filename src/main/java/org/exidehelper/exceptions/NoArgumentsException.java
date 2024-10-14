package org.exidehelper.exceptions;

public class NoArgumentsException extends RuntimeException {

    public NoArgumentsException() {
        super("No arguments");
    }

    public NoArgumentsException(String message) {
        super(message);
    }

}
