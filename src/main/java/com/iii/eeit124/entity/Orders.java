package com.iii.eeit124.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="ORDERS")
public class Orders {
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" {\"id\":\"").append(id).append("\", \"buyerId\":\"").append(buyerId)
				.append("\", \"buyerName\":\"").append(buyerName).append("\", \"buyerTel\":\"").append(buyerTel)
				.append("\", \"buyerAddress\":\"").append(buyerAddress).append("\", \"recipientName\":\"")
				.append(recipientName).append("\", \"recipientTel\":\"").append(recipientTel)
				.append("\", \"recipientAddress\":\"").append(recipientAddress).append("\", \"status\":\"")
				.append(status).append("\", \"total\":\"").append(total).append("\", \"createdAt\":\"")
				.append(createdAt).append("\", \"updatedAt\":\"").append(updatedAt).append("\", \"deletedAt\":\"")
				.append(deletedAt).append("\", \"buyer\":\"").append(buyer).append("\", \"orderItems\":\"")
				.append(orderItems).append("}");
		return builder.toString();
	}
	private Integer id;
	private Integer buyerId;
	private String buyerName;
	private String buyerTel;
	private String buyerAddress;
	private String recipientName;
	private String recipientTel;
	private String recipientAddress;
	private String status;
	private Double total;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	
	private Members buyer;
	private Set<OrderItems> orderItems = new HashSet<OrderItems>();
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Transient
	public Integer getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}
	@Column(name="BUYER_NAME")
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	@Column(name="BUYER_TEL")
	public String getBuyerTel() {
		return buyerTel;
	}
	public void setBuyerTel(String buyerTel) {
		this.buyerTel = buyerTel;
	}
	@Column(name="BUYER_ADDRESS")
	public String getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}
	@Column(name="RECIPIENT_NAME")
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	@Column(name="RECIPIENT_TEL")
	public String getRecipientTel() {
		return recipientTel;
	}
	public void setRecipientTel(String recipientTel) {
		this.recipientTel = recipientTel;
	}
	@Column(name="RECIPIENT_ADDRESS")
	public String getRecipientAddress() {
		return recipientAddress;
	}
	public void setRecipientAddress(String recipientAddress) {
		this.recipientAddress = recipientAddress;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="TOTAL")
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	@Column(name="CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	@Column(name="UPDATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Column(name="DELETED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="BUYER_ID")
	public Members getBuyer() {
		return buyer;
	}
	public void setBuyer(Members buyer) {
		this.buyer = buyer;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	public Set<OrderItems> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItems> orderItems) {
		this.orderItems = orderItems;
	}
	
}
