package com.iii.eeit124.entity;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
				.append(animalType).append("\", \"color\":\"").append(color).append("\", \"member\":\"")
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
	
//	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ANIMAL_TYPE_ID")
	public AnimalTypes getAnimalType() {
		return animalType;
	}
	public void setAnimalType(AnimalTypes animalType) {
		this.animalType = animalType;
	}
//	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COLOR_ID")
	public Colors getColor() {
		return color;
	}
	public void setColor(Colors color) {
		this.color = color;
	}
//	@JsonIgnore
	@ManyToOne(targetEntity=Members.class)
	@JoinColumn(name="MEMBER_ID")
	public Members getMember() {
		return member;
	}
	public void setMember(Members member) {
		this.member = member;
	}
//	@JsonIgnore
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
//	@Column(name="PRICE")
	@Transient
	public int getDiscountPrice() {
		return (int) Math.round(price*discount);
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
//	@Column(name="MEMBER_ID")
	public Integer getMemberId() {
//		return this.memberId;
		return this.member.getId();
	}
	@Transient
	public String getMemberName() {
//		return "NN";
		return this.member.getName();
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
	@JsonIgnore
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
	@JsonIgnore
	@Transient
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	public Set<ProductFiles> getContentImgs() {
		return contentImgs;
	}
	public void setContentImgs(Set<ProductFiles> contentImgs) {
		this.contentImgs = contentImgs;
	}
	
	//取得ProductFiles的ID，給ProductsInfo.jsp讀照片用
	@Transient
	public List<Integer> getProductFilesId(){
		Iterator iterator =  this.contentImgs.iterator();
		List<Integer> list = new ArrayList<>();
		while(iterator.hasNext()) {
		    ProductFiles productFiles = (ProductFiles)iterator.next();
		    list.add(productFiles.getId());
		    
		}
		return list;
	}
}
