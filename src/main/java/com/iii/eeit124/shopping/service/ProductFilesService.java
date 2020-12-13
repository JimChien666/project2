package com.iii.eeit124.shopping.service;

import java.util.List;

import com.iii.eeit124.entity.ProductFiles;
import com.iii.eeit124.entity.Products;

public interface ProductFilesService {
	
	ProductFiles getProductFiles(Integer Id);
	
	List<ProductFiles> getProductFilesList(Integer Id); 
}
