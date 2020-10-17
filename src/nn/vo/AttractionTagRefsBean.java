package nn.vo;

import java.io.Serializable;

public class AttractionTagRefsBean implements Serializable {
	private int id;
	private int tagId;
	private int attractionId;
	
	public AttractionTagRefsBean() {};
	public AttractionTagRefsBean(int tagId, int attractionId) {
		super();
		this.tagId = tagId;
		this.attractionId = attractionId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTagId() {
		return tagId;
	}
	public void setTagId(int tagId) {
		this.tagId = tagId;
	}
	public int getAttractionId() {
		return attractionId;
	}
	public void setAttractionId(int attractionId) {
		this.attractionId = attractionId;
	}
	
}
