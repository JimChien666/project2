package com.iii.eeit124.shopping.service;

import java.util.List;

import com.iii.eeit124.entity.AnimalTypes;
import com.iii.eeit124.entity.Categories;
import com.iii.eeit124.entity.Colors;
import com.iii.eeit124.entity.ProductFiles;
import com.iii.eeit124.entity.Products;

public interface CreateProductService {
	List<Colors> findAllColors();
	List<Categories> findAllCatrgories();
	List<AnimalTypes> findAllAnimalTypes();
	Colors findOneColor(Integer id);
	Categories findOneCatrgory(Integer id);
	AnimalTypes findOneAnimalType(Integer id);
	Products insertProduct(Products prducts);
	Products selectById(Integer id);
	Products updateProduct(Products prducts);

}
