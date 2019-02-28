package com.rk.controller;

import com.rk.dto.response.ReturnResult;
import com.rk.dto.request.SignInRequest;
import com.rk.entity.UserInfo;
import com.rk.service.interfaces.UserInfoService;
import com.rk.util.EncryptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */
@Controller
public class UserController extends BaseController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String Login() {
        return "login";
    }

    @RequestMapping(value = "/user/signin", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseBody()
    public ReturnResult SignIn(@RequestBody SignInRequest request, HttpSession session) {
        UserInfo userInfo = userInfoService.getUser(request.getAccount());
        //密码校验
        if (userInfo != null && userInfo.getPassword().equals(EncryptionUtils.encryptAES(request.getPassword()))) {
            session.setAttribute("USER", userInfo);
            userInfoService.updateUserLoginDate(userInfo.getId(), new Date());
            return ReturnResult.Success("登录成功");
        }

        return ReturnResult.Error("登录失败");
    }

    @RequestMapping(value = "/user/signout", method = RequestMethod.GET)
    public String SignOut(HttpSession session) {
        session.invalidate();
        return ("redirect:/");
    }
}
