package DAO;

public class ActivityRecords {
	
	private int id;
	private int activityId;
	private int animalId;
	
	
	public int getId() {
		return id;
	}
	
	public int getActivityId() {
		return activityId;
	}
	
	public int getanilmalId() {
		return animalId;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	public void setAnimalId(int animalId) {
		this.animalId = animalId;
	}

	@Override
	public String toString() {
		return "ActivityRecords [id=" + id + ", activityId=" + activityId + ", animalId=" + animalId + "]";
	}
	
	
	
	

}
