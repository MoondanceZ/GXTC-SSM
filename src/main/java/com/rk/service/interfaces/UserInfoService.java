package com.rk.service.interfaces;

import com.rk.entity.UserInfo;

import java.util.Date;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */
public interface UserInfoService {
    UserInfo getUser(String account);
    boolean updateUserLoginDate(long id, Date date);
}
