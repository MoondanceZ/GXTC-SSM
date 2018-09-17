package com.rk.dto.request;

import java.io.Serializable;

/**
 * Created by Qin_Yikai on 2018-09-17.
 */
public class SignInRequest implements Serializable {
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignInRequest() {
        super();
    }

    public SignInRequest(String account, String password) {
        super();
        this.account = account;
        this.password = password;
    }

    private String account;
    private String password;
}
