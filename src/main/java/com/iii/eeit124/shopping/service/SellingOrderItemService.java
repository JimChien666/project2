package com.iii.eeit124.shopping.service;

import java.util.List;

import com.iii.eeit124.entity.OrderItems;

public interface SellingOrderItemService {

	List<OrderItems> getPageSellingOrderItems(Integer pageNo,Integer recordsPerPage,Integer memberId);

	Long getRecordCounts(Integer memberId);

}
