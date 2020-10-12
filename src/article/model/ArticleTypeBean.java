package article.model;

import java.io.Serializable;

public class ArticleTypeBean implements Serializable {
	private int id;
	private String articleType;
	
	public ArticleTypeBean() {};
	
	public ArticleTypeBean(String articleType) {
		this.articleType = articleType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getArticleType() {
		return articleType;
	}
	public void setArticleType(String articleType) {
		this.articleType = articleType;
	}

	
}
