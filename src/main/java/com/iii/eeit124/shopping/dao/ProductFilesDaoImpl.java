package com.iii.eeit124.shopping.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;
import org.hibernate.query.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iii.eeit124.entity.ProductFiles;

@Transactional
@Repository
public class ProductFilesDaoImpl implements ProductFilesDao {
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Override
	public ProductFiles  getProductFiles(Integer Id) {
		Query<ProductFiles> query = sessionFactory.getCurrentSession().createQuery(" from ProductFiles where ID = ?0", ProductFiles.class);
		query.setParameter(0, Id);
		
		 ProductFiles productFiles = query.uniqueResult();

		return productFiles;		
	}

	
}
