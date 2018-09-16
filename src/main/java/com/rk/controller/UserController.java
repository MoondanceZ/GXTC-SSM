package com.rk.controller;

import com.rk.dto.ReturnResult;
import com.rk.entity.UserInfo;
import com.rk.service.interfaces.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */
@Component
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public String Login() {
        return "login";
    }

/*    @ModelAttribute
    public void userLoginModel(String account, String password, Model model) {
        model.addAttribute("account", account);
        model.addAttribute("password", password);
    }*/

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody()
    public ReturnResult<UserInfo> SignIn() {
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("123sadlf");
        return ReturnResult.Error("登录失败", userInfo);
    }
}
