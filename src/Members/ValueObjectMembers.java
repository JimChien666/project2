package Members;
import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValueObjectMembers  {
	private int Id;
	private String name;
	private String income;
	private String tel;
	private String account;
	private String password;
	private String email;
	private String address;
	private int adoptedLevelId;
	private String memberType;
	private String sex;
	
	public ValueObjectMembers() {
	}
	
	public ValueObjectMembers(int Id, String name, String income, String tel, String account, String password,
			String email, String address, int adoptedLevelId, String memberType, String sex) {
		super();
		this.Id = Id;
		this.name = name;
		this.income = income;
		this.tel = tel;
		this.account = account;
		this.password = password;
		this.email = email;
		this.address = address;
		this.adoptedLevelId = adoptedLevelId;
		this.memberType = memberType;
		this.sex = sex;
	}
	
	@Override
	public String toString() {
		return "ValueObjectMembers [Id=" + Id + ", name=" + name + ", income=" + income
				+ ", tel=" + tel + ", account=" + account + ", password=" + password + ", email="
				+ email + ", address=" + address + ", adoptedLevelId=" + adoptedLevelId + ", memberType=" + memberType
				+ ", sex=" +sex + "]";
	}
	
	
	public int getId() {
		return Id;
	}
	public void setId(int Id) {
		this.Id = Id;
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
	}}
//	public Members(int id, String name) {
//	this.id = id;
//	this.name = name;
//}

	