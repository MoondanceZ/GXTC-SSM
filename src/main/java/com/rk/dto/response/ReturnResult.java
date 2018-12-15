package com.rk.dto.response;

import java.io.Serializable;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */
public class ReturnResult<T> implements Serializable {
    //请求是否成功
    private boolean success;
    private T data;
    private String message;

    public ReturnResult() {
        super();
        data = null;
    }

    public ReturnResult(boolean success, T data, String message) {
        super();
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public ReturnResult(boolean success, String message) {
        super();
        this.success = success;
        this.data = null;
        this.message = message;
    }


    public static <T> ReturnResult Success(String message, T data) {
        ReturnResult returnResult = new ReturnResult(true, data, message);

        return returnResult;
    }

    public static ReturnResult Success(String message) {
        ReturnResult returnResult = new ReturnResult(true, message);

        return returnResult;
    }

    public static <T> ReturnResult Error(String message, T data) {
        ReturnResult returnResult = new ReturnResult(false, data, message);

        return  returnResult;
    }

    public static ReturnResult Error(String message) {
        ReturnResult returnResult = new ReturnResult(false, message);

        return  returnResult;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
