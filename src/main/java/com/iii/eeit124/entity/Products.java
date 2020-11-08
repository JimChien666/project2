package com.iii.eeit124.entity;
import java.io.Serializable;
import java.sql.Blob;
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
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "PRODUCTS")
@DynamicInsert@DynamicUpdate
public class Products implements Serializable{
 private static final long serialVersionUID = 1L;
 private Integer id;
 private String name;
 private Integer price;
 private Blob img;
// private String img;
 private String descript;
 private Integer quantity;
 private Integer specialPrice;
 private String rewardpoints;
 private boolean isThumb;
 private Integer memberId;
 private Integer animalTypeId;
 private Integer categoryId;
 private String filename;
 private Set<Files> files = new HashSet<Files>();   
 private MultipartFile profileImage;
 
 public Products() {
  
 } 

 @Id @Column
// @SequenceGenerator(name = "productsSeqGen", sequenceName = "PRODUCTS_SEQ1", allocationSize = 1, initialValue = 532)
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 public Integer getId() {
  return id;
 }
 public void setId(Integer id) {
  this.id = id;
 }
 @Column(name = "NAME")
 public String getName() {
  return name;
 }
 public void setName(String name) {
  this.name = name;
 }
 @Column(name = "PRICE")
 public Integer getPrice() {
  return price;
 }
 public void setPrice(Integer price) {
  this.price = price;
 }

 @Column(name = "IMG")
 @Lob
 public Blob getImg() {
  return img;
 }
 public void setImg(Blob img) {
  this.img = img;
 }
 @Column(name = "descript")
 public String getDescript() {
  return descript;
 }
 public void setDescript(String descript) {
  this.descript = descript;
 }
 @Column(name = "QUANTITY")
 public Integer getQuantity() {
  return quantity;
 }
 public void setQuantity(Integer quantity) {
  this.quantity = quantity;
 }
 @Column(name = "SPECIAL_PRICE")
 public Integer getSpecialPrice() {
  return specialPrice;
 }
 public void setSpecialPrice(Integer specialPrice) {
  this.specialPrice = specialPrice;
 }
 @Column(name = "REWARDPOINTS")
 public String getRewardpoints() {
  return rewardpoints;
 }
 public void setRewardpoints(String rewardpoints) {
  this.rewardpoints = rewardpoints;
 }
 @Column(name = "IS_THUMB") 
 public boolean getIsThumb() {
  return isThumb;
 }
 
 public void setIsThumb(boolean isThumb) {
  this.isThumb = isThumb;
 }
 @Column(name = "MEMBER_ID")
 public Integer getMemberId() {
  return memberId;
 }
 public void setMemberId(Integer memberId) {
  this.memberId = memberId;
 }
 @Column(name = "ANIMAL_TYPE_ID")
 public Integer getAnimalTypeId() {
  return animalTypeId;
 }
 public void setAnimalTypeId(Integer animalTypeId) {
  this.animalTypeId = animalTypeId;
 }
 @Column(name = "CATEGORY_ID")
 public Integer getCategoryId() {
  return categoryId;
 }
 public void setCategoryId(Integer categoryId) {
  this.categoryId = categoryId;
 }
 @Transient
 public String getFilename() {
  return filename;
 }
 public void setFilename(String filename) {
  this.filename = filename;
 }
// @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "FILES")
 @OneToMany(fetch = FetchType.LAZY, targetEntity=Files.class,cascade = CascadeType.ALL)
 @JoinColumns( value = {@JoinColumn(name="PRODUCT_ID",referencedColumnName="ID")})
 public Set<Files> getFiles() {
  return files;
 }
 public void setFiles(Set<Files> files) {
  this.files = files;
 }
  
@Transient 
public MultipartFile getProfileImage() {
	return profileImage;
}

public void setProfileImage(MultipartFile profileImage) {
	this.profileImage = profileImage;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Products [id=").append(id).append(", name=").append(name).append(", price=").append(price)
			.append(", img=").append(img).append(", descript=").append(descript).append(", quantity=").append(quantity)
			.append(", specialPrice=").append(specialPrice).append(", rewardpoints=").append(rewardpoints)
			.append(", isThumb=").append(isThumb).append(", memberId=").append(memberId).append(", animalTypeId=")
			.append(animalTypeId).append(", categoryId=").append(categoryId).append(", filename=").append(filename)
			.append(", files=").append(files).append(", profileImage=").append(profileImage).append("]");
	return builder.toString();
}

 
}