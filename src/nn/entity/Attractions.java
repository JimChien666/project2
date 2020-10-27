package nn.entity;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ATTRACTIONS")
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
	private int memberId;

	private AttractionTypes attractionType;
	private Citys city;
	private Set<Tags> tags = new HashSet<Tags>();
	private Set<AttractionComments> attractionComments = new HashSet<AttractionComments>();
//	private Set<Files> files = new HashSet<Files>();
	private Set<Files> contentImgs = new HashSet<Files>();
	private Set<Files> coverImgs = new HashSet<Files>();
	
	public Attractions() {};

	public Attractions(String name, Clob content, String tel, String email, String address, Date createdAt) {
		super();
		this.name = name;
		this.content = content;
		this.tel = tel;
		this.email = email;
		this.address = address;
		this.createdAt = createdAt;
	}

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

	@Column(name = "CREATED_AT")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "UPDATED_AT")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Column(name = "DELETED_AT")
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

	@Column(name = "MEMBER_ID")
	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CITY_ID")
	public Citys getCity() {
		return city;
	}

	public void setCity(Citys city) {
		this.city = city;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ATTRACTION_TYPE_ID")
	public AttractionTypes getAttractionType() {
		return attractionType;
	}

	public void setAttractionType(AttractionTypes attractionType) {
		this.attractionType = attractionType;
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "ATTRACTION_TAG_REFS", joinColumns = {
			@JoinColumn(name = "ATTRACTION_ID") }, inverseJoinColumns = { @JoinColumn(name = "TAG_ID") })
	public Set<Tags> getTags() {
		return tags;
	}

	public void setTags(Set<Tags> tags) {
		this.tags = tags;
	}
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "attraction", cascade = CascadeType.ALL)
	public Set<AttractionComments> getAttractionComments() {
		return attractionComments;
	}

	public void setAttractionComments(Set<AttractionComments> attractionComments) {
		this.attractionComments = attractionComments;
	}

	@OneToMany(fetch = FetchType.LAZY, targetEntity=Files.class, cascade = CascadeType.ALL)
	@JoinColumns(value = { @JoinColumn(name="CONTENT_ATTRACTION_ID",referencedColumnName="ID")})
	public Set<Files> getContentImgs() {
		return contentImgs;
	}

	
	public void setContentImgs(Set<Files> contentImgs) {
		this.contentImgs = contentImgs;
	}
	@OneToMany(fetch = FetchType.LAZY, targetEntity=Files.class, cascade = CascadeType.ALL)
	@JoinColumns(value = { @JoinColumn(name="COVER_ATTRACTION_ID",referencedColumnName="ID")})
	public Set<Files> getCoverImgs() {
		return coverImgs;
	}
	
	public void setCoverImgs(Set<Files> coverImgs) {
		this.coverImgs = coverImgs;
	}

	@Override
	public String toString() {
		return " {\"id\":\"" + id + "\", \"name\":\"" + name + "\", \"content\":\"" + content + "\", \"tel\":\"" + tel
				+ "\", \"email\":\"" + email + "\", \"address\":\"" + address + "\", \"createdAt\":\"" + createdAt
				+ "\", \"city\":" + city + ", \"tags\":" + tags + ", \"contentImgs\":" + contentImgs
				+ ", \"coverImgs\":" + coverImgs + "}";
	}

	

	@Transient
	public int getFirstCoverImgId() {
		Iterator<Files> iterator = this.coverImgs.iterator();
		int fileId = 0;
		if(iterator.hasNext()) {
			Files coverImg = iterator.next();
			fileId = coverImg.getId();
		}
		return fileId;
	}
	
	@Transient
	public int getFirstContentImgId() {
		Iterator<Files> iterator = this.contentImgs.iterator();
		int fileId = 0;
		if(iterator.hasNext()) {
			Files contentImg = iterator.next();
			fileId = contentImg.getId();
		}
		return fileId;
	}
}
