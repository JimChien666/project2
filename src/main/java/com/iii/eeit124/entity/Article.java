package com.iii.eeit124.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	private Members member;

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

//	@Column(name = "MEMBER_ID")
	@Transient
	public int getMemberid() {
		return this.member.getId();
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

	// TODO edit lazzy to eager 11/29 2020
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "article", cascade = CascadeType.ALL)
//	@OneToMany(fetch = FetchType.LAZY, targetEntity = Forums.class, cascade = CascadeType.ALL, mappedBy = "article")
	public Set<Forums> getForums() {
		return forums;
	}


	public void setForums(Set<Forums> forums) {
		this.forums = forums;
	}
	public void addForum(Forums forum) {
		this.forums.add(forum);
		forum.setArticle(this);
	}
	

	public Article(String title, int activitysid, int showarticle, int memberid, int articletypesid) {
		this.title = title;
		this.activitysid = activitysid;
		this.showarticle = showarticle;
		this.memberid = memberid;
		this.articletypesid = articletypesid;
	}
	
//	@Transient
//	public Forums getFirstForum() {	
//		System.out.println(this.getForums());
//		Iterator<Forums> iterator = this.getForums().iterator();
//		if(iterator.hasNext()) {
//			Forums forum = iterator.next();				
//			return forum;
//		}
//		return null;
//	}
	@Transient
	public Forums getFirstForum() {	
		System.out.println(this.getForums());
		Iterator<Forums> iterator = this.getForums().iterator();
		if(iterator.hasNext()) {
			Forums forum = iterator.next();				
			return forum;
		}
		return null;
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
	@JsonIgnore
	@ManyToOne(targetEntity = Members.class)
	@JoinColumn(name = "MEMBER_ID")
	public Members getMember() {
		return member;
	}

	public void setMember(Members member) {
		this.member = member;
	}

//	@Override
//	public String toString() {
//		return "Article [id=" + id + ", title=" + title + ", activitysid=" + activitysid + ", showarticle="
//				+ showarticle + ", memberid=" + memberid + ", articletypesid=" + articletypesid + ", forums=" + forums
//				+ "]";
//	}



}
