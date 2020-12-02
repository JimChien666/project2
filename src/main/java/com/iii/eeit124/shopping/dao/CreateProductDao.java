package com.iii.eeit124.shopping.dao;

import java.util.List;

import com.iii.eeit124.entity.AnimalTypes;
import com.iii.eeit124.entity.Categories;
import com.iii.eeit124.entity.Colors;
import com.iii.eeit124.entity.ProductFiles;
import com.iii.eeit124.entity.Products;

public interface CreateProductDao {
	List<Colors> findAllColors();

	List<Categories> findAllCatrgories();

	List<AnimalTypes> findAllAnimalTypes();

	Colors findOneColor(Integer id);

	Categories findOneCatrgory(Integer id);

	AnimalTypes findOneAnimalType(Integer id);

	Products insertProduct(Products prducts);

}

