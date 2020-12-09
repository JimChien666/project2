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
		Integer id = statusCheck.getId();

		if (statusCheck.getStatus()==1) {
			statusCheck.setMemeberid(memberid);
			statusCheck.setStatus(0);
			statusCheck.setArticleid(articleid);
//			followedDAO.update(memberid, articleid, status);
			followedDAO.statusUpdate(followedArticle);
		}else if(statusCheck.getStatus()==0){
//			Integer status = 0;
			statusCheck.setMemeberid(memberid);
			statusCheck.setStatus(1);
			statusCheck.setArticleid(articleid);
//			followedDAO.update(memberid, articleid, status);
			followedDAO.statusUpdate(followedArticle);
		} else {			
			FollowedArticle followedArticle = new FollowedArticle();
			followedArticle.setMemeberid(memberid);
			followedArticle.setStatus(statusCheck.getStatus());
			followedArticle.setArticleid(articleid);
			followedDAO.statusSave(followedArticle);
		}
		return statusCheck;	

	}

}
