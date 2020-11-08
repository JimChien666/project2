package com.iii.eeit124.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCart implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int membersId;
	private int productId;
	private int quantity;
	
	public ShoppingCart() {		
	}
	@Id @Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "MEMBERS_ID")
	public int getMembersId() {
		return membersId;
	}

	public void setMembersId(int membersId) {
		this.membersId = membersId;
	}
	@Column(name = "PRODUCTS_ID")
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	@Column(name = "QUANTITY")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
}
