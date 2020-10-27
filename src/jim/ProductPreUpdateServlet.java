package jim;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/ProductPreUpdate")
public class ProductPreUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(false);
		
//		if (session == null) {
//		     response.sendRedirect(request.getContextPath() + "/index.jsp");
//		     return;
//		}
		int productId = 0;
		String strProduct = request.getParameter("productId");
		
		System.out.println(strProduct);
		
		if (strProduct != null) {
			productId = Integer.parseInt(strProduct);
		}
		JdbcDao proJdbcDao = new JdbcDao();
		ValueObjectProduct product = proJdbcDao.getProduct(productId);
		System.out.println(product);
		
		session.setAttribute("product", product);
		
		RequestDispatcher rd = request.getRequestDispatcher("/jim/UpdateProduct.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}
}
