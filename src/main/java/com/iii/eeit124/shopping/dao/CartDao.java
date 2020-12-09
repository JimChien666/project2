package com.iii.eeit124.shopping.dao;

import com.iii.eeit124.entity.CartItems;
import com.iii.eeit124.entity.Products;

public interface CartDao {

	CartItems getCartItemInfo(Integer productId);

	Products getProduct(Integer productId);

}
