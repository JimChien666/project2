package com.iii.eeit124.shopping.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.MemberFiles;
import com.iii.eeit124.entity.Products;
import com.iii.eeit124.util.GlobalService;


@Transactional
@Repository
public class ProductListDaoImpl implements ProductListDao {
	@Autowired
	SessionFactory sessionFactory;
	
	Integer recordsPerPage = GlobalService.RECORDS_PER_PAGE;

	@Override
	public List<Products> findAllProducts() {
		@SuppressWarnings("unchecked")
		TypedQuery<Products> query = sessionFactory.getCurrentSession().createQuery("from Products");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Products> getPageProducts(Integer pageNo) {
		Integer startRecordNo = (pageNo - 1) * recordsPerPage;
		List<Products> list = new ArrayList<Products>();
		list = sessionFactory.getCurrentSession().createQuery("FROM Products")
  			  .setFirstResult(startRecordNo)
  			  .setMaxResults(recordsPerPage)
  			  .getResultList();
		
		
		return list;
	}

	@Override
	public Integer getTotalPages(Integer colorId, Integer categoryId, Integer animalTypeId) {
		int totalPages = (int) (Math.ceil(getRecordCounts(colorId, categoryId, animalTypeId) / (double) recordsPerPage));
		return totalPages;
	}
	
	public Long getRecordCounts(Integer colorId, Integer categoryId, Integer animalTypeId) {
		Long count = 0L; // 必須使用 long 型態
		
		String condiction = " where ";
		if (colorId != null) {
			condiction += "color_id = :colorId";
		}
		if (categoryId != null) {
			if (!condiction.equals(" where ")) {
				condiction += " and category_id = :categoryId";				
			}else {
				condiction += " category_id = :categoryId";
			}
		}
		if (animalTypeId != null) {
			if (!condiction.equals(" where ")) {
				condiction += " and animal_type_id = :animalTypeId";
			}else {
				condiction += " animal_type_id = :animalTypeId";
			}
		}
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Products" + condiction);
		if (colorId != null) {
			query.setParameter("colorId", colorId);
		}
		if (categoryId != null) {
			query.setParameter("categoryId", categoryId);
		}
		if (animalTypeId != null) {
			query.setParameter("animalTypeId", animalTypeId);
		}
		count = (Long) query.getSingleResult();
		return count;
	}

	@Override
	public Products getProduct(Integer productId) {
		Query<Products> query = sessionFactory.getCurrentSession().createQuery("from Products where ID = ?0", Products.class);
		query.setParameter(0, productId);
		Products product = query.uniqueResult();
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Products> getPageProducts(Integer pageNo, Integer colorId, Integer categoryId, Integer animalTypeId) {
		Integer startRecordNo = (pageNo - 1) * recordsPerPage;
		List<Products> list = new ArrayList<Products>();
		String condiction = " where ";
		if (colorId != null) {
			condiction += "color_id = :colorId";
		}
		if (categoryId != null) {
			if (!condiction.equals(" where ")) {
				condiction += " and category_id = :categoryId";				
			}else {
				condiction += " category_id = :categoryId";
			}
		}
		if (animalTypeId != null) {
			if (!condiction.equals(" where ")) {
				condiction += " and animal_type_id = :animalTypeId";
			}else {
				condiction += " animal_type_id = :animalTypeId";
			}
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Products" + condiction);
		query.setFirstResult(startRecordNo);
		query.setMaxResults(recordsPerPage);
		if (colorId != null) {
			query.setParameter("colorId", colorId);
		}
		if (categoryId != null) {
			query.setParameter("categoryId", categoryId);
		}
		if (animalTypeId != null) {
			query.setParameter("animalTypeId", animalTypeId);
		}
		list = query.getResultList();
//  			  .setFirstResult(startRecordNo)
//  			  .setMaxResults(recordsPerPage)
//  			  .getResultList();
		
		return list;
	}

	@Override
	public Integer getPageProducts() {
		int totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}

	public Long getRecordCounts() {
		Long count = 0L; // 必須使用 long 型態
		count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Products").getSingleResult();
		return count;
	}

}
