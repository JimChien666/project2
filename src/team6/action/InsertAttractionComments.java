package team6.action;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import global.util.HibernateUtil;
import team6.nn.dao.AttractionCommentsDAO;
import team6.nn.dao.AttractionTypesDAO;
import team6.nn.dao.AttractionsDAO;
import team6.nn.dao.CitysDAO;
import team6.nn.dao.TagsDAO;
import team6.nn.entity.AttractionComments;
import team6.nn.entity.AttractionTypes;
import team6.nn.entity.Attractions;
import team6.nn.entity.Citys;
import team6.nn.entity.Tags;

/**
 * Servlet implementation class InsertAttractionComments
 */
@WebServlet("/InsertAttractionComments")
public class InsertAttractionComments extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	protected void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		

		AttractionsDAO attractionsDAO = new AttractionsDAO(session);
		Attractions attraction = attractionsDAO.select(201);
		AttractionComments attractionComment1 = new AttractionComments();
		AttractionComments attractionComment2 = new AttractionComments();
		Set<AttractionComments> attractionComments = new HashSet<AttractionComments>();
		attractionComments.add(attractionComment1);
		attractionComments.add(attractionComment2);
		attraction.setAttractionComments(attractionComments);
		attractionComment1.setAttraction(attraction);
		attractionComment2.setAttraction(attraction);
		session.save(attractionComment2);
		session.save(attractionComment1);
	}

}
