package _01_register.model;

import java.sql.Blob;

public class MemberFileBean {
	private int id;
	private int memberId;
	private Blob blob;
	
	
	public MemberFileBean() {};
	public MemberFileBean(int memberId, Blob blob) {
		super();
		this.memberId = memberId;
		this.blob = blob;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public Blob getBlob() {
		return blob;
	}
	public void setBlob(Blob blob) {
		this.blob = blob;
	}
	
	
}
