package jim.entity;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import nn.entity.Files;

@Entity
@Table(name = "PRODUCTS")
public class Products implements Serializable{
 private static final long serialVersionUID = 1L;
 private int id;
 private String name;
 private int price;
 private Blob img;
// private String img;
 private String descript;
 private int quantity;
 private int specialPrice;
 private String rewardpoints;
 private boolean isThumb;
 private int memberId;
 private int animalTypeId;
 private int categoryId;
 private String filename;
 private Set<Files> files = new HashSet<Files>(); 
  
 
 public Products() {
  
 }
 public Products(String name, int price, Blob img, String descript, int quantity, int specialPrice,
 String rewardpoints, boolean isThumb, int memberId, int animalTypeId, int categoryId) {

  this.name = name;
  this.price = price;
  this.img = img;
  this.descript = descript;
  this.quantity = quantity;
  this.specialPrice = specialPrice;
  this.rewardpoints = rewardpoints;
  this.isThumb = isThumb;
  this.memberId = memberId;
  this.animalTypeId = animalTypeId;
  this.categoryId = categoryId;
 }
 public Products(int id,String name, int price, Blob img, String descript, int quantity, int specialPrice,
 String rewardpoints, boolean isThumb, int memberId, int animalTypeId, int categoryId) {
  this.id = id;
  this.name = name;
  this.price = price;
  this.img = img;
  this.descript = descript;
  this.quantity = quantity;
  this.specialPrice = specialPrice;
  this.rewardpoints = rewardpoints;
  this.isThumb = isThumb;
  this.memberId = memberId;
  this.animalTypeId = animalTypeId;
  this.categoryId = categoryId;
 } 
 public Products(int id,String name, int price, Blob img, String descript, int quantity, int specialPrice,
 String rewardpoints, boolean isThumb, int memberId, int animalTypeId, int categoryId,String filename) {
  this.id = id;
  this.name = name;
  this.price = price;
  this.img = img;
  this.descript = descript;
  this.quantity = quantity;
  this.specialPrice = specialPrice;
  this.rewardpoints = rewardpoints;
  this.isThumb = isThumb;
  this.memberId = memberId;
  this.animalTypeId = animalTypeId;
  this.categoryId = categoryId;
  this.filename = filename;
 }

 @Id @Column
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 public int getId() {
  return id;
 }
 public void setId(int id) {
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
 public int getPrice() {
  return price;
 }
 public void setPrice(int price) {
  this.price = price;
 }
// public String getImg() {
//  return img;
// }
// public void setImg(String img) {
//  this.img = img;
// }
 @Column(name = "IMG")
 public Blob getImg() {
  return img;
 }
 public void setImg(Blob img) {
  this.img = img;
 }
 @Column(name = "DESCRIPT")
 public String getDescript() {
  return descript;
 }
 public void setDescript(String descript) {
  this.descript = descript;
 }
 @Column(name = "QUANTITY")
 public int getQuantity() {
  return quantity;
 }
 public void setQuantity(int quantity) {
  this.quantity = quantity;
 }
 @Column(name = "SPECIAL_PRICE")
 public int getSpecialPrice() {
  return specialPrice;
 }
 public void setSpecialPrice(int specialPrice) {
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
 public int getMemberId() {
  return memberId;
 }
 public void setMemberId(int memberId) {
  this.memberId = memberId;
 }
 @Column(name = "ANIMAL_TYPE_ID")
 public int getAnimalTypeId() {
  return animalTypeId;
 }
 public void setAnimalTypeId(int animalTypeId) {
  this.animalTypeId = animalTypeId;
 }
 @Column(name = "CATEGORY_ID")
 public int getCategoryId() {
  return categoryId;
 }
 public void setCategoryId(int categoryId) {
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

}