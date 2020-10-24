package jim.entity;
import java.io.Serializable;
public class Categories implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private int membersId;
	private int productId;
	private int quantity;
	
	public Categories() {		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMembersId() {
		return membersId;
	}

	public void setMembersId(int membersId) {
		this.membersId = membersId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	
	
}
