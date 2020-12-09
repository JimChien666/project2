package com.iii.eeit124.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FOLLOW_PRODUCTS")
public class FollowProducts {
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" {\"id\":\"").append(id).append("\", \"productId\":\"").append(productId)
				.append("\", \"memberId\":\"").append(memberId).append("\", \"status\":\"").append(status).append("}");
		return builder.toString();
	}
	private Integer id;
	private Integer productId;
	private Integer memberId;
	private Integer status;
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="PRODUCT_ID")
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	@Column(name="MEMBER_ID")
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	@Column(name="STATUS")
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
