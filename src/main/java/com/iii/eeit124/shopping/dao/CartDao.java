package com.iii.eeit124.shopping.dao;

import com.iii.eeit124.entity.CartItems;

public interface CartDao {

	CartItems getCartItemInfo(Integer productId);

}
