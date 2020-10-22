package wey;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Id @Column(name = "ADOPTION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getAdoptionId() {
		return adoptionId;
	}
	public void setAdoptionId(int adoptionId) {
		this.adoptionId = adoptionId;
	}
	@Column(name = "ADOPTION_ID")
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	@Column(name = "ADOPTION_ID")
	public int getAnimalId() {
		return animalId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	@Column(name = "ADOPTION_ID")
	public Date getAdoptionDate() {
		return adoptionDate;
	}
	public void setAdoptionDate(Date adoptionDate) {
		this.adoptionDate = adoptionDate;
	}
	@Column(name = "ADOPTION_ID")
	public String getReviewStatus() {
		return reviewStatus;
	}
	public void setReviewStatus(String reviewStatus) {
		this.reviewStatus = reviewStatus;
	}
	@Column(name = "ADOPTION_ID")
	public String getAdoptionStatus() {
		return adoptionStatus;
	}
	public void setAdoptionStatus(String adoptionStatus) {
		this.adoptionStatus = adoptionStatus;
	}
	@Column(name = "ADOPTION_ID")
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	@Column(name = "ADOPTION_ID")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	@Column(name = "ADOPTION_ID")
	public Date getDeleteAt() {
		return deleteAt;
	}
	public void setDeleteAt(Date deleteAt) {
		this.deleteAt = deleteAt;
	}
}
