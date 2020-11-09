package com.iii.eeit124.member.dao;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.iii.eeit124.entity.MemberFiles;
import com.iii.eeit124.entity.Members;



@Transactional
@Repository
public class LoginDaoImpl implements LoginDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	@Override
	@Nullable
	public Members checkIDPassword(String account, String password) {
		Query<Members> query = sessionFactory.getCurrentSession().createQuery("from Members where ACCOUNT = ?0 and PASSWORD = ?1", Members.class);
		query.setParameter(0, account);
 		query.setParameter(1, password);
 		Members mb = query.uniqueResult();
		return mb;
	}

	@Override
	@Nullable
	public MemberFiles getFileById(int id) {
		Query<MemberFiles> query = sessionFactory.getCurrentSession().createQuery("from MemberFiles where ID = ?0", MemberFiles.class);
		query.setParameter(0, id);
		MemberFiles file = query.uniqueResult();
		return file;
	}
}
