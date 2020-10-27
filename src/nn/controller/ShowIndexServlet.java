package nn.controller;

import java.io.IOException;
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

import team6.nn.dao.GetIndexDataDAO;
import team6.nn.entity.AttractionTypes;

/**
 * Servlet implementation class ShowIndexServlet
 */
@WebServlet("/ShowIndexServlet")
public class ShowIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
	    GetIndexDataDAO getIndexDataDao = new GetIndexDataDAO(session);
	    List<AttractionTypes> attractionTypeList = getIndexDataDao.selectAllAttractionTypes();
	    
	    request.setAttribute("attractionTypeList", attractionTypeList);
	    RequestDispatcher rd = request.getRequestDispatcher("/nn/index2.jsp");
		rd.forward(request, response);
		
		return;
	    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
