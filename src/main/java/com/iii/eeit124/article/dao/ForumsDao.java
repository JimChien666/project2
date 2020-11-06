package com.iii.eeit124.article.dao;

import java.util.List;

import com.iii.eeit124.entity.Forums;

public interface ForumsDao {
	List<Forums> select(int id);
}
