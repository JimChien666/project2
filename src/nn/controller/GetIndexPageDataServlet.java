package nn.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import global.util.HibernateUtil;
import nn.dao.GetIndexDataDAO;
import nn.entity.AttractionTypes;
import nn.entity.Attractions;


/**
 * Servlet implementation class ShowIndexServlet
 */
@WebServlet("/GetIndexPageDataServlet")
public class GetIndexPageDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}
	
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory= HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		GetIndexDataDAO getIndexDataDAO = new GetIndexDataDAO(session);
		String allAttractionDataJson = "[";
		List<AttractionTypes> allAttractionTypes = getIndexDataDAO.selectAllAttractionTypes();
		for(AttractionTypes attractionType:allAttractionTypes) {
			allAttractionDataJson = allAttractionDataJson + "{\"id\":\"" + attractionType.getId() + "\",\"name\":\"" + attractionType.getName() + "\", \"attractions\":[";
//			out.println(attractionType.getName());
			for(Attractions attraction:attractionType.getAttractions()) {
				allAttractionDataJson = allAttractionDataJson + attraction + ",";
			}
			if(attractionType.getAttractions().size()>0) {				
				allAttractionDataJson = allAttractionDataJson.substring(0,allAttractionDataJson.length() - 1);
			}
			allAttractionDataJson = allAttractionDataJson + "]},";
		}
		allAttractionDataJson = allAttractionDataJson.substring(0,allAttractionDataJson.length() - 1);
		allAttractionDataJson = allAttractionDataJson + "]";
		out.println(allAttractionDataJson);
	}
	
}
