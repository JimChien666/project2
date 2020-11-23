package com.iii.eeit124.entity;

import java.util.Date;
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

import org.springframework.stereotype.Component;

@Entity
@Table(name = "adoptionRecords")
@Component
public class AdoptionRecords {

	private Integer adoptionId;
	private Integer memberId;//多對一
	private Integer animalId;//一對一
	private Date adoptionDate;
	private String reviewStatus;//審核狀態
	private Integer adoptionStatus;//1.領養中 2.送養中 3.已送養
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private Members member;
	private Animals animal;
	
	@Id
	@Column(name = "ADOPTION_ID")//大小寫沒差別，跟資料庫名一樣就好
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAdoptionId() {
		return adoptionId;
	}
	public void setAdoptionId(Integer adoptionId) {
		this.adoptionId = adoptionId;
	}
//	@Column(name = "Member_Id")
	@Transient
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
//	@Column(name = "Animal_Id")
	@Transient
	public Integer getAnimalId() {
		return animalId;
	}
	public void setAnimalId(Integer animalId) {
		this.animalId = animalId;
	}
	@Column(name = "Adoption_Date")
	public Date getAdoptionDate() {
		return adoptionDate;
	}
	public void setAdoptionDate(Date adoptionDate) {
		this.adoptionDate = adoptionDate;
	}
	@Column(name = "Review_Status")
	public String getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	@Column(name = "Adoption_Status")
	public Integer getAdoptionStatus() {
		return adoptionStatus;
	}
	public void setAdoptionStatus(Integer adoptionStatus) {
		this.adoptionStatus = adoptionStatus;
	}
	@Column(name = "Created_At")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	@Column(name = "Updated_At")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Column(name = "Deleted_At")
	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	@ManyToOne(fetch = FetchType.LAZY, targetEntity=Members.class)
	@JoinColumn(name = "MEMBER_ID")
	public Members getMember() {
		return member;
	}
	public void setMember(Members member) {
		this.member = member;
	}
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="Animal_Id", referencedColumnName="Animal_Id")
	public Animals getAnimal() {
		return animal;
	}
	public void setAnimal(Animals animal) {
		this.animal = animal;
	}
	
	@Override
	public String toString() {
		return "AdoptionRecords [adoptionId=" + adoptionId + ", memberId=" + memberId + ", animalId=" + animalId
				+ ", adoptionDate=" + adoptionDate + ", reviewStatus=" + reviewStatus + ", adoptionStatus="
				+ adoptionStatus + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt
				+ ", member=" + member + ", animal=" + animal + "]";
	}
}
