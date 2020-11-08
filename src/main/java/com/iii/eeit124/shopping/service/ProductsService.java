package com.iii.eeit124.shopping.service;

import java.sql.Blob;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.iii.eeit124.entity.Products;
import com.iii.eeit124.shopping.dao.ProductsDAO;

@Service
public class ProductsService {

	private ProductsDAO pDao;
	@Autowired
	public ProductsService(ProductsDAO pDao) {
		this.pDao=pDao;		
	}
	
	public Products insert(Products prducts) {		
		return pDao.insert(prducts);
	}
	public Products select(Integer id) {
		return pDao.select(id); 
	}	
	
	public List<Products> selectById(Integer id) {
		return pDao.selectById(id);		
	}
	
	public List<Products> selectByName(String keyword) {
		return pDao.selectByName(keyword);		
	}
	
	public List<Products> selectAll() {
		return pDao.selectAll();
	}
	
	public Products update(Products products) {		
		return pDao.update(products);
	}
	public boolean delete(Integer id) {		
		return pDao.delete(id);
	}
	
}
