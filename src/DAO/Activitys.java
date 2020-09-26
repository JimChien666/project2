package DAO;

import java.util.Date;

public class Activitys {

	private int id;
	private Date activeAt;
	private String name;
	private String topic;
	private String content;
	private String location;
	private int limitedNum;
	private int limitedType;
	
	/**
	 * 礚把计篶
	 */
	public Activitys() {
		
		
	}
	
	
	/**
	 * Τ把计篶
	 * @param id
	 * @param activeAt
	 * @param name
	 * @param topic
	 * @param content
	 * @param location
	 * @param limitedNum
	 * @param limitedType
	 */
	
	public Activitys(int id, Date activeAt, String name, String topic, String content, String location,
			int limitedNum, int limitedType) {
		super();
		this.id = id;
		this.activeAt = activeAt;
		this.name = name;
		this.topic = topic;
		this.content = content;
		this.location = location;
		this.limitedNum = limitedNum;
		this.limitedType = limitedType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getActiveAt() {
		return activeAt;
	}

	public void setActiveAt(Date activeAt) {
		this.activeAt = activeAt;
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

	public int getLimitedNum() {
		return limitedNum;
	}

	public void setLimitedNum(int limitedNum) {
		this.limitedNum = limitedNum;
	}

	public int getLimitedType() {
		return limitedType;
	}

	public void setLimitedType(int limitedType) {
		this.limitedType = limitedType;
	}


	@Override
	public String toString() {
		return "ActivitysDao [id=" + id + ", activeAt=" + activeAt + ", name=" + name + ", topic=" + topic
				+ ", content=" + content + ", location=" + location + ", limitedNum=" + limitedNum + ", limitedType="
				+ limitedType + "]";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}
