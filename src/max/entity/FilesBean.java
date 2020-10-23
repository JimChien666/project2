package max.entity;

import java.io.Serializable;
import java.sql.Blob;

public class FilesBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int fileId;
	private String fileType;
	private String fileURL;
	private int memberId;
	private int forumId;
	private int animalId;
	private int activityId;
	private int productId;
	private int contentAttractionId;
	private Blob fileBlob;
	private int coverAttractionId;
	
	public FilesBean() {
	}
	
	public FilesBean(int fileId, String fileType, String fileURL, int memberId, int forumId, int animalId,
			int activityId, int productId, int contentAttractionId, Blob fileBlob, int coverAttractionId) {
		super();
		this.fileId = fileId;
		this.fileType = fileType;
		this.fileURL = fileURL;
		this.memberId = memberId;
		this.forumId = forumId;
		this.animalId = animalId;
		this.activityId = activityId;
		this.productId = productId;
		this.contentAttractionId = contentAttractionId;
		this.fileBlob = fileBlob;
		this.coverAttractionId = coverAttractionId;
	}


	
	
	
	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileURL() {
		return fileURL;
	}

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getForumId() {
		return forumId;
	}

	public void setForumId(int forumId) {
		this.forumId = forumId;
	}

	public int getAnimalId() {
		return animalId;
	}

	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getContentAttractionId() {
		return contentAttractionId;
	}

	public void setContentAttractionId(int contentAttractionId) {
		this.contentAttractionId = contentAttractionId;
	}

	public Blob getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(Blob fileBlob) {
		this.fileBlob = fileBlob;
	}

	public int getCoverAttractionId() {
		return coverAttractionId;
	}

	public void setCoverAttractionId(int coverAttractionId) {
		this.coverAttractionId = coverAttractionId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
