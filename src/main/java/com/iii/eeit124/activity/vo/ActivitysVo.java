package com.iii.eeit124.activity.vo;

import org.springframework.beans.BeanUtils;

import com.iii.eeit124.entity.Activitys;
import com.iii.eeit124.enums.ActivitysType;
import com.iii.eeit124.enums.LimitType;
import com.iii.eeit124.util.DateUtils;

public class ActivitysVo {

	private Integer id;

	private String createDate;

	private String name;

	private String topic;

	private String content;

	private String location;

	private Integer limitNum;

	private String limitType;

	private Integer limitPeople;

	private String activitysType;

	private Integer amount;

	public static ActivitysVo convert(Activitys activitys) {
		ActivitysVo result = new ActivitysVo();
		BeanUtils.copyProperties(activitys, result);
		result.setCreateDate(DateUtils.format(activitys.getCreateDate()));
		result.setActivitysType(ActivitysType.findByCode(activitys.getActivitysType()).getDescription());
		result.setLimitType(LimitType.findByCode(activitys.getLimitType()).getDescription());
		return result;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(Integer limitNum) {
		this.limitNum = limitNum;
	}

	public String getLimitType() {
		return limitType;
	}

	public void setLimitType(String limitType) {
		this.limitType = limitType;
	}

	public Integer getLimitPeople() {
		return limitPeople;
	}

	public void setLimitPeople(Integer limitPeople) {
		this.limitPeople = limitPeople;
	}

	public String getActivitysType() {
		return activitysType;
	}

	public void setActivitysType(String activitysType) {
		this.activitysType = activitysType;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}
