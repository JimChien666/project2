package DAO;

import java.util.Date;

public class AdoptedRecords {
	private int id;
	private int memberId;
	private Date adoptedAt;
	private Date unadoptedAt;
	private int animalId;
	private String status;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public Date getAdoptedAt() {
		return adoptedAt;
	}
	public void setAdoptedAt(Date adoptedAt) {
		this.adoptedAt = adoptedAt;
	}
	public Date getUnadoptedAt() {
		return unadoptedAt;
	}
	public void setUnadoptedAt(Date unadoptedAt) {
		this.unadoptedAt = unadoptedAt;
	}
	public int getAnimalId() {
		return animalId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
