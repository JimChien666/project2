package wey.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import max.entity.MembersBean;

@Entity
@Table(name = "adoptionRecords")
public class AdoptionRecords {
	private int adoptionId;
	private int memberId;
	private int animalId;
	private Date adoptionDate;
	private String reviewStatus;
	private String adoptionStatus;
	private Date createAt;
	private Date updatedAt;
	private Date deleteAt;
	private MembersBean membersBean;
	private Animals animals;
	
	@Id @Column(name = "ADOPTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getAdoptionId() {
		return adoptionId;
	}
	public void setAdoptionId(int adoptionId) {
		this.adoptionId = adoptionId;
	}
	@Transient
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	@Transient
	public int getAnimalId() {
		return animalId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	@Column(name = "ADOPTION_DATE")
	public Date getAdoptionDate() {
		return adoptionDate;
	}
	public void setAdoptionDate(Date adoptionDate) {
		this.adoptionDate = adoptionDate;
	}
	@Column(name = "REVIEW_STATUS")
	public String getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	@Column(name = "ADOPTION_STATUS")
	public String getAdoptionStatus() {
		return adoptionStatus;
	}
	public void setAdoptionStatus(String adoptionStatus) {
		this.adoptionStatus = adoptionStatus;
	}
	@Column(name = "CREATED_AT")
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	@Column(name = "UPDATED_AT")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Column(name = "DELETED_AT")
	public Date getDeleteAt() {
		return deleteAt;
	}
	public void setDeleteAt(Date deleteAt) {
		this.deleteAt = deleteAt;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID")
	public MembersBean getMembersBean() {
		return membersBean;
	}
	public void setMembersBean(MembersBean membersBean) {
		this.membersBean = membersBean;
	}
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "animals", cascade = CascadeType.ALL)
	public Animals getAnimals() {
		return animals;
	}
	public void setAnimals(Animals animals) {
		this.animals = animals;
	}
}
