package nn.vo;

import java.io.Serializable;
import java.util.Date;

public class AttractionBean implements Serializable {
	private int id;
	private String name;
	private int memberId;
	private int attractionTypeId;
	private String content;
	private String tel;
	private String email;
	private String address;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private int cityId;
	
	
	public AttractionBean() {};
	
	public AttractionBean(String name, int memberId, int attractionTypeId, String content, String tel,
			String email, String address, int cityId) {
		super();
		this.name = name;
		this.memberId = memberId;
		this.attractionTypeId = attractionTypeId;
		this.content = content;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.cityId = cityId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getAttractionTypeId() {
		return attractionTypeId;
	}
	public void setAttractionTypeId(int attractionTypeId) {
		this.attractionTypeId = attractionTypeId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Date getDeletedat() {
		return deletedAt;
	}
	public void setDeletedat(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
}
