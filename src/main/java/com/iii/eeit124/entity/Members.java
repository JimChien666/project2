package com.iii.eeit124.entity;

import java.io.Serializable;
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
import javax.persistence.Transient;


@Entity
@Table(name="MEMBERS")
public class Members implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String sex;
	private String tel;
	private int income;
	private String account;
	private String password;
	private String email;
	private String address;
	private int adoptedLevelId;
	private String memberType;
	private Set<Files> files = new HashSet<Files>();


	public Members() {
	}
	
	public Members(String name, int income, String tel, String account, String password, String email,
			String address, String memberType, String sex) {
		super();
		this.name = name;
		this.income = income;
		this.tel = tel;
		this.account = account;
		this.password = password;
		this.email = email;
		this.address = address;
		this.memberType = memberType;
		this.sex = sex;
	}
	
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	@Column(name="INCOME")
	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
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
	public int getAdoptedLevelId() {
		return adoptedLevelId;
	}

	public void setAdoptedLevelId(int adoptedLevelId) {
		this.adoptedLevelId = adoptedLevelId;
	}

	@Transient
	public int getFileId() {
		Iterator<Files> iterator = this.files.iterator();
		int fileId = 0;
		if(iterator.hasNext()) {
			Files contentImg = iterator.next();
			fileId = contentImg.getId();
		}
		return fileId;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "member", cascade = CascadeType.ALL)
	public Set<Files> getFiles() {
		return files;
	}

	public void setFiles(Set<Files> files) {
		this.files = files;
	}
	
	
	
	
	
}
