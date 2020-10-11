package com.iii.eeit124.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "activitys")
public class Activitys {

	@Id
	@GeneratedValue
	@Column(name = "activitys_id")
	private Integer id;

	@Column(name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Column(name = "name")
	private String name;

	@Column(name = "topic")
	private String topic;

	@Column(name = "content")
	private String content;

	@Column(name = "location")
	private String location;

	@Column(name = "limit_num")
	private Integer limitNum;

	@Column(name = "limit_type")
	private String limitType;

	@Column(name = "limit_people")
	private Integer limitPeople;

	@Column(name = "activitys_type")
	private String activitysType;

	@Column(name = "amount")
	private Integer amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
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

	@Override
	public String toString() {
		return "Activitys [id=" + id + ", createDate=" + createDate + ", name=" + name + ", topic=" + topic
				+ ", content=" + content + ", location=" + location + ", limitNum=" + limitNum + ", limitType="
				+ limitType + ", limitPeople=" + limitPeople + ", activitysType=" + activitysType + ", amount=" + amount
				+ "]";
	}
	
}
