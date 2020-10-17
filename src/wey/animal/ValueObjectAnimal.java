package wey.animal;

import java.sql.Blob;
import java.util.Date;

public class ValueObjectAnimal {
	private int animalId;
	private int memberId;
	private String acceptionId;
	private int breedId;
	private int gender;
	private String coatColor;
	private int isAdoptionAvailable;
	private String note;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private String fileType;
	private String fileUrl;
	private Blob fileBlob;
	private String fileName;

	public ValueObjectAnimal() {
	}

	public ValueObjectAnimal(int animalId, int memberId, String acceptionId, int breedId, int gender, String coatColor,
			int isAdoptionAvailable, String note, Date createdAt, Date updatedAt, Date deletedAt, String fileType,
			String fileUrl, Blob fileBlob) {
		super();
		this.animalId = animalId;
		this.memberId = memberId;
		this.acceptionId = acceptionId;
		this.breedId = breedId;
		this.gender = gender;
		this.coatColor = coatColor;
		this.isAdoptionAvailable = isAdoptionAvailable;
		this.note = note;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedAt = deletedAt;
		this.fileType = fileType;
		this.fileUrl = fileUrl;
		this.fileBlob = fileBlob;
	}

	public int getAnimalId() {
		return animalId;
	}

	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getAcceptionId() {
		return acceptionId;
	}

	public void setAcceptionId(String acceptionId) {
		this.acceptionId = acceptionId;
	}

	public int getBreedId() {
		return breedId;
	}

	public void setBreedId(int breedId) {
		this.breedId = breedId;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getCoatColor() {
		return coatColor;
	}

	public void setCoatColor(String coatColor) {
		this.coatColor = coatColor;
	}

	public int getIsAdoptionAvailable() {
		return isAdoptionAvailable;
	}

	public void setIsAdoptionAvailable(int isAdoptionAvailable) {
		this.isAdoptionAvailable = isAdoptionAvailable;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
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

	public Blob getFileBlob() {
		return fileBlob;
	}

	public void setFileBlob(Blob fileBlob) {
		this.fileBlob = fileBlob;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
