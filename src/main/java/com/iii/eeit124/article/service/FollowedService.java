package com.iii.eeit124.article.service;

import com.iii.eeit124.entity.FollowedArticle;

public interface FollowedService {
	Integer statusCheck(Integer memberid, Integer articleid);
}
