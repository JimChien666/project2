package com.iii.eeit124.shopping.dao;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.Categories;

@Repository
@Transactional
public class ShoppingAanlysisDaoImpl implements ShoppingAanlysisDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Map<String, BigDecimal> getAllCategoriesCost(Integer id) {
		//每月第一天
		Calendar cal_1=Calendar.getInstance();
		cal_1.add(Calendar.MONTH, 0);
		cal_1.set(Calendar.DAY_OF_MONTH,0);
		//每月最後一天
		Calendar cale = Calendar.getInstance();
		cal_1.add(Calendar.MONTH, 0);
		cal_1.set(Calendar.DAY_OF_MONTH,1);
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery("From Categories");
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		System.out.println(cal_1.getTime());
		System.out.println(cale.getTime());
		@SuppressWarnings("unchecked")
		List<Categories> categories = query.getResultList();
		for(Categories categoriey:categories) {
			BigDecimal sum = new BigDecimal(0);
			@SuppressWarnings("rawtypes")
			Query query2 = sessionFactory.getCurrentSession().createSQLQuery("select sum(oi.price*oi.discount) from order_items oi left join products p on p.id=oi.product_id left join orders o on o.id=oi.order_id left join categories c on c.id=p.category_id where o.buyer_id=?0 and c.id=?1 and o.created_at between ?2 AND ?3 group by p.category_id");
			query2.setParameter(0, id);
			query2.setParameter(1, categoriey.getId());
			query2.setParameter(2, cal_1.getTime());
			query2.setParameter(3, cale.getTime());
			if (query2.uniqueResult() != null) {				
				sum = (BigDecimal)query2.uniqueResult();
			}
			map.put(categoriey.getName(), sum);
		}
		return map;
	}
	

	
}
