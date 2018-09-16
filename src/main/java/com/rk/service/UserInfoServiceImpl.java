package com.rk.service;

import com.rk.dao.UserInfoMapper;
import com.rk.entity.UserInfo;
import com.rk.service.interfaces.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;


    /**
     *
     * @return
     */
    @Override
    public List<UserInfo> getAllUsers() {
        return userInfoMapper.queryAll();
    }
}
