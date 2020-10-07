package jim;

import javax.servlet.annotation.WebServlet;
@WebServlet("/ProductsPage")
public class ProductsPage extends CatalogPage{
	  public void init() {
		    String[] ids = { "lewis001", "alexander001", "rowling001" };
		    setItems(ids);
		    setTitle("All-Time Best Children's Fantasy Books");
	  }
}
