package com.iii.eeit124.article.dao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iii.eeit124.entity.MemberOption;
import com.iii.eeit124.entity.Options;
@Repository
public class MemberOptionDAOImpl implements MemberOptionDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(MemberOption entity) {
		Session session = sessionFactory.getCurrentSession();
		session.save(entity);
	}

	@Override
	public Options findOptionById(Integer optionid) {
		Session session = sessionFactory.getCurrentSession();
		Query<Options> query = session.createQuery("from Options where id = ?0", Options.class);
		query.setParameter(0, optionid);
		Options option = query.uniqueResult();
		return option;
	}

	@Override
	public boolean CheckMemberVoteStatus(Integer mid, int fid) {
		Session session = sessionFactory.getCurrentSession();
		Query<MemberOption> query = session.createQuery("from MemberOption where MEMBER_ID = ?0 and FORUM_ID = ?1", MemberOption.class);
		query.setParameter(0, mid);
		query.setParameter(1, fid);
		MemberOption option = query.uniqueResult();
		if (option==null) {
			return true;
		}
		return false;
	}

	@Override
	public List<MemberOption> getVoteResult(Integer fid) {
		Session session = sessionFactory.getCurrentSession();
		Query<MemberOption> query = session.createQuery("from MemberOption where FORUM_ID = ?0", MemberOption.class);
		query.setParameter(0, fid);
		query.getResultList();		
		return query.getResultList();
	}

	@Override
	public Map<String, Integer> getVoteCount(Integer fid) {
		Session session = sessionFactory.getCurrentSession();
		Map<String, Integer> map = new HashMap<String, Integer>();
		Query<Options> queryOption = session.createQuery("from Options where FORUM_ID = ?0", Options.class);
		queryOption.setParameter(0, fid);
		List<Options> options = queryOption.getResultList();
		for(Options option:options) {
			String content = option.getContent();
			
			Query query = session.createSQLQuery("select count(mo.id) from options o left join  member_option mo on mo.option_id = o.id where o.forum_id= ?0 and o.content=?1 group by o.content");
			query.setParameter(0, fid);
			query.setParameter(1, content);
			BigDecimal uniqueResult = (BigDecimal)query.uniqueResult();
			Integer count = uniqueResult.intValue();
			map.put(content, count);
		}
		return map;
	}

}
