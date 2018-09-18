package com.rk.controller;

import com.rk.dto.ReturnResult;
import com.rk.dto.request.SignInRequest;
import com.rk.entity.UserInfo;
import com.rk.service.interfaces.UserInfoService;
import com.rk.util.EncryptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.management.counter.Variability;

import javax.servlet.http.HttpSession;
import java.util.Date;

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

    @RequestMapping(value = "/signin", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody()
    public ReturnResult<UserInfo> SignIn(@RequestBody SignInRequest request, HttpSession session) {
        UserInfo userInfo = userInfoService.getUser(request.getAccount());
        //密码校验
        if (userInfo != null && userInfo.getPassword().equals(EncryptionUtil.encryptAES(request.getPassword()))) {
            session.setAttribute("user", userInfo);
            userInfoService.updateUserLoginDate(userInfo.getId(), new Date());
            return ReturnResult.Success("登录成功");
        }

        return ReturnResult.Error("登录失败");
    }
}
