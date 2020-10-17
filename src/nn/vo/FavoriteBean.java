package nn.vo;

import java.io.Serializable;

public class FavoriteBean implements Serializable {
	private int id;
	private int memberId;
	private int attractionId;
	private int isFavoriteAvailable;
	
	public FavoriteBean(int attractionId,int memberId, int isFavoriteAvailable) {
		super();
		this.attractionId = attractionId;
		this.memberId = memberId;
		this.isFavoriteAvailable = isFavoriteAvailable;
	}
	public FavoriteBean() {};
	
	
	
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getIsFavoriteAvailable() {
		return isFavoriteAvailable;
	}
	public void setIsFavoriteAvailable(int isFavoriteAvailable) {
		this.isFavoriteAvailable = isFavoriteAvailable;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAttractionId() {
		return attractionId;
	}
	public void setAttractionId(int attractionId) {
		this.attractionId = attractionId;
	}
	public int isFavoriteAvailable() {
		return isFavoriteAvailable;
	}
	public void setFavoriteAvailable(int isFavoriteAvailable) {
		this.isFavoriteAvailable = isFavoriteAvailable;
	}
	
	
}
