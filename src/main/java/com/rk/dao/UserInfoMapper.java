package com.rk.dao;

import com.rk.entity.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */

public interface UserInfoMapper {
    List<UserInfo> queryAll();

    UserInfo queryByAccount(@Param("account") String account);

    int updateUserLoginDate(@Param("id") long id, @Param("date") Date date);
}
