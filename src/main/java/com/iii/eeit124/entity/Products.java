package com.iii.eeit124.entity;

import java.sql.Blob;
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
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="PRODUCTS")
public class Products {
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" {\"id\":\"").append(id).append("\", \"name\":\"").append(name).append("\", \"description\":\"")
				.append(description).append("\", \"quantity\":\"").append(quantity).append("\", \"price\":\"")
				.append(price).append("\", \"discount\":\"").append(discount).append("\", \"memberId\":\"")
				.append(memberId).append("\", \"categoryId\":\"").append(categoryId).append("\", \"colorId\":\"")
				.append(colorId).append("\", \"animalTypeId\":\"").append(animalTypeId).append("\", \"coverImg\":\"")
				.append(coverImg).append("\", \"createdAt\":\"").append(createdAt).append("\", \"updatedAt\":\"")
				.append(updatedAt).append("\", \"deletedAt\":\"").append(deletedAt).append("\", \"status\":\"")
				.append(status).append("\", \"multipartFile\":\"").append(multipartFile).append("\", \"animalType\":\"")
				.append(animalType).append("\", \"color\":\"").append(color).append("\", \"member\":\"").append(member)
				.append("\", \"category\":\"").append(category).append("\", \"contentImgs\":\"").append(contentImgs)
				.append("}");
		return builder.toString();
	}
	private Integer id;
	private String name;
	private String description;
	private Integer quantity;
	private Double price;
	private Double discount;
	private Integer memberId;
	private Integer categoryId;
	private Integer colorId;
	private Integer animalTypeId;
	private Blob coverImg;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private String status;
	private MultipartFile multipartFile;
	
	private AnimalTypes animalType;
	private Colors color;
	private Members member;
	private Categories category;
	private Set<ProductFiles> contentImgs = new HashSet<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ANIMAL_TYPE_ID")
	public AnimalTypes getAnimalType() {
		return animalType;
	}
	public void setAnimalType(AnimalTypes animalType) {
		this.animalType = animalType;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COLOR_ID")
	public Colors getColor() {
		return color;
	}
	public void setColor(Colors color) {
		this.color = color;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MEMBER_ID")
	public Members getMember() {
		return member;
	}
	public void setMember(Members member) {
		this.member = member;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID")
	public Categories getCategory() {
		return category;
	}
	public void setCategory(Categories category) {
		this.category = category;
	}
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="QUANTITY")
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	@Column(name="PRICE")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Column(name="DISCOUNT")
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	@Transient
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	@Transient
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	@Transient
	public Integer getColorId() {
		return colorId;
	}
	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}
	@Transient
	public Integer getAnimalTypeId() {
		return animalTypeId;
	}
	public void setAnimalTypeId(Integer animalTypeId) {
		this.animalTypeId = animalTypeId;
	}
	@Column(name="COVER_IMG")
	public Blob getCoverImg() {
		return coverImg;
	}
	public void setCoverImg(Blob coverImg) {
		this.coverImg = coverImg;
	}
	@Column(name="CREATED_AT")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	@Column(name="UPDATED_AT")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Column(name="DELETED_AT")
	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Transient
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	public Set<ProductFiles> getContentImgs() {
		return contentImgs;
	}
	public void setContentImgs(Set<ProductFiles> contentImgs) {
		this.contentImgs = contentImgs;
	}
}
