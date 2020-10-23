package jim.entity;
import java.io.Serializable;
public class Oders implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String orderItem;
	private int productsId;
	private int shoppingCartId;
	
	public Oders() {		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(String orderItem) {
		this.orderItem = orderItem;
	}

	public int getProductsId() {
		return productsId;
	}

	public void setProductsId(int productsId) {
		this.productsId = productsId;
	}

	public int getShoppingCartId() {
		return shoppingCartId;
	}

	public void setShoppingCartId(int shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}

	
}
