package com.iii.eeit124.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.entity.FollowProducts;
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.dao.MyProductsDao;
import com.iii.eeit124.shopping.dao.ProductListDao;

@Service
public class MyProductsServiceImpl implements MyProductsService {

	@Autowired
	MyProductsDao dao;
	public List<Products> getPageProducts(Integer pageNo,Integer recordsPerPage, Integer memberId){
		return dao.getPageProducts(pageNo,recordsPerPage,memberId);
	}

	@Override
	public Integer getTotalPages(Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage,String keywordSearch, Integer memberId) {
		return dao.getTotalPages(colorId, categoryId, animalTypeId,recordsPerPage,keywordSearch,memberId);
	}

	@Override
	public Products getProduct(Integer productId,Integer memberId) {
		return dao.getProduct(productId);
	}

	@Override
	public List<Products> getPageProducts(Integer pageNo, Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage,String keywordSearch,Integer memberId) {
		return dao.getPageProducts(pageNo, colorId, categoryId, animalTypeId,recordsPerPage,keywordSearch,memberId);
	}

	@Override
	public Integer getTotalPages(Integer recordsPerPage, Integer memberId) {
		
		return dao.getTotalPages(recordsPerPage,memberId);
	}

	@Override
	public Long getRecordCounts(Integer colorId, Integer categoryId, Integer animalTypeId,String keywordSearch,Integer memberId) {
		return dao.getRecordCounts(colorId, categoryId, animalTypeId,keywordSearch,memberId);
	}

	@Override
	public Long getRecordCounts(Integer memberId) {
		return dao.getRecordCounts(memberId);
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
	
	@Override
	public List<Products> findMyProducts(Integer memberId,String keywordSearch) {
		
		return dao.findMyProducts(memberId,keywordSearch);
	}
	
	@Override
	public List<Products> findMyProducts(Integer memberId) {		
		return dao.findMyProducts(memberId);
	}

}
