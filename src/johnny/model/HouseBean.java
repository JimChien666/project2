package johnny.model;
/*
 * 
 */
public class HouseBean {
	private int houseid;
	private String housename;
	
	public HouseBean(int houseid, String housename) {
		this.houseid = houseid;
		this.housename = housename;
	}
	
	public HouseBean() {
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
