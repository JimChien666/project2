package max.entity;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import team6.nn.model.Attractions;
import wey.entity.Breeds;

@Entity
@Table(name = "favorites")
public class Favorites {
	private int favoriteId;
	private int memberId;
	private int forumId;
	private int animalId;
	private int activityId;
	private int productId;
	private int attractionId;
	private int tripId;
	private int isFavoriteAvailable;
//	//Members
//	private Members members;
//	//
	
	
	
	@Id @Column(name = "FAVORITE_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(int favoriteId) {
		this.favoriteId = favoriteId;
	}
	
	@Column(name = "MEMBER_ID")
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	@Column(name = "FORUM_ID")
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	
	@Column(name = "ANIMAL_ID")
	public int getAnimalId() {
		return animalId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}
	
	@Column(name = "ACTIVITY_ID")
	public int getActivityId() {
		return activityId;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	@Column(name = "PRODUCT_ID")
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	@Column(name = "IS_FAVORITE_AVAILABLE")
	public int getIsFavoriteAvailable() {
		return isFavoriteAvailable;
	}

	public void setIsFavoriteAvailable(int isFavoriteAvailable) {
		this.isFavoriteAvailable = isFavoriteAvailable;
	}
	
	@Column(name = "ATTRACTON_ID")
	public int getAttractionId() {
		return attractionId;
	}
	public void setAttractionId(int attractionId) {
		this.attractionId = attractionId;
	}
	
	@Column(name = "TRIP_ID")
	public int getTripId() {
		return tripId;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

}