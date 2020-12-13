package com.iii.eeit124.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MEMBER_OPTION")
public class MemberOption {
	private int id;
	private int optionid;
	private Options options;
	private int memberid;
	private Members members;

	private Integer forumid;
	private Forums forums;
	
	@Id @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Transient
//	@Column(name = "OPTION_ID")
	public int getOptionid() {
		return this.options.getId();
	}

	public void setOptionid(int optionid) {
		this.optionid = optionid;
	}
	
	@JsonIgnore
	@ManyToOne(targetEntity = Options.class)
	@JoinColumn(name = "OPTION_ID")
	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	@Transient
//	@Column(name = "MEMBER_ID")
	public int getMemberid() {
		return this.members.getId();
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	
	@JsonIgnore
	@ManyToOne(targetEntity = Members.class)
	@JoinColumn(name = "MEMBER_ID")
	public Members getMembers() {
		return members;
	}

	public void setMembers(Members members) {
		this.members = members;
	}

	@Transient
	public Integer getForumid() {
		return forumid;
	}

	public void setForumid(Integer forumid) {
		this.forumid = forumid;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORUM_ID")
	public Forums getForums() {
		return forums;
	}

	public void setForums(Forums forums) {
		this.forums = forums;
	}

	
}
