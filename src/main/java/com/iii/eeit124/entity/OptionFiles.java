package com.iii.eeit124.entity;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "OptionFiles")
public class OptionFiles {
	private Integer id;
	private String filename;
	private Blob fileblob;
	
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

	public OptionFiles(Integer id, String filename, Blob fileblob) {
		super();
		this.id = id;
		this.filename = filename;
		this.fileblob = fileblob;
	}

	public OptionFiles() {
	}

}
