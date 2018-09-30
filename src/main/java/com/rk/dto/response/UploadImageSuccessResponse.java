package com.rk.dto.response;

/**
 * Created by Qin_Yikai on 2018-09-30.
 */
public class UploadImageSuccessResponse {
    public UploadImageSuccessResponse(int error, String url) {
        this.error = error;
        this.url = url;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private int error;
    private String url;
}
