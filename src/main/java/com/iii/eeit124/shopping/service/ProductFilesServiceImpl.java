package com.iii.eeit124.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.entity.ProductFiles;
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.dao.ProductFilesDao;
import com.iii.eeit124.shopping.dao.ProductListDao;

@Service
public class ProductFilesServiceImpl implements ProductFilesService {

	@Autowired
	ProductFilesDao productFilesDao;
	
//	@Override
//	public List<Products> findAllProducts() {
//		
//		return dao.findAllProducts();
//	}
//
//	public List<Products> getPageProducts(Integer pageNo,Integer recordsPerPage){
//		return dao.getPageProducts(pageNo,recordsPerPage);
//	}
//
//	@Override
//	public Integer getTotalPages(Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage) {
//		return dao.getTotalPages(colorId, categoryId, animalTypeId,recordsPerPage);
//	}

	@Override
	public ProductFiles getProductFiles(Integer Id) {
		return productFilesDao.getProductFiles(Id);
	}

//	@Override
//	public List<Products> getPageProducts(Integer pageNo, Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage) {
//		return dao.getPageProducts(pageNo, colorId, categoryId, animalTypeId,recordsPerPage);
//	}
//
//	@Override
//	public Integer getTotalPages(Integer recordsPerPage) {
//		
//		return dao.getTotalPages( recordsPerPage);
//	}
//
//	@Override
//	public Long getRecordCounts(Integer colorId, Integer categoryId, Integer animalTypeId) {
//		return dao.getRecordCounts(colorId, categoryId, animalTypeId);
//	}
//
//	@Override
//	public Long getRecordCounts() {
//		return dao.getRecordCounts();
//	}


}
