package com.iii.eeit124.shopping.dao;

import java.util.List;

import com.iii.eeit124.entity.ProductFiles;
import com.iii.eeit124.entity.Products;

public interface ProductFilesDao {
//	查產品ID的DAO
	ProductFiles getProductFiles(Integer Id);

	List<ProductFiles>  getProductFilesList(Integer Id); 
}
