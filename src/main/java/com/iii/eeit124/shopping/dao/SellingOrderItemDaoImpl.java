package com.iii.eeit124.shopping.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iii.eeit124.entity.OrderItems;
import com.iii.eeit124.entity.ProductFiles;
@Transactional
@Repository
public class SellingOrderItemDaoImpl implements SellingOrderItemDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public List<OrderItems> getPageSellingOrderItems(Integer pageNo ,Integer recordsPerPage,Integer memberId) {
		Integer startRecordNo = (pageNo - 1) * recordsPerPage;
		Query<OrderItems> query = sessionFactory.getCurrentSession().createQuery(" from OrderItems where SELLER_ID=?0", OrderItems.class);
		query.setParameter(0, memberId).setFirstResult(startRecordNo).setMaxResults(recordsPerPage);
		
		return query.getResultList();
	}

	@Override
	public Long getRecordCounts(Integer memberId) {
		Long count = 0L; // 必須使用 long 型態
		count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM OrderItems where SELLER_ID=?0").setParameter(0, memberId).getSingleResult();
		return count;
	}

}
