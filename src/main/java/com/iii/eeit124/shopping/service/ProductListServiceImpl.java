package com.iii.eeit124.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
