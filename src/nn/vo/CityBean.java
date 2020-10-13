package nn.vo;

import java.io.Serializable;

public class CityBean implements Serializable {
	private int id;
	private String name;
	
	public CityBean() {};
	public CityBean(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
