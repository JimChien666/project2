package com.iii.eeit124.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="FILES")
public class Files implements Serializable {
	private Integer id;
	private String FileType;
	private String FileUrl;
	private Integer memberId;
	private Integer forumId;
	private Integer animalId;
	private Integer activityId;
	private Integer productId;
	private Integer contentAttractionId;
	private Integer coverAttractionId;
	private Members member;
	

	private Blob fileBlob;
	
	
	public Files() {};
	
	public Files(String fileType, Blob fileBlob) {
		super();
		FileType = fileType;
		this.fileBlob = fileBlob;
	}
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@SequenceGenerator(name = "filesSeqGen", sequenceName = "FILES_SEQ2", allocationSize = 1, initialValue = 2301)
//	@GeneratedValue(generator = "filesSeqGen", strategy = GenerationType.SEQUENCE)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="FILE_TYPE")
	public String getFileType() {
		return FileType;
	}
	public void setFileType(String fileType) {
		FileType = fileType;
	}
	@Column(name="FILE_URL")
	public String getFileUrl() {
		return FileUrl;
	}
	public void setFileUrl(String fileUrl) {
		FileUrl = fileUrl;
	}
	@Transient
//	@Column(name="MEMBER_ID")
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	@Column(name="FORUM_ID")
	public Integer getForumId() {
		return forumId;
	}
	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}
	@Column(name="ANIMAL_ID")
	public Integer getAnimalId() {
		return animalId;
	}
	public void setAnimalId(Integer animalId) {
		this.animalId = animalId;
	}
	@Column(name="ACTIVITY_ID")
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	@Column(name="PRODUCT_ID")
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	@Column(name="CONTENT_ATTRACTION_ID")
	public Integer getContentAttractionId() {
		return contentAttractionId;
	}
	public void setContentAttractionId(Integer contentAttractionId) {
		this.contentAttractionId = contentAttractionId;
	}
	@Column(name="COVER_ATTRACTION_ID")
	public Integer getCoverAttractionId() {
		return coverAttractionId;
	}
	public void setCoverAttractionId(Integer coverAttractionId) {
		this.coverAttractionId = coverAttractionId;
	}
	@Column(name="FILE_BLOB")
	public Blob getFileBlob() {
		return fileBlob;
	}
	public void setFileBlob(Blob fileBlob) {
		this.fileBlob = fileBlob;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID")
	public Members getMember() {
		return member;
	}

	public void setMember(Members member) {
		this.member = member;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(" {\"id\":\"").append(id).append("\", \"FileType\":\"").append(FileType)
				.append("\", \"FileUrl\":\"").append(FileUrl).append("\", \"memberId\":\"").append(memberId)
				.append("\", \"forumId\":\"").append(forumId).append("\", \"animalId\":\"").append(animalId)
				.append("\", \"activityId\":\"").append(activityId).append("\", \"productId\":\"").append(productId)
				.append("\", \"contentAttractionId\":\"").append(contentAttractionId)
				.append("\", \"coverAttractionId\":\"").append(coverAttractionId).append("\", \"member\":\"")
				.append(member).append("\", \"fileBlob\":\"").append("").append("}");
		return builder.toString();
	}
}
