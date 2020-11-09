package com.iii.eeit124.shopping.service;

import java.util.List;

import com.iii.eeit124.entity.Categories;
import com.iii.eeit124.entity.Colors;

public interface CreateProductService {
	List<Colors> findAllColors();
	List<Categories> findAllCatrgories();
}
