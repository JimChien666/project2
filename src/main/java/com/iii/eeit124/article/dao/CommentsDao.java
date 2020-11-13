package com.iii.eeit124.article.dao;

import java.util.List;

import com.iii.eeit124.entity.Comments;

public interface CommentsDao {
	void save(Comments comments);

	List<Comments> select(int id);
}
