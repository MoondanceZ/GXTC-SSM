package com.rk.service;

import com.rk.dao.CustomerMapper;
import com.rk.dto.response.ReturnResult;
import com.rk.entity.Customer;
import com.rk.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Thu Dec 13 19:56:20 CST 2018 Qin_Yikai
 */

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long> implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public ReturnResult disabled(Long[] ids) {
        if (customerMapper.disabled(ids) > 0) {
            return ReturnResult.Success("禁用成功");
        } else {
            return ReturnResult.Error("禁用失败");
        }
    }

    @Override
    public ReturnResult enabled(Long[] ids) {
        if (customerMapper.enabled(ids) > 0) {
            return ReturnResult.Success("启用成功");
        } else {
            return ReturnResult.Error("启用失败");
        }
    }
}

