package com.iii.eeit124.article.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iii.eeit124.article.dao.FollowedDAO;
import com.iii.eeit124.entity.FollowedArticle;
@Transactional
@Service
public class FollowedServiceImpl implements FollowedService {

	@Autowired
	FollowedDAO followedDAO;
	
	@Override
	public Integer statusCheck(Integer memberid, Integer articleid) {
		FollowedArticle statusCheck = followedDAO.statusCheck(memberid, articleid);
//		Integer id = statusCheck.getId();

		if (statusCheck==null) {
			FollowedArticle followedArticle = new FollowedArticle();
			followedArticle.setMemberid(memberid);
			followedArticle.setStatus(1);
			followedArticle.setArticleid(articleid);
			followedDAO.statusSave(followedArticle);
			return 1;
		}else if(statusCheck.getStatus()==0){
//			Integer status = 0;
			statusCheck.setStatus(1);
			
//			followedDAO.update(memberid, articleid, status);
			followedDAO.statusUpdate(statusCheck);
			return 1; 
		} else {			

			
			
			statusCheck.setStatus(0);
//			followedDAO.update(memberid, articleid, status);
			followedDAO.statusUpdate(statusCheck);
			return 0;
		}

	}

}
