package com.iii.eeit124.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "article")
public class Article {

	private int id;
	private String title;
	private int activitysid;
	private int showarticle;
	private int memberid;
	private int articletypesid;
	private Set<Forums> forums = new HashSet<Forums>();

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "ACTIVITYS_ID")
	public int getActivitysid() {
		return activitysid;
	}

	public void setActivitysid(int activitysid) {
		this.activitysid = activitysid;
	}

	@Column(name = "SHOW_ARTICLE")
	public int getShowarticle() {
		return showarticle;
	}

	public void setShowarticle(int showarticle) {
		this.showarticle = showarticle;
	}

	@Column(name = "MEMBER_ID")
	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}

	@Column(name = "ARTICLETYPES_ID")
	public int getArticletypesid() {
		return articletypesid;
	}

	public void setArticletypesid(int articletypesid) {
		this.articletypesid = articletypesid;
	}

	@OneToMany(fetch = FetchType.LAZY, targetEntity = Forums.class, cascade = CascadeType.ALL, mappedBy = "article")
	public Set<Forums> getForums() {
		return forums;
	}

	public void setForums(Set<Forums> forums) {
		this.forums = forums;
	}

	public Article(String title, int activitysid, int showarticle, int memberid, int articletypesid) {
		this.title = title;
		this.activitysid = activitysid;
		this.showarticle = showarticle;
		this.memberid = memberid;
		this.articletypesid = articletypesid;
	}

	public Article() {
	}



	public Article(int id, String title, int activitysid, int showarticle, int memberid, int articletypesid,
			Set<Forums> forums) {
		this.id = id;
		this.title = title;
		this.activitysid = activitysid;
		this.showarticle = showarticle;
		this.memberid = memberid;
		this.articletypesid = articletypesid;
		this.forums = forums;
	}



}
