package com.iii.eeit124.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "animals")//大小寫沒差別，跟資料庫名一樣就好
@Component
public class Animals {
	
	private Integer animalId;//TODO int Integer會出錯
	private Integer memberId;
	private String acceptionId;
	private Integer breedId;
	private Integer gender;
	private String coatColor;
	private Integer isAdoptionAvailable;
	private String note;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private MultipartFile animalFiles;
//	private AdoptionRecords adoptionRecords;
//	private MembersBean membersBean;
//	private Breeds breeds;
	private Set<Files> files = new HashSet<Files>();
	
	public Animals () {}
	
//	@SequenceGenerator(name = "activitysSeqGen", sequenceName = "activitys_seq", allocationSize = 1)
//	@GeneratedValue(generator = "activitysSeqGen", strategy = GenerationType.SEQUENCE)
	@Id
	@Column(name = "ANIMAL_ID")//大小寫沒差別，跟資料庫名一樣就好
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAnimalId() {
		return animalId;
	}
	public void setAnimalId(Integer animalId) {
		this.animalId = animalId;
	}

	@Column(name = "MEMBER_ID")
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
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
	public Integer getBreedId() {
		return breedId;
	}
	public void setBreedId(Integer breedId) {
		this.breedId = breedId;
	}

	@Column(name = "GENDER")
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
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
	public Integer getIsAdoptionAvailable() {
		return isAdoptionAvailable;
	}
	public void setIsAdoptionAvailable(Integer isAdoptionAvailable) {
		this.isAdoptionAvailable = isAdoptionAvailable;
	}

	@Column(name = "NOTE")
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	@Column(name = "CREATED_AT", updatable = false)
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
	
	@Transient
	public MultipartFile getAnimalFiles() {
		return animalFiles;
	}
	public void setAnimalFiles(MultipartFile animalFiles) {
		this.animalFiles = animalFiles;
	}
	
//	@OneToOne(fetch = FetchType.LAZY, mappedBy = "animals", cascade = CascadeType.ALL)
//	public AdoptionRecords getAdoptionRecords() {
//		return adoptionRecords;
//	}
//	public void setAdoptionRecords(AdoptionRecords adoptionRecords) {
//		this.adoptionRecords=adoptionRecords;
//	}
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "ID")
//	public MembersBean getMembersBean() {
//		return membersBean;
//	}
//	public void setMembersBean(MembersBean membersBean) {
//		this.membersBean = membersBean;
//	}
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "BREED_ID")
//	public Breeds getBreeds() {
//		return breeds;
//	}
//	public void setBreeds(Breeds breeds) {
//		this.breeds = breeds;
//	}
	@OneToMany(fetch = FetchType.LAZY, targetEntity=Files.class, cascade = CascadeType.ALL)
	@JoinColumns(value = { @JoinColumn(name="ANIMAL_ID",referencedColumnName="ANIMAL_ID")})//第一個ANIMAL_ID為Files的，第二個為Animals的。
	public Set<Files> getFiles() {
		return files;
	}
	public void setFiles(Set<Files> files) {
		this.files = files;
	}
	
	@Override
	public String toString() {
		return "Animals [animalId=" + animalId + ", memberId=" + memberId + ", acceptionId=" + acceptionId
				+ ", breedId=" + breedId + ", gender=" + gender + ", coatColor=" + coatColor + ", isAdoptionAvailable="
				+ isAdoptionAvailable + ", note=" + note + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ ", deletedAt=" + deletedAt + ", files=" + files + ", animalFiles=" + animalFiles + "]";
	}
}
