package nn.vo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;




public class LikeCart {
	private List<AttractionIntroduction> cart = new ArrayList();
	
	public LikeCart() {};
	
	
	public List<AttractionIntroduction> getContent() { // ${ShoppingCart.content}
		return cart;
	}
	
	public void addToCart(AttractionIntroduction  attractionIntroduction) {
		    cart.add(attractionIntroduction);
		
	}
}
