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


@Entity
@Table(name="MEMBER_FILES")
public class MemberFiles implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1651897310959690387L;
	private Integer id;
	private String fileType;
	private String fileUrl;
	private Integer memberId;
	private Members member;

	private Blob fileBlob;
	
	
	public MemberFiles() {};
	
	public MemberFiles(String fileType, Blob fileBlob) {
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
	@Transient
//	@Column(name="MEMBER_ID")
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	@Column(name="FILE_BLOB")
	public Blob getFileBlob() {
		return fileBlob;
	}
	public void setFileBlob(Blob fileBlob) {
		this.fileBlob = fileBlob;
	}
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "MEMBER_ID")
	public Members getMember() {
		return member;
	}

	public void setMember(Members member) {
		this.member = member;
	}
	

}
