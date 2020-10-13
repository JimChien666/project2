package Members;

import java.io.Serializable;

public class MemberBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int Id;
	private String name;
	private String sex;
	private String tel;
	private String email;
	private String address;
	private String income;
	private String account;
	private String password;
	private String memberType;
	
	
	public MemberBean() {
	}
	
	
	public MemberBean(String name, String income, String tel, String account, String password, String email,
			String address, String memberType, String sex) {
		super();
		this.Id = Id;
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

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
//	@Override
//	public String toString() {
//		return "ValueObjectMembers [Id=" + Id + ", name=" + name + ", income=" + income
//				+ ", tel=" + tel + ", account=" + account + ", password=" + password + ", email="
//				+ email + ", address=" + address + ", adoptedLevelId=" + adoptedLevelId + ", memberType=" + memberType
//				+ ", sex=" +sex + "]";
//	}
	
	

	

}
