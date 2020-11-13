package com.iii.eeit124.shopping.dao;

import java.util.List;

import com.iii.eeit124.entity.Products;

public interface ProductListDao {
	List<Products> findAllProducts();

	List<Products> getPageProducts(Integer pageNo,Integer recordsPerPage);

	Integer getTotalPages(Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage);

	Products getProduct(Integer productId);

	List<Products> getPageProducts(Integer pageNo, Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage);

	Integer getTotalPages(Integer recordsPerPage);

	Long getRecordCounts(Integer colorId, Integer categoryId, Integer animalTypeId);

	Long getRecordCounts();
}
