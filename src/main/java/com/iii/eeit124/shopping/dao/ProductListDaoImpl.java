package com.iii.eeit124.shopping.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.Activitys;
import com.iii.eeit124.entity.FollowProducts;
import com.iii.eeit124.entity.Products;


@Transactional
@Repository
public class ProductListDaoImpl implements ProductListDao {
	@Autowired
	SessionFactory sessionFactory;
	
//	Integer recordsPerPage = GlobalService.RECORDS_PER_PAGE;
	static String orderByString=" order by color_Id";
	public static String getPageOrderBy(Integer orderBy) {
		switch(orderBy) { 
	        case 0:
	        	orderByString=" order by color_Id, id";
	        	break;
	        case 1: 
	        	orderByString=" order by name";
	            break; 
	        case 2: 
	        	orderByString=" order by price";
	            break; 
	        case 3: 
	        	orderByString=" order by name desc";
	            break; 
	        case 4: 
	        	orderByString=" order by price desc";
	            break; 
	        case 5: 
	        	orderByString=" order by id desc";
	            break; 

	    }
		return orderByString;
	}
	@Override
	public List<Products> findAllProducts() {
		@SuppressWarnings("unchecked")
		TypedQuery<Products> query = sessionFactory.getCurrentSession().createQuery("from Products where deleted_at=null and status='上架中' " + orderByString);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Products> getPageProducts(Integer pageNo,Integer recordsPerPage) {
		Integer startRecordNo = (pageNo - 1) * recordsPerPage; // 第二頁的第二筆＝（2-1）*3
		List<Products> list = new ArrayList<Products>();
		list = sessionFactory.getCurrentSession().createQuery("from Products where deleted_at=null and status='上架中' " + orderByString)
  			  .setFirstResult(startRecordNo) //index的概念
  			  .setMaxResults(recordsPerPage) //當最後一頁商品數量不足顯示,則補足磯零數
  			  .getResultList();
		return list;
	}

	@Override
	public Integer getTotalPages(Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage,String keywordSearch) {
		//用getRecordCounts()來計算總頁數
		int totalPages = (int) (Math.ceil(getRecordCounts(colorId, categoryId, animalTypeId,keywordSearch) / (double) recordsPerPage));
		return totalPages;
	}
	
	public Long getRecordCounts(Integer colorId, Integer categoryId, Integer animalTypeId,String keywordSearch) {
		Long count = 0L; // 必須使用 long 型態
		
		String condiction = " ";
		if (colorId != null) {
			condiction += " and color_id = :colorId";
		}
		if (categoryId != null) {
//			if (!condiction.equals(" where ")) {
				condiction += " and category_id = :categoryId";				
//			}else {
//				condiction += " category_id = :categoryId";
//			}
		}
		if (animalTypeId != null) {
//			if (!condiction.equals(" where ")) {
				condiction += " and animal_type_id = :animalTypeId";
//			}else {
//				condiction += " animal_type_id = :animalTypeId";
//			}
		}
		if (keywordSearch != null) {
//			if (!condiction.equals(" where ")) {
				condiction += " and name like :keywordSearch";
//			}else {
//				condiction += " animal_type_id = :animalTypeId";
//			}
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Products where deleted_at=null and status='上架中' " + condiction);
		if (colorId != null) {
			query.setParameter("colorId", colorId);
		}
		if (categoryId != null) {
			query.setParameter("categoryId", categoryId);
		}
		if (animalTypeId != null) {
			query.setParameter("animalTypeId", animalTypeId);
		}
		if (keywordSearch != null) {
			query.setParameter("keywordSearch", "%"+keywordSearch+"%");
		}
		count = (Long) query.getSingleResult();
		return count;
	}

	@Override
	@Nullable  //使用者進入購物車詳細清單與商品詳細列表時ProductsInfoController使用此方法才不會報錯
	public Products getProduct(Integer productId) {
		Query<Products> query = sessionFactory.getCurrentSession().createQuery("from Products where  ID = ?0", Products.class);
		query.setParameter(0, productId);
		Products product = query.uniqueResult();
		return product;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Products> getPageProducts(Integer pageNo, Integer colorId, Integer categoryId, Integer animalTypeId,Integer recordsPerPage,String keywordSearch) {
		Integer startRecordNo = (pageNo - 1) * recordsPerPage;
		List<Products> list = new ArrayList<Products>();
		String condiction = "  ";
		if (keywordSearch != null) {
			condiction += " and name like :keywordSearch";
		}
		if (colorId != null) {
			condiction += " and color_id = :colorId";
		}
		if (categoryId != null) {
//			if (!condiction.equals(" where ")) {
				condiction += " and category_id = :categoryId";				
//			}else {
//				condiction += " category_id = :categoryId";
//			}
		}
		if (animalTypeId != null) {
//			if (!condiction.equals(" where ")) {
				condiction += " and animal_type_id = :animalTypeId";
//			}else {
//				condiction += " animal_type_id = :animalTypeId";
//			}
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery("FROM Products Where deleted_at=null and status='上架中' " + condiction  + orderByString);
		
		
		if (keywordSearch != null) {
			query.setParameter("keywordSearch", "%"+keywordSearch+"%");
		}
		if (colorId != null) {
			query.setParameter("colorId", colorId);
		}
		if (categoryId != null) {
			query.setParameter("categoryId", categoryId);
		}
		if (animalTypeId != null) {
			query.setParameter("animalTypeId", animalTypeId);
		}
		query.setFirstResult(startRecordNo)
		.setMaxResults(recordsPerPage);
		list = query.getResultList();
//  			  .setFirstResult(startRecordNo)
//  			  .setMaxResults(recordsPerPage)
//  			  .getResultList();
		
		return list;
	}

	@Override
	public Integer getTotalPages(Integer recordsPerPage) {
		int totalPages = (int) (Math.ceil(getRecordCounts() / (double) recordsPerPage));
		return totalPages;
	}

	public Long getRecordCounts() {
		Long count = 0L; // 必須使用 long 型態
		count = (Long) sessionFactory.getCurrentSession().createQuery("SELECT count(*) FROM Products where deleted_at=null and status='上架中' ").getSingleResult();
		return count;
	}
	
	public List<Products> selectByName(String keyword) {//用名子與敘述查詢
		Query<Products> query = sessionFactory.getCurrentSession().createQuery("from Products where deleted_at=null and status='上架中'  and name like ?1 " + orderByString, Products.class);
//		Query<Products> query = sessionFactory.getCurrentSession().createQuery("from Products where deleted_at=null and status='上架中'  and name like ?1 or description like ?2 "+ orderByString, Products.class);
		query.setParameter(1, "%"+keyword+"%");
		List<Products> list = query.getResultList();
		return list;
	}
	@Override
	public Integer changeLikeStatus(Integer productId, Integer memberId) {
		Query<FollowProducts> query = sessionFactory.getCurrentSession().createQuery("from FollowProducts where product_id=?0 and member_id=?1", FollowProducts.class);
		query.setParameter(0, productId);
		query.setParameter(1, memberId);
		FollowProducts followProduct = query.uniqueResult();
		if (followProduct==null) {
			return 2;
		} else if (followProduct.getStatus() == 1) {
			return 1;
		}
		return 0;
	}
	@Override
	public void updateFollowProductStatus(FollowProducts followProduct) {
		sessionFactory.getCurrentSession().update(followProduct);
	}
	@Override
	public void saveFollowProduct(FollowProducts followProduct) {
		sessionFactory.getCurrentSession().save(followProduct);
	}
	@Override
	public FollowProducts getFollowProduct(Integer productId, Integer memberId) {
		@SuppressWarnings("unchecked")
		TypedQuery<FollowProducts> query = sessionFactory.getCurrentSession().createQuery("from FollowProducts where product_id = ?0 and member_id=?1");
		query.setParameter(0, productId);
		query.setParameter(1, memberId);
		List<FollowProducts> list = query.getResultList();
		return list != null ? list.get(0) : null;
	}
	@Override
	public List<FollowProducts> getLikeProduct(Integer memberId) {
		TypedQuery<FollowProducts> query = sessionFactory.getCurrentSession().createQuery("from FollowProducts where member_id=?0 and status=1");
		query.setParameter(0, memberId);
		List<FollowProducts> resultList = query.getResultList();
		return resultList;
	}
	@Override
	public List<Products> getLikeProductList(Integer memberId) {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("select product_id from Follow_Products where member_id=?0 and status=1");
		query.setParameter(0, memberId);
		List resultList = query.getResultList();
		List<Products> resultList2 = new ArrayList();
		if(resultList.size()!= 0) {
			Query<Products> query2 = sessionFactory.getCurrentSession().createQuery("from Products where deleted_at=null and status='上架中'  and id in ("+ resultList.toString().replace("[", "").replace("]", "") +")", Products.class);
			resultList2 = query2.getResultList();
		}
		
		
		return resultList2;
	}
}
