package com.iii.eeit124.entity;

import java.sql.Blob;

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
@Table(name = "OPTIONS")
public class Options {
	private int id;
	private String content;//選項內容描述
	private Blob optionBlob;

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

	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Transient
	public Integer getForumid() {
		return this.forums.getId();
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
	@JsonIgnore
	@Column(name = "OPTIONBLOB")
	public Blob getOptionBlob() {
		return optionBlob;
	}

	public void setOptionBlob(Blob optionBlob) {
		this.optionBlob = optionBlob;
	}

}
