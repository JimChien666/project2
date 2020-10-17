package DAO;

public class AdoptedLevels {
	private int id;
	private int max;
	private int min;
	private int limitedAdopted;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMax() {
		return max;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}
	public int getLimitedAdopted() {
		return limitedAdopted;
	}
	public void setLimitedAdopted(int limitedAdopted) {
		this.limitedAdopted = limitedAdopted;
	}
	@Override
	public String toString() {
		return "AdoptedLevels [id=" + id + ", max=" + max + ", min=" + min + ", limitedAdopted=" + limitedAdopted + "]";
	}
	
}
