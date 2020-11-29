package com.iii.eeit124.shopping.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.shopping.dao.ShoppingAanlysisDao;

@Service
public class ShoppingAanlysisServiceImpl implements ShoppingAanlysisService {

	@Autowired
	ShoppingAanlysisDao dao;
	
	@Override
	public Map<String, BigDecimal> getAllCategoriesCost(Integer id) {
		
		return dao.getAllCategoriesCost(id);
	}

}
