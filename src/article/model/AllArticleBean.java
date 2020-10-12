package article.model;

import java.io.Serializable;
import java.util.Date;

public class AllArticleBean implements Serializable {
	private int id;
	private int articleId;
	private String title;
	private String content;
	private Date createdAt;
	private int memberId;
	/**
	 * 
	 */
	public AllArticleBean() {
	}
	/**
	 * @param articleId
	 * @param title
	 * @param content
	 * @param createdAt
	 * @param memberId
	 */
	public AllArticleBean(int id,int articleId, String title, String content, Date createdAt, int memberId) {
		this.id = id;
		this.articleId = articleId;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
		this.memberId = memberId;
	}
	/**
	 * @return the articleId
	 */
	public int getArticleId() {
		return articleId;
	}
	/**
	 * @param articleId the articleId to set
	 */
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the createdAt
	 */
	public Date getCreatedAt() {
		return createdAt;
	}
	/**
	 * @param createdAt the createdAt to set
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	/**
	 * @return the memberId
	 */
	public int getMemberId() {
		return memberId;
	}
	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

}
