package jim;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jim.entity.Products;
import jim.entity.Products2;

/**
 * Servlet implementation class InsertProduct
 */
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	 private static final String CHARSET_CODE = "UTF-8";
    public DeleteProduct() {
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
        PrintWriter out = response.getWriter();
		int id=0;
        String name = null; 
		int price = 0;
//		String img =null;
		Blob img =null;
		String descript = null; 
		int quantity = 0;
		int specialPrice = 0;
		String rewardpoints = null; 
		boolean isThumb = false;
		int memberId = 0; 
		int animalTypeId = 0; 
		int categoryId = 0;
	    
		id = Integer.parseInt(request.getParameter("id").trim()); //把空白去掉!!
//	    img = request.getParameter("img").trim(); //把空白去掉!!

	    Products product =  new Products(id,name,price,img,descript,quantity,specialPrice,
	    		rewardpoints,isThumb,memberId,animalTypeId,categoryId);
	    JdbcDao jdbcdao = new JdbcDao();

	    if (jdbcdao.deleteProduct(product)!=0)  
        {
          System.out.println("Get some SQL commands done!");
          System.out.println(jdbcdao.deleteProduct(product));
//          request.getSession(true).invalidate(); //關掉session
          request.getRequestDispatcher("jim/Thanks.jsp").include(request,response);
          
        }else {
        	 out.println("對不起，刪除失敗!");
        }	    
	  }    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
