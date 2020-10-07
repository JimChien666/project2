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
	private Date deletedat;
	private int cityId;
	
	
	public AttractionBean() {};
	
	public AttractionBean(int id, String name, int memberId, int attractionTypeId, String content, String tel,
			String email, String attress, Date createdAt, Date updatedAt, Date deletedat, int cityId) {
		super();
		this.id = id;
		this.name = name;
		this.memberId = memberId;
		this.attractionTypeId = attractionTypeId;
		this.content = content;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.deletedat = deletedat;
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
		return deletedat;
	}
	public void setDeletedat(Date deletedat) {
		this.deletedat = deletedat;
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	
}
