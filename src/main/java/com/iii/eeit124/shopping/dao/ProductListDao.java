package com.iii.eeit124.shopping.dao;

import java.util.List;

import com.iii.eeit124.entity.Products;

public interface ProductListDao {
	List<Products> findAllProducts();

	List<Products> getPageProducts(Integer pageNo);

	Integer getTotalPages();

	Products getProduct(Integer productId);
}
