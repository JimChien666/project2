package com.iii.eeit124.shopping.dao;

import java.util.List;

import com.iii.eeit124.entity.Orders;

public interface OrderListDao {

	List<Orders> indAllOrdersByMemberId(Integer pageNo,Integer recordsPerPage,Integer id);

	Long getRecordCounts();

}
