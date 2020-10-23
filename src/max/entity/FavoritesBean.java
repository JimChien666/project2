package max.entity;

import java.io.Serializable;

public class FavoritesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int favoriteId;
	private int memberId;
	private int forumId;
	private int animalId;
	private int activityId;
	private int productId;
	private boolean isFavoriteAvailable;
	private int attractionId;
	private int tripId;

	public int getFavoriteId() {
		return favoriteId;
	}
	
	public FavoritesBean(int favoriteId, int memberId, int forumId, int animalId, int activityId, int productId,
			boolean isFavoriteAvailable, int attractionId, int tripId) {
		super();
		this.favoriteId = favoriteId;
		this.memberId = memberId;
		this.forumId = forumId;
		this.animalId = animalId;
		this.activityId = activityId;
		this.productId = productId;
		this.isFavoriteAvailable = isFavoriteAvailable;
		this.attractionId = attractionId;
		this.tripId = tripId;
	}

	

	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
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

	public int getAnimalId() {
		return animalId;
	}

	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public boolean isFavoriteAvailable() {
		return isFavoriteAvailable;
	}

	public void setFavoriteAvailable(boolean isFavoriteAvailable) {
		this.isFavoriteAvailable = isFavoriteAvailable;
	}

	public int getAttractionId() {
		return attractionId;
	}

	public void setAttractionId(int attractionId) {
		this.attractionId = attractionId;
	}

	public int getTripId() {
		return tripId;
	}

	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	{

	}
}