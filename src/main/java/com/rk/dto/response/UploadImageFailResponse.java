package com.rk.dto.response;

/**
 * Created by Qin_Yikai on 2018-09-30.
 */
public class UploadImageFailResponse {
    private int error;
    private String message;

    public int getError() {
        return error;
    }

    public UploadImageFailResponse(int error, String message) {
        this.error = error;
        this.message = message;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
