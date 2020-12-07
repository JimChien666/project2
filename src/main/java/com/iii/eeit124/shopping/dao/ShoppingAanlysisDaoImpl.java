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

import com.iii.eeit124.entity.AnimalTypes;
import com.iii.eeit124.entity.Categories;
import com.iii.eeit124.entity.Colors;

@Repository
@Transactional
public class ShoppingAanlysisDaoImpl implements ShoppingAanlysisDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Map<String, BigDecimal> getAllCategoriesCost(Integer id) {
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery("From Categories");
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		@SuppressWarnings("unchecked")
		List<Categories> categories = query.getResultList();
		for(Categories categoriey:categories) {
			BigDecimal sum = new BigDecimal(0);
			@SuppressWarnings("rawtypes")
			Query query2 = sessionFactory.getCurrentSession().createSQLQuery("select sum(oi.price*oi.discount) from order_items oi left join products p on p.id=oi.product_id left join orders o on o.id=oi.order_id left join categories c on c.id=p.category_id where o.buyer_id=?0 and c.id=?1 group by p.category_id");
			query2.setParameter(0, id);
			query2.setParameter(1, categoriey.getId());
			if (query2.uniqueResult() != null) {				
				sum = (BigDecimal)query2.uniqueResult();
			}
			map.put(categoriey.getName(), sum);
		}
		return map;
	}

	@Override
	public Map<String, BigDecimal> getAllAnimalTypeCost(Integer id) {
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery("From AnimalTypes");
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		@SuppressWarnings("unchecked")
		List<AnimalTypes> animalTypes = query.getResultList();
		for(AnimalTypes animalType:animalTypes) {
			BigDecimal sum = new BigDecimal(0);
			@SuppressWarnings("rawtypes")
			Query query2 = sessionFactory.getCurrentSession().createSQLQuery("select sum(oi.price*oi.discount) from order_items oi left join products p on p.id=oi.product_id left join orders o on o.id=oi.order_id left join animal_types ats on ats.id=p.animal_type_id where o.buyer_id=?0 and ats.id=?1 group by p.animal_type_id");
			query2.setParameter(0, id);
			query2.setParameter(1, animalType.getId());
			if (query2.uniqueResult() != null) {				
				sum = (BigDecimal)query2.uniqueResult();
			}
			map.put(animalType.getName(), sum);
		}
		return map;
	}

	@Override
	public Map<String, BigDecimal> getAllColorCost(Integer id) {
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery("From Colors");
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		@SuppressWarnings("unchecked")
		List<Colors> colors = query.getResultList();
		for(Colors color:colors) {
			BigDecimal sum = new BigDecimal(0);
			@SuppressWarnings("rawtypes")
			Query query2 = sessionFactory.getCurrentSession().createSQLQuery("select sum(oi.price*oi.discount) from order_items oi left join products p on p.id=oi.product_id left join orders o on o.id=oi.order_id left join colors c on c.id=p.color_id where o.buyer_id=?0 and c.id=?1 group by p.color_id");
			query2.setParameter(0, id);
			query2.setParameter(1, color.getId());
			if (query2.uniqueResult() != null) {				
				sum = (BigDecimal)query2.uniqueResult();
			}
			map.put(color.getName(), sum);
		}
		return map;
	}
	

	
}
