package com.iii.eeit124.shopping.service;

import java.util.List;

import com.iii.eeit124.entity.Orders;

public interface OrderListService {

	List<Orders> findAllOrdersByMemberId(Integer pageNo, Integer recordsPerPage, Integer id);

	Long getRecordCounts(Integer id);

}
