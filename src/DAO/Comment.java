package DAO;

public class Comment {
	private int id;
	private int memberId;
	private int forumId;
	private String commentS;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public String getCommentS() {
		return commentS;
	}
	public void setCommentS(String commentS) {
		this.commentS = commentS;
	}
}
