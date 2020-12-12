package com.iii.eeit124.activity.vo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.iii.eeit124.entity.Activitys;
import com.iii.eeit124.enums.ActivitysType;
import com.iii.eeit124.enums.LimitType;
import com.iii.eeit124.util.DateUtils;

public class ActivitysVo {

	private Integer id;

	/**
	 * 活動建立時間
	 */
	private String createDate;

	/**
	 * 活動時間 yyyy/MM/dd hh:mm:ss
	 */
	private String activityDate;

	/**
	 * 活動名稱
	 */
	private String name;

	/**
	 * 活動主題
	 */
	private String topic;

	/**
	 * 活動內容說明
	 */
	private String content;

	/**
	 * 位置
	 */
	private String location;

	/**
	 * 限制人數
	 */
	private Integer limitNum;

	/**
	 * 限制類型
	 */
	private String limitType;

	/**
	 * 限制人數
	 */
	private Integer limitPeople;

	/**
	 * 活動類型
	 */
	private String activitysType;

	/**
	 * 報名金額
	 */
	private Integer amount;

	/**
	 * 活動圖片
	 */
	private MultipartFile pic;

	private String base64Pic;

	public void validate() {
		if (getActivityDate() == null) {
			System.out.println("請填活動日期");
			throw new RuntimeException("請填活動日期");
		}

		if (StringUtils.isEmpty(getName())) {
			System.out.println("請填活動名稱");
			throw new RuntimeException("請填活動名稱");
		}

		if (StringUtils.isEmpty(getTopic())) {
			System.out.println("請填活動主題");
			throw new RuntimeException("請填活動主題");
		}

		if (StringUtils.isEmpty(getContent())) {
			System.out.println("請填活動內容");
			throw new RuntimeException("請填活動內容");
		}

		if (StringUtils.isEmpty(getLocation())) {
			System.out.println("請填活動地點");
			throw new RuntimeException("請填活動地點");
		}

		if (StringUtils.isEmpty(getActivitysType())) {
			System.out.println("請選擇活動類型");
			throw new RuntimeException("請選擇活動類型");
		}
	}

	public static ActivitysVo convert(Activitys activitys) {
		ActivitysVo result = new ActivitysVo();
		BeanUtils.copyProperties(activitys, result);
		result.setCreateDate(DateUtils.format(activitys.getCreateDate()));
		result.setActivityDate(DateUtils.format(activitys.getActivityDate()));
		result.setActivitysType(ActivitysType.findByCode(activitys.getActivitysType()).getDescription());
		result.setLimitType(LimitType.findByCode(activitys.getLimitType()).getDescription());

		byte[] bytes = activitys.getPic();
		if (bytes != null) {
			byte[] encodeBase64 = Base64.getEncoder().encode(bytes);
			String base64Encoded = "";
			try {
				base64Encoded = new String(encodeBase64, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			result.setBase64Pic(base64Encoded);
		}

		return result;
	}

	public Activitys toEntity() {
		Activitys result = new Activitys();
		BeanUtils.copyProperties(this, result);
		result.setCreateDate(new Date());
		result.setActivityDate(DateUtils.format(this.getActivityDate()));
		result.setActivitysType(ActivitysType.findByDesc(this.getActivitysType()).getCode());
		result.setLimitType(LimitType.findByDesc(this.getLimitType()).getCode());
		if (getPic() != null) {
			try {
				result.setPic(getPic().getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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

	public String getActivityDate() {
		return activityDate;
	}

	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;
	}

	public MultipartFile getPic() {
		return pic;
	}

	public void setPic(MultipartFile pic) {
		this.pic = pic;
	}

	public String getBase64Pic() {
		return base64Pic;
	}

	public void setBase64Pic(String base64Pic) {
		this.base64Pic = base64Pic;
	}

	@Override
	public String toString() {
		return "ActivitysVo [id=" + id + ", createDate=" + createDate + ", activityDate=" + activityDate + ", name="
				+ name + ", topic=" + topic + ", content=" + content + ", location=" + location + ", limitNum="
				+ limitNum + ", limitType=" + limitType + ", limitPeople=" + limitPeople + ", activitysType="
				+ activitysType + ", amount=" + amount + ", pic=" + pic + ", base64Pic=" + base64Pic + "]";
	}

}
