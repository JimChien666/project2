package DAO;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Members implements Serializable {
	private int id;
	private String name;
	private int income;
	private String phone;
	private String account;
	private String password;
	private String email;
	private String address;
	private int adoptedLevelId;
	private String memberType;
	private String sex;
//	public Members(int id, String name) {
//	this.id = id;
//	this.name = name;
//}
	public Members() {};

	public String getName() {
		return name;
	}
	public Members(String name, int income, String phone, String account, String password) {
	super();
	this.name = name;
	this.income = income;
	this.phone = phone;
	this.account = account;
	this.password = password;
}
	public void setName(String name) {
		this.name = name;
	}
	public int getIncome() {
		return income;
	}
	public void setIncome(int income) {
		this.income = income;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public void Email(String password) {
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
	public int getAdoptedLevelId() {
		return adoptedLevelId;
	}
	public void setAdoptedLevelId(int adoptedLevelId) {
		this.adoptedLevelId = adoptedLevelId;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	
	
}

	