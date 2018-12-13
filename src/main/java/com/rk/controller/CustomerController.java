package com.rk.controller;

import com.rk.dto.LayPage;
import com.rk.dto.ReturnResult;
import com.rk.dto.request.CustomerPageRequest;
import com.rk.entity.Customer;
import com.rk.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/pageList", method = RequestMethod.GET)
    @ResponseBody
    public LayPage<List<Customer>> ProductType(CustomerPageRequest pageRequest) {
        return customerService.getPageList(pageRequest);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody()
    public ReturnResult Delete(@RequestParam("ids[]") Long[] ids) {
        return customerService.delete(ids);
    }

    @RequestMapping(value = "/disabled")
    @ResponseBody()
    public ReturnResult Disabled(@RequestParam("ids[]") Long[] ids) {
        return customerService.disabled(ids);
    }

    @RequestMapping(value = "/enabled")
    @ResponseBody()
    public ReturnResult Enabled(@RequestParam("ids[]") Long[] ids) {
        return customerService.enabled(ids);
    }
}
