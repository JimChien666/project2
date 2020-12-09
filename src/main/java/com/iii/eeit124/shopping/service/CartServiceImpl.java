package com.iii.eeit124.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.entity.CartItems;
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.dao.CartDao;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartDao dao;
	
	@Override
	public CartItems getCartItemInfo(Integer productId) {
		return dao.getCartItemInfo(productId);
	}

	@Override
	public Products getProduct(Integer productId) {
		return dao.getProduct(productId);
	}

}
