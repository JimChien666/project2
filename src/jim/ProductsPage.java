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
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import global.util.HibernateUtil;
import jim.dao.ProductsDAO;
import jim.entity.Products;


@WebServlet("/ProductsPage")
public class ProductsPage extends HttpServlet {	
	int pageNo = 1;

	  public void doGet(HttpServletRequest request,HttpServletResponse response)
	     throws ServletException, IOException {
		    response.setContentType("text/html; charset=UTF-8");
		    response.setCharacterEncoding("UTF-8");
		    PrintWriter out = response.getWriter();
		    
		    //Hibernate
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session Session = factory.getCurrentSession();
			
			ProductsDAO productsDao = new ProductsDAO(Session);
			List<Products> list = productsDao.selectAll();
			request.setAttribute("ProductList", list);
			//
			
			//SQL
//			JdbcDao jdbcDao = new JdbcDao();
//			List<Products> list = jdbcDao.listProducts();
//			request.setAttribute("ProductList", list);
			//
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