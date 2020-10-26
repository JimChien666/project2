package jim;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import jim.entity.Products;
import jim.entity.ProductsDAO;
import tw.reicheng.util.HibernateUtil;

@WebServlet("/QueryProduct")
public class QueryProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";
    public QueryProduct() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
	    // To prevent caching 
	    response.setHeader("Cache-Control","no-cache"); // HTTP 1.1
	    response.setHeader("Pragma","no-cache"); // HTTP 1.0
	    response.setDateHeader ("Expires", -1); // Prevents caching at the proxy server

	   if (request.getParameter("submit")!=null)
	     gotoSubmitProcess(request, response);
	}
	public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
		String search = "";
        PrintWriter out = response.getWriter();
        
//        if (request.getParameter("name") != null) {
//        	search = request.getParameter("name").trim();
//        }
//
//		String searchName = search.trim(); //把空白去掉!!
//	    img = request.getParameter("img").trim(); //把空白去掉!!

		
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		ProductsDAO productsDao = new ProductsDAO(session);
		Products products = productsDao.select(63);
		
		response.setContentType("text/html;charset=UTF-8");
		
		out.write("Id:" + products.getId() + "<br>");
		out.write("Name:" + products.getName() + "<br>");
		
		out.close();
		
		
//		
//	    JdbcDao jdbcdao = new JdbcDao();
//
//    	List<Products> SearchList = new ArrayList<>();
//    	List<Products> list = jdbcdao.listProducts();
//    	for(Products product:list) {
//    		if(product.getName().contains(searchName)) {
//    			SearchList.add(product);
//    		}
//
////			    out.println(list.getDescript());
//    	}
//      System.out.println("Get some SQL commands done!");
//      int size = SearchList.size();
//      if(size>0) {
//    	  
//    	  request.setAttribute("ProductList", SearchList);
//          RequestDispatcher rd= request.getRequestDispatcher("/jim/ProductList.jsp");
//          rd.forward(request, response);
//          return;
//      }
//      else {
//    	  System.out.println("dfjioasj");
//    	  request.setAttribute("noResult", "noResult");
//    	  
//    	  response.sendRedirect("/Project2/jim/QueryProduct.jsp");
//    	  RequestDispatcher rd= request.getRequestDispatcher("/jim/QueryProduct.jsp");
//          rd.forward(request, response);
//      }
//          request.getSession(true).invalidate(); //關掉session
//          request.getRequestDispatcher("jim/Thanks.jsp").include(request,response);
   
	  }    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
