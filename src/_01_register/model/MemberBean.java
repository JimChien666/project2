package _01_register.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;

public class MemberBean implements Serializable {
	private static final long serialVersionUID = 1L;
	int id;
	String type;
	String name;
	String sex;
	String tel;
	int income;
	String account;
	String password;
	String email;
	String address;
	int fileId;
	
	public int getFileId() {
		return fileId;
	}


	public void setFileId(int fileId) {
		this.fileId = fileId;
	}


	public MemberBean() {};
	
	
	public MemberBean(String type, String name, String sex, String tel, int income, String account,
			String password, String email, String address) {
		super();
		this.type = type;
		this.name = name;
		this.sex = sex;
		this.tel = tel;
		this.income = income;
		this.account = account;
		this.password = password;
		this.email = email;
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
