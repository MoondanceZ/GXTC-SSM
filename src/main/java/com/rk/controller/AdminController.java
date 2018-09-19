package com.rk.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Qin_Yikai on 2018-09-16.
 */
@Component
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping("/main")
    public String Main() {
        return "main";
    }

    @RequestMapping("/product")
    public String Product() {
        return "admin/product";
    }
}
