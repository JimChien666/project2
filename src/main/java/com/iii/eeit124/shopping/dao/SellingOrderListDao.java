package com.iii.eeit124.shopping.dao;

import java.util.List;

import com.iii.eeit124.entity.Orders;

public interface SellingOrderListDao {

	List<Orders> indAllOrdersByMemberId(Integer pageNo, Integer recordsPerPage, Integer id, Integer paidStatus,
			String orderStatus);

	Long getRecordCounts(Integer id, Integer paidStatus, String orderStatus);

	List<Orders> indAllOrdersByMemberId(Integer pageNo, Integer recordsPerPage, Integer id);

	Long getRecordCounts(Integer id);

	Integer updateOrderStatus(Integer orderItemId);

}
