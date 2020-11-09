package com.iii.eeit124.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.entity.Categories;
import com.iii.eeit124.entity.Colors;
import com.iii.eeit124.shopping.dao.CreateProductDao;


@Service
public class CreateProductServiceImpl implements CreateProductService {
	@Autowired
	CreateProductDao dao ;
	
	@Override
	public List<Colors> findAllColors(){
		return dao.findAllColors();
	}
	
	@Override
	public List<Categories> findAllCatrgories(){
		return dao.findAllCatrgories();
	}
}
