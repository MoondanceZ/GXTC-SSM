package com.rk.service;

import com.rk.dao.ShoppingCartMapper;
import com.rk.entity.ShoppingCart;
import com.rk.service.interfaces.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Sat Dec 15 17:05:46 CST 2018 Qin_Yikai
 */ 

@Service
public class ShoppingCartServiceImpl extends BaseServiceImpl<ShoppingCart, Long> implements ShoppingCartService {
	@Autowired
	private ShoppingCartMapper shoppingCartMapper;
}

