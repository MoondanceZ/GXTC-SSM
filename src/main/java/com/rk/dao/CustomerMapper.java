package com.rk.dao;

import com.rk.entity.Customer;

/**
 * Thu Dec 13 19:56:20 CST 2018 Qin_Yikai
 */ 

public interface CustomerMapper extends BaseMapper<Customer, Long> {
    int disabled(Long[] ids);
    int enabled(Long[] ids);
}

