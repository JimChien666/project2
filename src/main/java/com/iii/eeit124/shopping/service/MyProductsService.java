package com.iii.eeit124.shopping.service;

import java.util.List;

import com.iii.eeit124.entity.FollowProducts;
import com.iii.eeit124.entity.Products;

public interface MyProductsService {
	List<Products> getPageProducts(Integer pageNo,Integer recordsPerPage,Integer memberId);
	Integer getTotalPages(Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage,String keywordSearch,Integer memberId);
	Integer getTotalPages(Integer recordsPerPage,Integer memberId);
	Products getProduct(Integer productId,Integer memberId);
	Long getRecordCounts(Integer colorId, Integer categoryId, Integer animalTypeId,String keywordSearch,Integer memberId);
	Long getRecordCounts(Integer memberId);
	List<Products> getPageProducts(Integer pageNo, Integer colorId, Integer categoryId, Integer animalTypeId,
			Integer recordsPerPage,String keywordSearch,Integer memberId);
	List<Products> selectByName(String keyword);
	Integer changeLikeStatus(Integer productId, Integer memberId);
	void saveFollowProduct(FollowProducts followProduct);
	void updateFollowProductStatus(FollowProducts followProduct);
	FollowProducts getFollowProduct(Integer productId, Integer memberId);
	List<FollowProducts> getLikeProduct(Integer id);
	List<Products> getLikeProductList(Integer memberId);
	List<Products> findMyProducts(Integer memberId,String keywordSearch);
	List<Products> findMyProducts(Integer memberId);
}
