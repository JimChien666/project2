package team6.nn.model;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Attractions")
public class Attractions implements Serializable {
	private int id;
	private String name;
	private Clob content;
	private String tel;
	private String email;
	private String address;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	private int AttractionTypeId;
	private int cityId;
	private AttractionTypes attractionType;
	private Citys city;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CONTENT")
	public Clob getContent() {
		return content;
	}

	public void setContent(Clob content) {
		this.content = content;
	}

	@Column(name = "TEL")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "CREATEDAT")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "UPDATEDAT")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "DELETEDAT")
	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

	@Transient
	public int getAttractionTypeId() {
		return AttractionTypeId;
	}

	public void setAttractionTypeId(int attractionTypeId) {
		AttractionTypeId = attractionTypeId;
	}

	@Transient
	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID")
	public Citys getCity() {
		return city;
	}

	public void setCity(Citys city) {
		this.city = city;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID")
	public AttractionTypes getAttractionType() {
		return attractionType;
	}

	public void setAttractionType(AttractionTypes attractionType) {
		this.attractionType = attractionType;
	}
}
