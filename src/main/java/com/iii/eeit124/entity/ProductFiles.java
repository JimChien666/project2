package com.iii.eeit124.entity;

import java.io.Serializable;
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

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="PRODUCT_FILES")
public class ProductFiles implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5108642249962869083L;
	private Integer id;
	private String fileType;
	private String fileUrl;
	private Integer prodoctId;
	private Products product;
	private MultipartFile multipartFile;
	private Blob fileBlob;
	
	
	public ProductFiles() {};
	
	public ProductFiles(String fileType, Blob fileBlob) {
		super();
		this.fileType = fileType;
		this.fileBlob = fileBlob;
	}
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="FILE_TYPE")
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Column(name="FILE_URL")
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	@Column(name="FILE_BLOB")
	public Blob getFileBlob() {
		return fileBlob;
	}
	public void setFileBlob(Blob fileBlob) {
		this.fileBlob = fileBlob;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PRODUCT_ID")
	public Products getProduct() {
		return product;
	}

	public void setProduct(Products product) {
		this.product = product;
	}
	@Transient
	public Integer getProdoctId() {
		return prodoctId;
	}

	public void setProdoctId(Integer prodoctId) {
		this.prodoctId = prodoctId;
	}
	
	@JsonIgnore
	@Transient
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

}
