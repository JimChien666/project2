package com.iii.eeit124.entity;


import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
@Entity
@Table(name = "FORUMS")
public class Forums {
	private int id;
	private int articleid;
	private String content;
	private Date createdat;
	private int voteid;
	private int memberid;
	private Article article;

	@Id @Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Forums [id=" + id + ", articleid=" + articleid + ", content=" + content + ", createdat=" + createdat
				+ ", voteid=" + voteid + ", memberid=" + memberid + ", article=" + article + "]";
	}

	public void setId(int id) {
		this.id = id;
	}

//	@Column(name = "ARTICLE_ID")
//	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "article"))
//	@GeneratedValue(generator = "generator")
	@Transient
	public int getArticleid() {
		return articleid;
	}

	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}

	@Column(name = "CONTENT")
	@Lob
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "CREATED_AT")
//	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreatedat() {
		return createdat;
	}

	public void setCreatedat(Date createdat) {
		this.createdat = createdat;
	}

	@Column(name = "VOTE_ID")
	public int getVoteid() {
		return voteid;
	}

	public void setVoteid(int voteid) {
		this.voteid = voteid;
	}

	@Column(name = "MEMBER_ID")
	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
	}
	@ManyToOne(fetch = FetchType.LAZY)
//	@PrimaryKeyJoinColumn
	@JoinColumn(name = "id")
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Forums() {
	}

	public Forums(int id, int articleid, String content, Date createdat, int voteid, int memberid, Article article) {
		super();
		this.id = id;
		this.articleid = articleid;
		this.content = content;
		this.createdat = createdat;
		this.voteid = voteid;
		this.memberid = memberid;
		this.article = article;
	}

}
