package com.iii.eeit124.shopping.service;

import java.util.List;

import com.iii.eeit124.entity.Products;

public interface ProductListService {
	List<Products> findAllProducts();
	List<Products> getPageProducts(Integer pageNo);
	Integer getTotalPages(Integer colorId, Integer categoryId, Integer animalTypeId);
	Integer getTotalPages();
	Products getProduct(Integer productId);
	List<Products> getPageProducts(Integer pageNo, Integer colorId, Integer categoryId, Integer animalTypeId);
	Long getRecordCounts(Integer colorId, Integer categoryId, Integer animalTypeId);
	Long getRecordCounts();
}
