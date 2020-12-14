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
@Table(name = "COMMENTS")
public class Comments {
	private int id;
	private String comment;
	private int forumid;
	private int memberid;
	private Members member;
	private Forums forums;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "CONTENT")
	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Transient
//	@Column(name = "FORUM_ID")
	public int getForumid() {
//		return forumid;
		return this.forums.getId();
	}

	public void setForumid(int forumid) {
		this.forumid = forumid;
	}

//	@Column(name = "MEMBER_ID")
	@Transient
	public int getMemberid() {
//		return memberid;
		return this.member.getId();
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	@JsonIgnore
	@ManyToOne(targetEntity = Members.class)
	@JoinColumn(name = "MEMBER_ID")
	public Members getMember() {
		return member;
	}

	@Transient
	public Integer getForumOwnerFileId() {
		return this.member.getFileId();
	}
	
	@Transient
	public String getMemberName() {
		return this.member.getName();
	}
	
	public void setMember(Members member) {
		this.member = member;
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
