package jim;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductsBean;

/**
 * Servlet implementation class ProductsPage
 */
@WebServlet("/ProductsPage")
public class ProductsPage extends HttpServlet {	
	int pageNo = 1;

	  public void doGet(HttpServletRequest request,HttpServletResponse response)
	     throws ServletException, IOException {
		    response.setContentType("text/html");
		    response.setCharacterEncoding("UTF-8");
		    PrintWriter out = response.getWriter();
		    
			JdbcDao jdbcDao = new JdbcDao();
			List<ProductsBean> list = jdbcDao.listProducts();	
			List<ProductsBean> searchlist = jdbcDao.listProducts();	
			for(ProductsBean listp :list) {	
				if(listp.getName().contains("程小乖")) {
					searchlist.add(listp);
				}
			    out.println(listp.getId());				    
			    out.println(listp.getName());			    
			    out.println(listp.getDescript());	    
			}
			request.setAttribute("ProductList", list);
			RequestDispatcher rd= request.getRequestDispatcher("/jim/ProductList.jsp");
			rd.forward(request, response);
			return;
	  } 

	  public void doPost(HttpServletRequest request,
	                     HttpServletResponse response)
	      throws ServletException, IOException {
	    doGet(request, response);
	  }
	}