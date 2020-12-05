package com.iii.eeit124.shopping.dao;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingAanlysisDao {

	Map<String, BigDecimal> getAllCategoriesCost(Integer id);

	Map<String, BigDecimal> getAllAnimalTypeCost(Integer id);

	Map<String, BigDecimal> getAllColorCost(Integer id);

	Integer getMostBuyAnimalType(Integer id);

	Integer getMostBuyColor(Integer id);

}
