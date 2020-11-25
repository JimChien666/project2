package com.iii.eeit124.shopping.service;

import com.iii.eeit124.entity.Orders;

public interface CreateOrderService {

	boolean saveOrder(Orders order);

	void updateOrderIsPaid(String merchantTradeNo);

	void updateOrderStatus(String merchantTradeNo, String status);

}
