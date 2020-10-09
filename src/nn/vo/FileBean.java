package nn.vo;

import java.io.Serializable;

public class FileBean implements Serializable {
	private int fileId;
	private String fileType;
	private String fileUrl;
	private int coverAttractionId;
	private int contentAttractionId;
	
	public FileBean() {};
	public FileBean(String fileType, String fileUrl, int coverAttractionId, int contentAttractionId) {
		super();
		this.fileType = fileType;
		this.fileUrl = fileUrl;
		this.coverAttractionId = coverAttractionId;
		this.contentAttractionId = contentAttractionId;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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
	public int getCoverAttractionId() {
		return coverAttractionId;
	}
	public void setCoverAttractionId(int coverAttractionId) {
		this.coverAttractionId = coverAttractionId;
	}
	public int getContentAttractionId() {
		return contentAttractionId;
	}
	public void setContentAttractionId(int contentAttractionId) {
		this.contentAttractionId = contentAttractionId;
	}
	
	
	
	
}
