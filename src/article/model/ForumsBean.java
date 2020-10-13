package article.model;

import java.io.Serializable;
import java.util.Date;

public class ForumsBean implements Serializable {

	private int id;
	private int articleId;
	private String content;
	private Date createdAt;
	private int voteId;
	private int memberId;
	/**
	 * 
	 */
	public ForumsBean() {
	}
	
	/**
	 * @param id
	 * @param articleId
	 * @param content
	 * @param createdAt
	 * @param voteId
	 * @param memberId
	 */
	public ForumsBean(int id, int articleId, String content, Date createdAt, int voteId, int memberId) {
		this.id = id;
		this.articleId = articleId;
		this.content = content;
		this.createdAt = createdAt;
		this.voteId = voteId;
		this.memberId = memberId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the voteId
	 */
	public int getVoteId() {
		return voteId;
	}

	/**
	 * @param voteId the voteId to set
	 */
	public void setVoteId(int voteId) {
		this.voteId = voteId;
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
	
}
