package wey.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.annotation.MultipartConfig;

@Entity
@Table(name = "animals")
@MultipartConfig
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
//	private AdoptionRecords adoptionRecords;
//	private MembersBean membersBean;
//	private Breeds breeds;
//	private Set<FilesBean> filesBean;
	
	public Animals() {
	}
	
	public Animals(int animalId, int memberId, String acceptionId, int breedId, int gender, String coatColor,
			int isAdoptionAvailable, String note, Date createdAt, Date updatedAt, Date deletedAt) {
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
	}
	
	@Id @Column(name = "ANIMAL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getAnimalId() {
		return animalId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	@Column(name = "MEMBER_ID")
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
//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "files")
//	public Set<FilesBean> getFilesBean() {
//		return filesBean;
//	}
//	public void setFilesBean(Set<FilesBean> filesBean) {
//		this.filesBean = filesBean;
//	}
}
