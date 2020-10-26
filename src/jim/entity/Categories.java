package jim.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHOPPING_CART")
public class Categories implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String color;
	private String living;
	private String bowl;
	private String toilet;
	private String toy;
	private String shampoo;
	private String outdoor;
	private String eat;
	private String cat;
	private String dog;
	
	
	
	public Categories() {		
	}
	@Id @Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Column(name = "COLOR")
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	@Column(name = "LIVING")
	public String getLiving() {
		return living;
	}

	public void setLiving(String living) {
		this.living = living;
	}
	@Column(name = "BOWL")
	public String getBowl() {
		return bowl;
	}

	public void setBowl(String bowl) {
		this.bowl = bowl;
	}
	@Column(name = "TOILET")
	public String getToilet() {
		return toilet;
	}

	public void setToilet(String toilet) {
		this.toilet = toilet;
	}
	@Column(name = "TOY")
	public String getToy() {
		return toy;
	}

	public void setToy(String toy) {
		this.toy = toy;
	}
	@Column(name = "SHAMPOO")
	public String getShampoo() {
		return shampoo;
	}

	public void setShampoo(String shampoo) {
		this.shampoo = shampoo;
	}
	@Column(name = "OUTDOOR")
	public String getOutdoor() {
		return outdoor;
	}

	public void setOutdoor(String outdoor) {
		this.outdoor = outdoor;
	}
	@Column(name = "EAT")
	public String getEat() {
		return eat;
	}
	public void setEat(String eat) {
		this.eat = eat;
	}
	@Column(name = "CAT")
	public String getCat() {
		return cat;
	}
	public void setCat(String cat) {
		this.cat = cat;
	}
	@Column(name = "DOG")
	public String getDog() {
		return dog;
	}
	public void setDog(String dog) {
		this.dog = dog;
	}	
	
}
