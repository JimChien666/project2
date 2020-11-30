package com.iii.eeit124.article.service;

import java.util.List;

import com.iii.eeit124.entity.Comments;

public interface CommentsService {
	void save(Comments comments);

	List<Comments> select(int id);
}
