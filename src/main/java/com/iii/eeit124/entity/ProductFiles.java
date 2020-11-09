package com.iii.eeit124.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="PRODUCT_FILES")
public class ProductFiles implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5108642249962869083L;
	private Integer id;
	private String FileType;
	private String FileUrl;
	private Integer ProdoctId;
	private Products product;

	private Blob fileBlob;
	
	
	public ProductFiles() {};
	
	public ProductFiles(String fileType, Blob fileBlob) {
		super();
		FileType = fileType;
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
		return FileType;
	}
	public void setFileType(String fileType) {
		FileType = fileType;
	}
	@Column(name="FILE_URL")
	public String getFileUrl() {
		return FileUrl;
	}
	public void setFileUrl(String fileUrl) {
		FileUrl = fileUrl;
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
		return ProdoctId;
	}

	public void setProdoctId(Integer prodoctId) {
		ProdoctId = prodoctId;
	}
	

}
