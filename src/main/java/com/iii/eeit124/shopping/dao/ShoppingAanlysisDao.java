package com.iii.eeit124.shopping.dao;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingAanlysisDao {

	Map<String, BigDecimal> getAllCategoriesCost(Integer id);

}
