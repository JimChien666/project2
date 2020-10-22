package wey;

import java.util.Date;
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

@Entity
@Table(name = "animals")
public class Animals {
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
	
	@Id @Column(name = "ANIMAL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getAnimalId() {
		return animalId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	@Transient
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	@Column(name = "ACCEPTION_ID")
	public String getAcceptionId() {
		return acceptionId;
	}
	public void setAcceptionId(String acceptionId) {
		this.acceptionId = acceptionId;
	}
	@Column(name = "BREED_ID")
	public int getBreedId() {
		return breedId;
	}
	public void setBreedId(int breedId) {
		this.breedId = breedId;
	}
	@Column(name = "GENDER")
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	@Column(name = "COAT_COLOR")
	public String getCoatColor() {
		return coatColor;
	}
	public void setCoatColor(String coatColor) {
		this.coatColor = coatColor;
	}
	@Column(name = "IS_ADOPTION_AVAILABLE")
	public int getIsAdoptionAvailable() {
		return isAdoptionAvailable;
	}
	public void setIsAdoptionAvailable(int isAdoptionAvailable) {
		this.isAdoptionAvailable = isAdoptionAvailable;
	}
	@Column(name = "NOTE")
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Column(name = "CREATED_AT")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	@Column(name = "UPDATED_AT")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Column(name = "DELETED_AT")
	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ID")
//	public MemberBean getMemberBean() {
//		return memberBean;
//	}
}
