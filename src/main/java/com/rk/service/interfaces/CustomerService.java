package com.rk.service.interfaces;

import com.rk.dto.ReturnResult;
import com.rk.entity.Customer;

/**
 * Thu Dec 13 19:56:20 CST 2018 Qin_Yikai
 */

public interface CustomerService extends BaseService<Customer, Long> {
    ReturnResult disabled(Long[] ids);
    ReturnResult enabled(Long[] ids);
}

