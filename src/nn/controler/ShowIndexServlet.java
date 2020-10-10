package nn.controler;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nn.dao.ShowIndexDao;
import nn.vo.AttractionIntroduction;
import nn.vo.AttractionTypeBean;

/**
 * Servlet implementation class ShowIndexServlet
 */
@WebServlet("/nn/controler/ShowIndexServlet")
public class ShowIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
	    ShowIndexDao dao = new ShowIndexDao();
	    List<AttractionTypeBean> attractionTypeList = dao.getAttractionTypeList();
	    HttpSession session = request.getSession();
	    request.setAttribute("attractionTypeList", attractionTypeList);
	    RequestDispatcher rd = request.getRequestDispatcher("/nn/index.jsp");
		rd.forward(request, response);
		
		return;
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
