package com.iii.eeit124.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "followedArticle")
public class FollowedArticle {
	private Integer id;
	private Integer memberid;
	private Integer articleid;
	private Integer status;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "MEMEBERID")
	public Integer getMemberid() {
		return memberid;
	}

	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}

	@Column(name = "ARTICLEID")
	public Integer getArticleid() {
		return articleid;
	}

	public void setArticleid(Integer articleid) {
		this.articleid = articleid;
	}
	
	@Column(name = "STATUS")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public FollowedArticle() {
	}

	public FollowedArticle(Integer id, Integer memberid, Integer articleid, Integer status) {
		super();
		this.id = id;
		this.memberid = memberid;
		this.articleid = articleid;
		this.status = status;
	}
		
}
