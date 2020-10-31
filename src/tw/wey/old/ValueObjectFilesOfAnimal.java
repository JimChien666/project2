package tw.wey.old;

import java.sql.Blob;

public class ValueObjectFilesOfAnimal {
	private String fileType;
	private String fileUrl;
	private int animalId;
	private Blob fileBlob;
	
	public ValueObjectFilesOfAnimal() {	
	}

	public ValueObjectFilesOfAnimal(String fileType, String fileUrl, int animalId, Blob fileBlob) {
		super();
		this.fileType = fileType;
		this.fileUrl = fileUrl;
		this.animalId = animalId;
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

	public int getAnimalId() {
		return animalId;
	}

	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	public Blob getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(Blob fileBlob) {
		this.fileBlob = fileBlob;
	}
}
