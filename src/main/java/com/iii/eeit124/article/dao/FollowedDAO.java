package com.iii.eeit124.article.dao;

import com.iii.eeit124.entity.FollowedArticle;

public interface FollowedDAO {
	FollowedArticle statusCheck(Integer memberid, Integer articleid);
	void statusSave(FollowedArticle followedArticle);
	void statusUpdate(FollowedArticle followedArticle);
	Integer checkId(Integer id);
}
