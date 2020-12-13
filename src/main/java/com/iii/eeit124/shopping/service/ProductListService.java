package com.iii.eeit124.shopping.service;

import java.util.List;

import com.iii.eeit124.entity.FollowProducts;
import com.iii.eeit124.entity.Products;

public interface ProductListService {
	List<Products> findAllProducts();
	List<Products> getPageProducts(Integer pageNo,Integer recordsPerPage);
	Integer getTotalPages(Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage,String keywordSearch);
	Integer getTotalPages(Integer recordsPerPage);
	Products getProduct(Integer productId);
	Long getRecordCounts(Integer colorId, Integer categoryId, Integer animalTypeId,String keywordSearch);
	Long getRecordCounts();
	List<Products> getPageProducts(Integer pageNo, Integer colorId, Integer categoryId, Integer animalTypeId,
			Integer recordsPerPage,String keywordSearch);
	List<Products> selectByName(String keyword);
	Integer changeLikeStatus(Integer productId, Integer memberId);
	void saveFollowProduct(FollowProducts followProduct);
	void updateFollowProductStatus(FollowProducts followProduct);
	FollowProducts getFollowProduct(Integer productId, Integer memberId);
	List<FollowProducts> getLikeProduct(Integer id);
	List<Products> getLikeProductList(Integer memberId);

}
