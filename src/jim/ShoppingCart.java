package jim;

import java.util.Vector;

public class ShoppingCart {
	  private Vector itemsOrdered;

	  /** Builds an empty shopping cart. */
	  
	  public ShoppingCart() {
	    itemsOrdered = new Vector();
	  }

	  /** Returns Vector of ItemOrder entries giving
	   *  Item and number ordered.
	   */
	  
	  public Vector getItemsOrdered() {
	    return(itemsOrdered);
	  }

	  /** Looks through cart to see if it already contains
	   *  an order entry corresponding to item ID. If it does,
	   *  increments the number ordered. If not, looks up
	   *  Item in catalog and adds an order entry for it.
	   */
	  
	  public synchronized void addItem(String itemID) {
	    ItemOrder order;
	    for(int i=0; i<itemsOrdered.size(); i++) {
	      order = (ItemOrder)itemsOrdered.elementAt(i);
	      
	      //�p�G�w�g�s�b���ʨ����A�h�W�[�ƶq1
	      if (order.getItemID().equals(itemID)) {  
	        order.incrementNumItems();
	        return;
	      }
	    }
	    
	    //�p�G���b���ʨ����A�h�[�J���ʨ���
	    ItemOrder newOrder = new ItemOrder(Catalog.getItem(itemID));
	    itemsOrdered.addElement(newOrder);
	  }

	  /** Looks through cart to find order entry corresponding
	   *  to item ID listed. If the designated number
	   *  is positive, sets it. If designated number is 0
	   *  (or, negative due to a user input error), deletes
	   *  item from cart.
	   */
	  
	  public synchronized void setNumOrdered(String itemID,
	                                         int numOrdered) {
	    ItemOrder order;
	    for(int i=0; i<itemsOrdered.size(); i++) {
	      order = (ItemOrder)itemsOrdered.elementAt(i);
	      if (order.getItemID().equals(itemID)) {
	        if (numOrdered <= 0) {
	          itemsOrdered.removeElementAt(i);
	        } else {
	          order.setNumItems(numOrdered);
	        }
	        return;
	      }
	    }
	    ItemOrder newOrder =
	      new ItemOrder(Catalog.getItem(itemID));
	    itemsOrdered.addElement(newOrder);
	  }
	}
	    
