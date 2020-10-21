package team6.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "profiles")
public class Profiles {
	private int id;
	private String username;
	private String userpwd;
	@Id@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "Username")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(name = "Userpwd")
	public String getUserpwd() {
		return userpwd;
	}
	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	
}
