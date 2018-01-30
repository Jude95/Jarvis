package com.jude.jarvis_core.exception;

/**
 * Created by Jude on 2017/7/28.
 */

public class IllegalClassParameterException extends RuntimeException{

    public IllegalClassParameterException() {
        super();
    }

    public IllegalClassParameterException(String message) {
        super(message);
    }

    public IllegalClassParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
