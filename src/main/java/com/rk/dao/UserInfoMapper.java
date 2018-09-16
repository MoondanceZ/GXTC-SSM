package com.rk.dao;

import com.rk.entity.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */

public interface UserInfoMapper {
    List<UserInfo> queryAll();
}
