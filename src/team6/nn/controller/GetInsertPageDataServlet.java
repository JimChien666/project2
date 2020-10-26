package team6.nn.controller;

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
import org.json.simple.JSONArray;

import team6.nn.dao.AttractionTypesDAO;
import team6.nn.dao.InsertAttractionDAO;
import team6.nn.entity.AttractionTypes;
import team6.nn.entity.Citys;
import team6.nn.entity.Tags;
import team6.util.HibernateUtil;

/**
 * Servlet implementation class GetInsertPageDataServlet
 */
@WebServlet("/GetInsertPageDataServlet")
public class GetInsertPageDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		InsertAttractionDAO insertAttractionDao = new InsertAttractionDAO(session);
		List<Citys> citys = insertAttractionDao.selectAllCitys();
		List<AttractionTypes> attractionTypes = insertAttractionDao.selectAllAttractionTypes();
		List<Tags> tags = insertAttractionDao.selectAllTags();
		String citysJson = JSONArray.toJSONString(citys);
		String tagsJson = JSONArray.toJSONString(tags);
		System.out.println(tagsJson);
		String attractionTypesJson = JSONArray.toJSONString(attractionTypes);
		String result = "{\"citys\":" + citysJson + ", \"attractionTypes\": " + attractionTypesJson +", \"tags\": " + tagsJson + "}";
	    out.write(result);
	    return;
	}
	
}
