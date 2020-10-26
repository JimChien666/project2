package team6.nn.entity;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FILES")
public class Files implements Serializable {
	private int id;
	private String FileType;
	private String FileUrl;
	private int memberId;
	private int forumId;
	private int animalId;
	private int activityId;
	private int productId;
	private int contentAttractionId;
	private int coverAttractionId;
	private Blob fileBlob;
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	@Column(name="MEMBER_ID")
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	@Column(name="FORUM_ID")
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	@Column(name="ANIMAL_ID")
	public int getAnimalId() {
		return animalId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	@Column(name="ACTIVITY_ID")
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	@Column(name="PRODUCT_ID")
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	@Column(name="CONTENT_ATTRACTION_ID")
	public int getContentAttractionId() {
		return contentAttractionId;
	}
	public void setContentAttractionId(int contentAttractionId) {
		this.contentAttractionId = contentAttractionId;
	}
	@Column(name="COVER_ATTRACTION_ID")
	public int getCoverAttractionId() {
		return coverAttractionId;
	}
	public void setCoverAttractionId(int coverAttractionId) {
		this.coverAttractionId = coverAttractionId;
	}
	@Column(name="FILE_BLOB")
	public Blob getFileBlob() {
		return fileBlob;
	}
	public void setFileBlob(Blob fileBlob) {
		this.fileBlob = fileBlob;
	}
}
