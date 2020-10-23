package jim.entity;
import java.io.Serializable;
public class ShoppingCart implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String color;
	private String living;
	private String bowl;
	private String toilet;
	private String toy;
	private String shampoo;
	private String outdoor;	
	
	public ShoppingCart() {		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLiving() {
		return living;
	}

	public void setLiving(String living) {
		this.living = living;
	}

	public String getBowl() {
		return bowl;
	}

	public void setBowl(String bowl) {
		this.bowl = bowl;
	}

	public String getToilet() {
		return toilet;
	}

	public void setToilet(String toilet) {
		this.toilet = toilet;
	}

	public String getToy() {
		return toy;
	}

	public void setToy(String toy) {
		this.toy = toy;
	}

	public String getShampoo() {
		return shampoo;
	}

	public void setShampoo(String shampoo) {
		this.shampoo = shampoo;
	}

	public String getOutdoor() {
		return outdoor;
	}

	public void setOutdoor(String outdoor) {
		this.outdoor = outdoor;
	}
	
	
}
