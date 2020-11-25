package com.iii.eeit124.shopping.dao;

import java.util.List;

import com.iii.eeit124.entity.OrderItems;

public interface SellingOrderItemDao {

	List<OrderItems> getPageSellingOrderItems(Integer pageNo ,Integer recordsPerPage,Integer memberId);

	Long getRecordCounts(Integer memberId);

}
