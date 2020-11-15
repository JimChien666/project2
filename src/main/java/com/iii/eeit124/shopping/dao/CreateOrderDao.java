package com.iii.eeit124.shopping.dao;

import com.iii.eeit124.entity.Orders;

public interface CreateOrderDao {
	boolean saveOrder(Orders order);
}
