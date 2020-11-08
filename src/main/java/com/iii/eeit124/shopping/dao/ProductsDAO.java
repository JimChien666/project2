package com.iii.eeit124.shopping.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.iii.eeit124.entity.Products;

@Repository
public class ProductsDAO {
	
	private SessionFactory sessionFactory;
	@Autowired
	public ProductsDAO( SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public ProductsDAO() {
		
	}
	
//	@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
//	maxFileSize=1024*1024*10,      // 10MB
//	maxRequestSize=1024*1024*50)   // 50MB
	public Products insert(Products product) {
			sessionFactory.getCurrentSession().save(product);
			return product;
			
			//新增圖片
//			Collection<Part> parts = request.getParts();
//			GlobalService.exploreParts(parts, request);
//			// 由parts != null來判斷此上傳資料是否為HTTP multipart request
//			if (parts != null) { // 如果這是一個上傳資料的表單
//				for (Part p : parts) {
//					String fldName = p.getName();
//					String value = request.getParameter(fldName);
//					// 1. 讀取使用者輸入資料
//					if (p.getContentType() != null) {
//						// 取出圖片檔的檔名
//						fileName = GlobalService.getFileName(p);
//						// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
//						fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
//						if (fileName != null && fileName.trim().length() > 0) {
//							sizeInBytes = p.getSize();
//							is = p.getInputStream();
//						} else {
//							
//							out.write( "必須挑選圖片檔");
//				        	 RequestDispatcher rd = request.getRequestDispatcher("/ProductsPage");			
//				        	 rd.forward(request,response);
//				        	 return;
//						}					
//					}				
//				}	
//			try {
//				blob = SystemUtils2018.fileToBlob(is, sizeInBytes);
//			} catch (IOException | SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//			//新增檔案到files資料庫
//
//			try {
//				String fileType = "";
//				String fileUrl = "";
//				
//				SessionFactory factory = HibernateUtil.getSessionFactory();
//				Session hSession = factory.getCurrentSession();
//
//				Products product = new Products(name, price, blob, descript, quantity, specialPrice, rewardpoints, isThumb, memberId, animalTypeId, categoryId);
//				
//				Set<Files> files = new HashSet<Files>();
//				files.add(new Files("image", blob));
//				product.setFiles(files);
//				hSession.save(product);
//	
//				System.out.println(" Create Product done!");
//				response.sendRedirect("/Project2/ProductsPage");
//			}	
			//
	}

	public Products select(Integer id) {//查ID單筆
		return sessionFactory.getCurrentSession().get(Products.class, id);
	}
	public List<Products> selectByName(String keyword) {//用名子與敘述查單筆
		Query<Products> query = sessionFactory.getCurrentSession().createQuery("from Products where name like ?1 or  descript like ?2 order by id ", Products.class);
		query.setParameter(1, "%"+keyword+"%");
		query.setParameter(2, "%"+keyword+"%");
		List<Products> list = query.list();
		return list;
	}

	public List<Products> selectById(Integer id) {//用ID查單筆
		Query<Products> query = sessionFactory.getCurrentSession().createQuery("from Products where id =?0 order by id", Products.class);
		query.setParameter(0, id);
		List<Products> list = query.list();
		return list;
	}

	public List<Products> selectAll() {//查所有,用ID排序
		Query<Products> query = sessionFactory.getCurrentSession().createQuery("from Products order by id", Products.class);
		List<Products> list = query.list();
		return list;
	}

	public Products update(Products products) {
		
		Products result = (Products)sessionFactory.getCurrentSession().merge(products);
		return result;
	}

	public boolean delete(Integer id) {
		Products products = sessionFactory.getCurrentSession().get(Products.class, id);
		if (products != null) {
			sessionFactory.getCurrentSession().delete(products);
			return true;
		}
		return false;
	}

}
