package com.iii.eeit124.member.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.Files;
import com.iii.eeit124.entity.Members;



@Transactional
@Repository
public class LoginDaoImpl implements LoginDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	@Override
	public Members checkIDPassword(String account, String password) {
		Query<Members> query = sessionFactory.getCurrentSession().createQuery("from Members where ACCOUNT = ?0 and PASSWORD = ?1", Members.class);
		query.setParameter(0, account);
 		query.setParameter(1, password);
 		Members mb = query.uniqueResult();
		return mb;
	}

	@Override
	public Files getFileById(int id) {
		Query<Files> query = sessionFactory.getCurrentSession().createQuery("from Files where ID = ?0", Files.class);
		query.setParameter(0, id);
		Files file = query.uniqueResult();
		return file;
	}
}
