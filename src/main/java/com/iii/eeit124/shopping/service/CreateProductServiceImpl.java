package com.iii.eeit124.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.entity.AnimalTypes;
import com.iii.eeit124.entity.Categories;
import com.iii.eeit124.entity.Colors;
import com.iii.eeit124.entity.ProductFiles;
import com.iii.eeit124.entity.Products;
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

	@Override
	public List<AnimalTypes> findAllAnimalTypes() {
		return dao.findAllAnimalTypes();
	}

	@Override
	public Colors findOneColor(Integer id) {
		return dao.findOneColor(id);
	}

	@Override
	public Categories findOneCatrgory(Integer id) {
		return dao.findOneCatrgory(id);
	}

	@Override
	public AnimalTypes findOneAnimalType(Integer id) {
		return dao.findOneAnimalType(id);
	}

	@Override
	public Products insertProduct(Products prducts) {
		return dao.insertProduct(prducts);
	}
	
	@Override
	public List<Products> selectById(Integer id){
		return dao.selectById(id);	
	}
}

