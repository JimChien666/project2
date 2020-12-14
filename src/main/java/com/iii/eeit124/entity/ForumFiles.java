package com.iii.eeit124.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "OptionFiles")
public class ForumFiles {
	private Integer id;
	private String filename;
	private Blob fileblob;

	private Integer forumsid;//對應選項的id many to one
	private Forums forums;
	
	@Id @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "FILENAME")
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "FILEBLOB")
	public Blob getFileblob() {
		return fileblob;
	}

	public void setFileblob(Blob fileblob) {
		this.fileblob = fileblob;
	}

	@Transient
	public Integer getForumsid() {
		return forumsid;
	}

	public void setForumsid(Integer forumsid) {
		this.forumsid = forumsid;
	}

	@JsonIgnore
	@JoinColumn(name = "FORUMS_ID")
	@ManyToOne(targetEntity = Forums.class)
	public Forums getForums() {
		return forums;
	}

	public void setForums(Forums forums) {
		this.forums = forums;
	}
	


	
	


	

}
