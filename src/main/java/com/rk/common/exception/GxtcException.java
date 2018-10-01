package com.rk.common.exception;

import javax.servlet.ServletException;

/**
 * Created by Qin_Yikai on 2018-10-01.
 */
public class GxtcException extends ServletException {
    public GxtcException() {
    }

    public GxtcException(String message) {
        super(message);
    }

    public GxtcException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public GxtcException(Throwable rootCause) {
        super(rootCause);
    }
}
