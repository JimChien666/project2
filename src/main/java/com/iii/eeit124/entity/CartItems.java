package com.iii.eeit124.entity;

public class CartItems {
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" {\"productId\":\"").append(productId).append("\", \"price\":\"").append(price)
				.append("\", \"discount\":\"").append(discount).append("\", \"quantity\":\"").append(quantity)
				.append("\", \"memberId\":\"").append(memberId).append("\", \"memberName\":\"").append(memberName)
				.append("\", \"productName\":\"").append(productName).append("}");
		return builder.toString();
	}
	private Integer productId;
	private Double price;
	private Double discount;
	private Integer quantity;
	private Integer memberId;
	private String memberName;
	private String productName;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Double getPrice() {
		return price;
	}
	public int getDiscountPrice() {
		return (int) Math.round(price*discount);
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDiscount() {
		return discount;
	}
	
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
}
