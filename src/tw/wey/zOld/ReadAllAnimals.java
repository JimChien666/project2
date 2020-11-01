package tw.wey.zOld;

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
import tw.wey.dao.AnimalsDao;
import tw.wey.entity.Animals;

@WebServlet("/ReadAllAnimals")
public class ReadAllAnimals extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request, response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session hSession = factory.getCurrentSession();
		
		AnimalsDao animalsDao = new AnimalsDao(hSession);
		List<Animals> list = animalsDao.readAll();
		request.setAttribute("AnimalsList", list);
		
		RequestDispatcher rd = request.getRequestDispatcher("/wey/animal/ReadAnimal.jsp");
		rd.forward(request, response);
		return;
	}
}
