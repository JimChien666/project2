package com.iii.eeit124.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.entity.FollowProducts;
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.dao.ProductListDao;

@Service
public class ProductListServiceImpl implements ProductListService {

	@Autowired
	ProductListDao dao;
	
	@Override
	public List<Products> findAllProducts() {
		
		return dao.findAllProducts();
	}

	public List<Products> getPageProducts(Integer pageNo,Integer recordsPerPage){
		return dao.getPageProducts(pageNo,recordsPerPage);
	}

	@Override
	public Integer getTotalPages(Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage,String keywordSearch) {
		return dao.getTotalPages(colorId, categoryId, animalTypeId,recordsPerPage,keywordSearch);
	}

	@Override
	public Products getProduct(Integer productId) {
		return dao.getProduct(productId);
	}

	@Override
	public List<Products> getPageProducts(Integer pageNo, Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage,String keywordSearch) {
		return dao.getPageProducts(pageNo, colorId, categoryId, animalTypeId,recordsPerPage,keywordSearch);
	}

	@Override
	public Integer getTotalPages(Integer recordsPerPage) {
		
		return dao.getTotalPages( recordsPerPage);
	}

	@Override
	public Long getRecordCounts(Integer colorId, Integer categoryId, Integer animalTypeId,String keywordSearch) {
		return dao.getRecordCounts(colorId, categoryId, animalTypeId,keywordSearch);
	}

	@Override
	public Long getRecordCounts() {
		return dao.getRecordCounts();
	}

	public List<Products> selectByName(String keyword){
		return dao.selectByName(keyword);
	}

	@Override
	public Integer changeLikeStatus(Integer productId, Integer memberId) {
		return dao.changeLikeStatus(productId, memberId);
	}

	@Override
	public void saveFollowProduct(FollowProducts followProduct) {
		dao.saveFollowProduct(followProduct);
		
	}

	@Override
	public void updateFollowProductStatus(FollowProducts followProduct) {
		dao.updateFollowProductStatus(followProduct);
		
	}

	@Override
	public FollowProducts getFollowProduct(Integer productId, Integer memberId) {
		return dao.getFollowProduct(productId, memberId);
	}

	@Override
	public List<FollowProducts> getLikeProduct(Integer memberId) {
		return dao.getLikeProduct(memberId);
	}

	@Override
	public List<Products> getLikeProductList(Integer memberId) {
		return dao.getLikeProductList(memberId);
	}

	

}
