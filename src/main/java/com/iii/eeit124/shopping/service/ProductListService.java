package com.iii.eeit124.shopping.service;

import java.util.List;

import com.iii.eeit124.entity.Products;

public interface ProductListService {
	List<Products> findAllProducts();
	List<Products> getPageProducts(Integer pageNo,Integer recordsPerPage);
	Integer getTotalPages(Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage);
	Integer getTotalPages(Integer recordsPerPage);
	Products getProduct(Integer productId);
	Long getRecordCounts(Integer colorId, Integer categoryId, Integer animalTypeId);
	Long getRecordCounts();
	List<Products> getPageProducts(Integer pageNo, Integer colorId, Integer categoryId, Integer animalTypeId,
			Integer recordsPerPage);
}
