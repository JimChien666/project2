package wey.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import team6.util.HibernateUtil;
import wey.dao.AnimalsDao;
import wey.entity.Animals;

@WebServlet("/AnimalsController")
public class AnimalsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			SessionFactory factory = HibernateUtil.getSessionFactory();
			Session hSession = factory.getCurrentSession();
			
			AnimalsDao dao = new AnimalsDao(hSession);
			Animals animals = new Animals();
			int memberId = 0;
			String acceptionId = "";
			int breedId = 0;
			int gender = 0;
			String coatColor = "";
			int isAdoptionAvailable = 0;
			String note = "";
			animals.setMemberId(memberId);
			animals.setBreedId(breedId);
			
		} catch (Exception e) {
			System.out.println("AnimalsController error");
			e.printStackTrace(); 
//			RequestDispatcher rd = request.getRequestDispatcher("/wey/animal/CreateAnimal.jsp");
//			rd.forward(request, response);
		}
	}

}
