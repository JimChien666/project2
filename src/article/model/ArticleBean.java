package article.model;

import java.io.Serializable;

public class ArticleBean implements Serializable {
	private int id;
	private String title;
	private int activitysId;
	private int articleType;
	private int showArticle;
	private int memberId;
	/**
	 * 
	 */
	public ArticleBean() {
	}
	/**
	 * @param id
	 * @param title
	 * @param activitysId
	 * @param articleType
	 * @param showArticle
	 * @param memberId
	 */
	public ArticleBean(int id, String title, int activitysId, int articleType, int showArticle, int memberId) {
		this.id = id;
		this.title = title;
		this.activitysId = activitysId;
		this.articleType = articleType;
		this.showArticle = showArticle;
		this.memberId = memberId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getActivitysId() {
		return activitysId;
	}
	public void setActivitysId(int activitysId) {
		this.activitysId = activitysId;
	}
	public int getArticleType() {
		return articleType;
	}
	public void setArticleType(int articleType) {
		this.articleType = articleType;
	}
	public int getShowArticle() {
		return showArticle;
	}
	public void setShowArticle(int showArticle) {
		this.showArticle = showArticle;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}


}
