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
public class RegisterDaoImpl implements RegisterDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	
	// 儲存MemberBean物件，將參數mb新增到Member表格內。
	public int saveMember(Members mb) {
		sessionFactory.getCurrentSession().save(mb);
		return 1;
	}
	// 判斷參數id(會員帳號)是否已經被現有客戶使用，如果是，傳回true，表示此id不能使用，
	// 否則傳回false，表示此id可使用。
	@Override
	public boolean accountExists(String account) {
		Query<Members> query = sessionFactory.getCurrentSession().createQuery("from Members where ACCOUNT = ?0", Members.class);
		query.setParameter(0, account);
 		Members mb = query.uniqueResult();
		if (mb==null) {
			return false;
		}
		return true;
	}
	
	// 由參數 id (會員帳號) 到Member表格中 取得某個會員的所有資料，傳回值為一個MemberBean物件，
	// 如果找不到對應的會員資料，傳回值為null。
	@Override
	public Members queryMember(String id) {
		return sessionFactory.getCurrentSession().get(Members.class, id);
	}

	@Override
	public void saveFile(Files file) {
		sessionFactory.getCurrentSession().save(file);
	}
}
