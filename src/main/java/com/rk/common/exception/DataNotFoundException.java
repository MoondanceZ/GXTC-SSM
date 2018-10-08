package com.rk.common.exception;

import javax.servlet.ServletException;

/**
 * Created by Qin_Yikai on 2018-10-01.
 */
public class DataNotFoundException extends ServletException {
    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public DataNotFoundException(Throwable rootCause) {
        super(rootCause);
    }
}
