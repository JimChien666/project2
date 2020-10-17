package nn.vo;

import java.io.Serializable;

public class TagBean implements Serializable  {
	private int id;
	private String name;
	
	
	public TagBean() {};
	public TagBean(int id, String name) {
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
