package nn.vo;

import java.util.LinkedHashMap;
import java.util.Map;




public class LikeCart {
	private Map<Integer, AttractionIntroduction> cart = new LinkedHashMap< >();
	
	public LikeCart() {};
	
	
	public Map<Integer, AttractionIntroduction>  getContent() { // ${ShoppingCart.content}
		return cart;
	}
	
	public void addToCart(int bookID, AttractionIntroduction  attractionIntroduction) {
		if ( cart.get(bookID) == null ) {
		    cart.put(bookID, attractionIntroduction);
		}
		
	}
}
