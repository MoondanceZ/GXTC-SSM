package com.rk.dto;

import com.fasterxml.jackson.databind.ser.Serializers;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */
public class ReturnResult<T> {
    //请求是否成功
    private boolean success;
    private T data;
    private String message;

    public ReturnResult(){
        data = null;
    }

    public static<T> ReturnResult Success(String message, T data){
        ReturnResult returnResult = new ReturnResult();
        returnResult.setData(data);
        returnResult.setMessage(message);
        returnResult.setSuccess(true);

        return  returnResult;
    }
    public static<T> ReturnResult Success(String message){
        ReturnResult returnResult = new ReturnResult();
        returnResult.setMessage(message);
        returnResult.setSuccess(true);

        return  returnResult;
    }

    public static<T> ReturnResult Error(String message, T data){
        ReturnResult returnResult = new ReturnResult();
        returnResult.setData(data);
        returnResult.setMessage(message);
        returnResult.setSuccess(false);

        return  returnResult;
    }

    public static<T> ReturnResult Error(String message){
        ReturnResult returnResult = new ReturnResult();
        returnResult.setMessage(message);
        returnResult.setSuccess(false);

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
