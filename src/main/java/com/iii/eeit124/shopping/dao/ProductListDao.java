package com.iii.eeit124.shopping.dao;

import java.util.List;

import com.iii.eeit124.entity.FollowProducts;
import com.iii.eeit124.entity.Products;

public interface ProductListDao {
	List<Products> findAllProducts();

	List<Products> getPageProducts(Integer pageNo,Integer recordsPerPage);

	Integer getTotalPages(Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage,String keywordSearch);

	Products getProduct(Integer productId);

	List<Products> getPageProducts(Integer pageNo, Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage,String keywordSearch);

	Integer getTotalPages(Integer recordsPerPage);

	Long getRecordCounts(Integer colorId, Integer categoryId, Integer animalTypeId,String keywordSearch);

	Long getRecordCounts();
	
	List<Products> selectByName(String keyword);

	Integer changeLikeStatus(Integer productId, Integer memberId);

	void updateFollowProductStatus(FollowProducts followProduct);

	void saveFollowProduct(FollowProducts followProduct);

	FollowProducts getFollowProduct(Integer productId, Integer memberId);

	List<FollowProducts> getLikeProduct(Integer memberId);

	List<Products> getLikeProductList(Integer memberId);
}
