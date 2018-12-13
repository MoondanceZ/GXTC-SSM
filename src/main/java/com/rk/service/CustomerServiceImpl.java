package com.rk.service;

import com.rk.dao.CustomerMapper;
import com.rk.entity.Customer;
import com.rk.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Thu Dec 13 17:21:59 CST 2018 Qin_Yikai
 */ 

@Service
public class CustomerServiceImpl extends BaseServiceImpl<Customer, Long> implements CustomerService {
	@Autowired
	private CustomerMapper customerMapper;
}

