package com.rk.service.interfaces;

import com.rk.entity.UserInfo;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */
public interface UserInfoService {
    List<UserInfo> getAllUsers();
    UserInfo getUser(String account);
    boolean updateUserLoginDate(long id, Date date);
}
