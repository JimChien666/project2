package com.iii.eeit124.activity.vo;

import org.springframework.beans.BeanUtils;

import com.iii.eeit124.entity.ActivityApply;
import com.iii.eeit124.enums.ApplyType;
import com.iii.eeit124.util.DateUtils;

public class ActivityApplyVo {

	private Integer applyId;

	private String activityComment;

	private String applyDate;

	private Integer amount;

	private String applyType;

	private Integer memberId;

	private Integer activitysId;

	public static ActivityApplyVo convert(ActivityApply entity) {
		ActivityApplyVo result = new ActivityApplyVo();
		BeanUtils.copyProperties(entity, result);
		result.setApplyDate(DateUtils.format(entity.getApplyDate()));
		result.setApplyType(ApplyType.findByCode(entity.getApplyType()).getCode());
		return result;
	}

	public ActivityApply toEntity() {
		ActivityApply result = new ActivityApply();
		BeanUtils.copyProperties(this, result);
		result.setApplyDate(DateUtils.format(this.getApplyDate()));
		result.setApplyType(ApplyType.PARTICIPANT.getCode());
		return result;
	}

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

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
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

}
