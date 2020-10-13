package article.model;

import java.io.Serializable;

public class CommentsBean implements Serializable {

	private int id;
	private String comment;
	private int forumId;
	private int memberId;
	/**
	 * 
	 */
	public CommentsBean() {
	}
	
	/**
	 * @param id
	 * @param comment
	 * @param forumId
	 * @param memberId
	 */
	public CommentsBean(int id, String comment, int forumId, int memberId) {
		this.id = id;
		this.comment = comment;
		this.forumId = forumId;
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
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the forumId
	 */
	public int getForumId() {
		return forumId;
	}

	/**
	 * @param forumId the forumId to set
	 */
	public void setForumId(int forumId) {
		this.forumId = forumId;
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
