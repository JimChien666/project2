package com.iii.eeit124.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.entity.OrderItems;
import com.iii.eeit124.shopping.dao.SellingOrderItemDao;


@Service
public class SellingOrderItemServiceImpl implements SellingOrderItemService {

	@Autowired
	SellingOrderItemDao dao;
	
	@Override
	public List<OrderItems> getPageSellingOrderItems(Integer pageNo ,Integer recordsPerPage, Integer memberId) {
		return dao.getPageSellingOrderItems(pageNo, recordsPerPage, memberId);
	}

	@Override
	public Long getRecordCounts(Integer memberId) {
		
		return dao.getRecordCounts(memberId);
	}

}
