
package com.iii.eeit124.entity;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.stereotype.Component;

@Entity
@Table(name="MEMBERS")
@Component
public class Members implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String sex;
	private String tel;
	private String account;
	private String password;
	private String email;
	private String address;
	private Integer adoptedLevelId;
	private String memberType;
	private Date createdAt;
	private Set<MemberFiles> files = new HashSet<MemberFiles>();
//	private Set<Products> products = new HashSet<Products>();
//	private Set<Orders> orders = new HashSet<Orders>();
//	private Set<OrderItems> orderItems = new HashSet<OrderItems>();
//	private Set<Animals> animals = new HashSet<Animals>();
	

	

//	@JsonIgnore
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "member", cascade = CascadeType.ALL)
//	public Set<Products> getProducts() {
//		return products;
//	}
//
//
//
//	public void setProducts(Set<Products> products) {
//		this.products = products;
//	}



	public Members() {
	}
	
	

//	public Members(String name, String tel, String account, String password, String email,
//			String address, String memberType, String sex) {
//		super();
//		this.name = name;
//		this.tel = tel;
//		this.account = account;
//		this.password = password;
//		this.email = email;
//		this.address = address;
//		this.memberType = memberType;
//		this.sex = sex;
//	}
	
	public Members(String name, String sex, String tel, String account, String password, String email,
			String address, Integer adoptedLevelId, String memberType) {
		super();
		this.name = name;
		this.sex = sex;
		this.tel = tel;
		this.account = account;
		this.password = password;
		this.email = email;
		this.address = address;
		this.adoptedLevelId = adoptedLevelId;
		this.memberType = memberType;
	}



	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="MEMBERTYPE")
	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	@Column(name="NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name="SEX")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name="TEL")
	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	@Column(name="ACCOUNT")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	@Column(name="PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
//	@Column(name="ADOPTED_LEVEL_ID")
	@Transient
	public Integer getAdoptedLevelId() {
		return adoptedLevelId;
	}

	public void setAdoptedLevelId(Integer adoptedLevelId) {
		this.adoptedLevelId = adoptedLevelId;
	}

	@Transient
	public Integer getFileId() {
		Iterator<MemberFiles> iterator = this.files.iterator();
		Integer fileId = 0;
		if(iterator.hasNext()) {
			MemberFiles contentImg = iterator.next();
			fileId = contentImg.getId();
		}
		return fileId;
	}
	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "member", cascade = CascadeType.ALL)
	public Set<MemberFiles> getFiles() {
		return files;
	}

	public void setFiles(Set<MemberFiles> files) {
		this.files = files;
	}


	@Column(name = "CREATED_AT")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
//	public Set<Animals> getAnimals() {
//		return animals;
//	}
//
//	public void setAnimals(Set<Animals> animals) {
//		this.animals = animals;
//	}
	
//	@JsonIgnore
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "buyer", cascade = CascadeType.ALL)
//	public Set<Orders> getOrders() {
//		return orders;
//	}
//
//
//
//	public void setOrders(Set<Orders> orders) {
//		this.orders = orders;
//	}

//	@JsonIgnore
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "seller", cascade = CascadeType.ALL)
//	public Set<OrderItems> getOrderItems() {
//		return orderItems;
//	}
//
//
//
//	public void setOrderItems(Set<OrderItems> orderItems) {
//		this.orderItems = orderItems;
//	}
	
	
	
	
	
}
