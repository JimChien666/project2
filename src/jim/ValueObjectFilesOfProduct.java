package jim;

import java.sql.Blob;

public class ValueObjectFilesOfProduct {
	private String fileType;
	private String fileUrl;
	private int productId;
	private Blob fileBlob;
	
	public ValueObjectFilesOfProduct() {	
	}

	public ValueObjectFilesOfProduct(String fileType, String fileUrl, int productId, Blob fileBlob) {
		super();
		this.fileType = fileType;
		this.fileUrl = fileUrl;
		this.productId = productId;
		this.fileBlob = fileBlob;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Blob getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(Blob fileBlob) {
		this.fileBlob = fileBlob;
	}
}
