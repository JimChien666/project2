package team6.johnny.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEMBER_OPTION")
public class MemberOption {
	private int id;
	private int optionid;
	private int memberid;

	@Id @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "OPTION_ID")
	public int getOptionid() {
		return optionid;
	}

	public void setOptionid(int optionid) {
		this.optionid = optionid;
	}

	@Column(name = "MEMBER_ID")
	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

}
