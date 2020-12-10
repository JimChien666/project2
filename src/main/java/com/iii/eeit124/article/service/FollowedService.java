package com.iii.eeit124.article.service;

import java.util.List;

import com.iii.eeit124.entity.FollowedArticle;

public interface FollowedService {
	Integer statusChange(Integer memberid, Integer articleid);
	List<FollowedArticle> statusCheck(Integer memberid);
	List<FollowedArticle> personalFollowed(Integer memberid);
}
