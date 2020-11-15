package com.iii.eeit124.shopping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.entity.Orders;
import com.iii.eeit124.shopping.dao.CreateOrderDao;

@Service
public class CreateOrderServiceImpl implements CreateOrderService {

	@Autowired
	CreateOrderDao dao;
	
	@Override
	public boolean saveOrder(Orders order) {
		return dao.saveOrder(order);
	}

}
