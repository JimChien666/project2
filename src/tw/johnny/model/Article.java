package tw.johnny.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	@Id	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "title")
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
	
}
