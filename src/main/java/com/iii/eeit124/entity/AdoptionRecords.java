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
	//領養注意事項
	private Integer noticeOptions;
	//領養申請
	private String applicantName;
	private Integer agreement;
	private String feedAddress;
	private String feedAddressType;
	private Integer currentAnimalsNum;
	private String adopterName;
	private String personalId;
	private Date birthday;
	private String birthdayString;
	private String tel;
	private String mobile;
	private String email;
	private String residentAddress;
	private String mailingAddress;
	
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
	
	//20201201加入領養注意與申請欄位
	@Column(name = "Notice_Options")
	public Integer getNoticeOptions() {
		return noticeOptions;
	}
	public void setNoticeOptions(Integer noticeOptions) {
		this.noticeOptions = noticeOptions;
	}
	@Column(name = "Applicant_Name")
	public String getApplicantName() {
		return applicantName;
	}
	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}
	@Column(name = "Agreement")
	public Integer getAgreement() {
		return agreement;
	}
	public void setAgreement(Integer agreement) {
		this.agreement = agreement;
	}
	@Column(name = "FeedAddress")
	public String getFeedAddress() {
		return feedAddress;
	}
	public void setFeedAddress(String feedAddress) {
		this.feedAddress = feedAddress;
	}
	@Column(name = "FeedAddress_Type")
	public String getFeedAddressType() {
		return feedAddressType;
	}
	public void setFeedAddressType(String feedAddressType) {
		this.feedAddressType = feedAddressType;
	}
	@Column(name = "Current_Animals_Num")
	public Integer getCurrentAnimalsNum() {
		return currentAnimalsNum;
	}
	public void setCurrentAnimalsNum(Integer currentAnimalsNum) {
		this.currentAnimalsNum = currentAnimalsNum;
	}
	@Column(name = "Adopter_Name")
	public String getAdopterName() {
		return adopterName;
	}
	public void setAdopterName(String adopterName) {
		this.adopterName = adopterName;
	}
	@Column(name = "Personal_Id")
	public String getPersonalId() {
		return personalId;
	}
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}
	@Column(name = "Birthday")
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getBirthdayString() {
		return birthdayString;
	}
	public void setBirthdayString(String birthdayString) {
		this.birthdayString = birthdayString;
	}
	@Column(name = "Tel")
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name = "Mobile")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name = "Email")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "Resident_Address")
	public String getResidentAddress() {
		return residentAddress;
	}
	public void setResidentAddress(String residentAddress) {
		this.residentAddress = residentAddress;
	}
	@Column(name = "Mailing_Address")
	public String getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	@Override
	public String toString() {
		return "AdoptionRecords [adoptionId=" + adoptionId + ", memberId=" + memberId + ", animalId=" + animalId
				+ ", adoptionDate=" + adoptionDate + ", reviewStatus=" + reviewStatus + ", adoptionStatus="
				+ adoptionStatus + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deletedAt=" + deletedAt
				+ ", member=" + member + ", animal=" + animal + ", noticeOptions=" + noticeOptions + ", applicantName="
				+ applicantName + ", agreement=" + agreement + ", feedAddress=" + feedAddress + ", feedAddressType="
				+ feedAddressType + ", currentAnimalsNum=" + currentAnimalsNum + ", adopterName=" + adopterName
				+ ", personalId=" + personalId + ", birthday=" + birthday + ", birthdayString=" + birthdayString
				+ ", tel=" + tel + ", mobile=" + mobile + ", email=" + email + ", residentAddress=" + residentAddress
				+ ", mailingAddress=" + mailingAddress + "]";
	}
}
