package com.iii.eeit124.shopping.service;

import com.iii.eeit124.entity.CartItems;
import com.iii.eeit124.entity.Products;


public interface CartService {

	CartItems getCartItemInfo(Integer productId);

	Products getProduct(Integer productId);

}
