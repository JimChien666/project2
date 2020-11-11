package com.iii.eeit124.shopping.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

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
	public Integer getTotalPages() {
		int totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}
	
	public long getRecordCounts() {
		Long count = 0L; // 必須使用 long 型態
		count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Products").getSingleResult();
		return count;
	}

	@Override
	public Products getProduct(Integer productId) {
		Query<Products> query = sessionFactory.getCurrentSession().createQuery("from Products where ID = ?0", Products.class);
		query.setParameter(0, productId);
		Products product = query.uniqueResult();
		return product;
	}

}
