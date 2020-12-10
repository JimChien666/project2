package com.iii.eeit124.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "activity_apply")

public class ActivityApply {
	@Id
	@Column(name = "apply_id")
	@SequenceGenerator(name = "activity_applySeqGen", sequenceName = "activity_apply_seq")
	@GeneratedValue(generator = "activity_applySeqGen")
	private Integer applyId;

	@Column(name = "activity_comment")
	private String activityComment;

	@Column(name = "apply_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date applyDate;

	@Column(name = "amount")
	private Integer amount;

	@Column(name = "member_id")
	private Integer memberId;

	@Column(name = "activitys_id")
	private Integer activitysId;

	@Column(name = "activitys_type")
	private String applyType;

	public Integer getApplyId() {
		return applyId;
	}

	public void setApplyId(Integer applyId) {
		this.applyId = applyId;
	}

	public String getActivityComment() {
		return activityComment;
	}

	public void setActivityComment(String activityComment) {
		this.activityComment = activityComment;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getActivitysId() {
		return activitysId;
	}

	public void setActivitysId(Integer activitysId) {
		this.activitysId = activitysId;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	@Override
	public String toString() {
		return "ActivityApply [applyId=" + applyId + ", activityComment=" + activityComment + ", applyDate=" + applyDate
				+ ", amount=" + amount + ", memberId=" + memberId + ", activitysId=" + activitysId + ", applyType="
				+ applyType + "]";
	}

}