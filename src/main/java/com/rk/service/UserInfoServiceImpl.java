package com.rk.service;

import com.rk.dao.UserInfoMapper;
import com.rk.entity.UserInfo;
import com.rk.service.interfaces.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */
@Service
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo, Long> implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUser(String account) {
        return userInfoMapper.queryByAccount(account);
    }

    @Override
    public boolean updateUserLoginDate(long id, Date date) {
        return userInfoMapper.updateUserLoginDate(id, date) > 0;
    }
}
