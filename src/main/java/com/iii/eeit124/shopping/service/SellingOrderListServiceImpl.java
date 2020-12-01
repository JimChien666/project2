package com.iii.eeit124.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.entity.Orders;
import com.iii.eeit124.shopping.dao.OrderListDao;
import com.iii.eeit124.shopping.dao.SellingOrderListDao;

@Service
public class SellingOrderListServiceImpl implements SellingOrderListService {

	@Autowired
	SellingOrderListDao dao;
	
	@Override
	public List<Orders> findAllOrdersByMemberId(Integer pageNo, Integer recordsPerPage, Integer id, Integer paidStatus,
			String orderStatus) {
		return dao.indAllOrdersByMemberId(pageNo,recordsPerPage,id, paidStatus, orderStatus);
	}

	@Override
	public Long getRecordCounts(Integer id, Integer paidStatus, String orderStatus) {
		return dao.getRecordCounts(id, paidStatus, orderStatus);
	}

	@Override
	public List<Orders> findAllOrdersByMemberId(Integer pageNo, Integer recordsPerPage, Integer id) {
		return dao.indAllOrdersByMemberId(pageNo,recordsPerPage,id);
	}

	@Override
	public Long getRecordCounts(Integer id) {
		return dao.getRecordCounts(id);
	}

	@Override
	public Integer updateOrderStatus(Integer orderItemId) {
		return dao.updateOrderStatus(orderItemId);
	}

}
