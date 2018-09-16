package com.rk.controller;

import com.rk.entity.UserInfo;
import com.rk.service.interfaces.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */
@Component
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public String test(Model model){
        List<UserInfo> users = userInfoService.getAllUsers();
        model.addAttribute("list", users);
        model.addAttribute("test", "I'm test");
        return "test";
    }
}
