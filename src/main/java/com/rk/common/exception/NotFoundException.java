package com.rk.common.exception;

import javax.servlet.ServletException;

/**
 * Created by Qin_Yikai on 2018-10-01.
 */
public class NotFoundException extends ServletException {
    public NotFoundException() {
        super("HTTP 404 Not Found");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public NotFoundException(Throwable rootCause) {
        super(rootCause);
    }
}
