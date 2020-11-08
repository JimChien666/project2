package com.iii.eeit124.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Oders implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String orderItem;
	private int productsId;
	private int shoppingCartId;
	
	public Oders() {		
	}
	@Id @Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "ORDER_ITEM")
	public String getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}
	@Column(name = "PRODUCTS_ID")
	public int getProductsId() {
		return productsId;
	}

	public void setProductsId(int productsId) {
		this.productsId = productsId;
	}
	@Column(name = "SHOPPING_CART_ID")
	public int getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	
}
