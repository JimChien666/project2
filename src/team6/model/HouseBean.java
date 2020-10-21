package team6.model;

public class HouseBean {
	private int houseid;
	private String housename;
	
	public HouseBean() {		
	}
	public HouseBean(int houseid,String housename) {
		this.houseid = houseid; //全域=區域
		this.housename = housename;
	}
	
	public int getHouseid() {
		return houseid;
	}
	public void setHouseid(int houseid) {
		this.houseid = houseid;
	}
	public String getHousename() {
		return housename;
	}
	public void setHousename(String housename) {
		this.housename = housename;
	}
	
	
}
