package team6.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import team6.nn.dao.AttractionTypesDAO;
import team6.nn.dao.CitysDAO;
import team6.nn.dao.TagsDAO;
import team6.nn.entity.AttractionTypes;
import team6.nn.entity.Attractions;
import team6.nn.entity.Citys;
import team6.nn.entity.Files;
import team6.nn.entity.Tags;
import team6.util.HibernateUtil;

/**
 * Servlet implementation class DemoHibernateServletAction1
 */
@WebServlet("/DemoCityBeanAction1")
public class InsertAttractionAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
//		CitysDAO cDao = new CitysDAO(session);
//		Citys cBean = cDao.select(1);
//		
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		
//		out.write("HouseId:" + cBean.getId() + "<br>");
//		out.write("HouseName:" + cBean.getName() + "<br>");
//		
//		out.close();
		Attractions attraction = new Attractions();
		
		//選擇city
		CitysDAO cityDao = new CitysDAO(session);
		Citys city = cityDao.select(1);
		//選擇Attractiontypes
		AttractionTypesDAO attractionTypeDao = new AttractionTypesDAO(session);
		AttractionTypes attractionType = attractionTypeDao.select(1);
		attraction.setId(100);
		attraction.setCity(city);
		attraction.setAttractionType(attractionType);
		Set<Attractions> attractions = new HashSet<Attractions>();
		attractions.add(attraction);
		city.setAttractions(attractions);
		attractionType.setAttractions(attractions);
		attraction.setCity(city);
		attraction.setAttractionType(attractionType);
		
		TagsDAO tagsDAO = new TagsDAO(session);
		Tags tag1 = tagsDAO.select(1);
		Tags tag2 = tagsDAO.select(2);
		Set<Tags> tags = new HashSet<Tags>();
		tags.add(tag1);
		tags.add(tag2);
		attraction.setTags(tags);
		Files file = new Files();
		Set<Files> files = new HashSet<Files>();
		files.add(file);
		attraction.setFiles(files);
		
		
		session.save(attraction);
	}

}
