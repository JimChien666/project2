package com.iii.eeit124.shopping.service;

import java.math.BigDecimal;
import java.util.Map;

public interface ShoppingAanlysisService {

	Map<String, BigDecimal> getAllCategoriesCost(Integer id);

	Map<String, BigDecimal> getAllAnimalTypeCost(Integer id);

	Map<String, BigDecimal> getAllColorCost(Integer id);

}
