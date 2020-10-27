package max.entity;


import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import jim.entity.Products;
import max.model.Members;
import team6.johnny.model.Forums;
import wey.entity.Animals;
import wey.entity.Breeds;
@Entity
@Table(name = "files")
public class Files{
	private int fileId;
	private String fileType;
	private String fileUrl;
	private int memberId;
	private int forumId;
	private int animalId;
	private int activityId;
	private int productId;
	private int contentAttractionId;
	private Blob fileBlob;
	private int coverAttractionId;
	
	//Members
	private Members members;
	//Forums
	private Forums forums;
//	//Activitys
//	private Activitys activitys;
	//Products
	private Products products;
	//Animals
	private Animals animals;
	
	
	@Id @Column(name = "FILE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	@Column(name = "FILE_TYPE")
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Column(name = "FILE_URL")
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	@Transient
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	@Transient
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	@Transient
	public int getAnimalId() {
		return animalId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	@Transient
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	@Transient
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	@Transient
	public int getContentAttractionId() {
		return contentAttractionId;
	}
	public void setContentAttractionId(int contentAttractionId) {
		this.contentAttractionId = contentAttractionId;
	}
	@Column(name = "FILE_BLOB")
	public Blob getFileBlob() {
		return fileBlob;
	}
	public void setFileBlob(Blob fileBlob) {
		this.fileBlob = fileBlob;
	}
	@Transient
	public int getCoverAttractionId() {
		return coverAttractionId;
	}
	public void setCoverAttractionId(int coverAttractionId) {
		this.coverAttractionId = coverAttractionId;
	}

	//關聯
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID")
	public Members getMembers() {
		return members;
	}
	public void setMembers(Members members) {
		this.members = members;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID")
	public Forums getForums() {
		return forums;
	}
	public void setForums(Forums forums) {
		this.forums = forums;
	}
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ACTIVITYS_ID")
//	public Activitys getActivitys() {
//		return activitys;
//	}
//	public void setActivitys(Activitys activitys) {
//		this.activitys = activitys;
//	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID")
	public Products getProducts() {
		return products;
	}
	public void setProducts(Products products) {
		this.products = products;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ANIMAL_ID")
	public Animals getAnimals() {
		return animals;
	}
	public void setBreeds(Animals animals) {
		this.animals = animals;	
	}
}
