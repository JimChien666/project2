package tw.wey.old;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import global.util.HibernateUtil;
import wey.entity.Animals;

@WebServlet("/PreUpdateAnimals")
public class PreUpdateAnimals extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession(false);
		//取得欲修改的ID，轉為數字
		String animalIdString = request.getParameter("animalId");
		int animalId = 0;
		if(animalIdString!=null) {
			animalId = Integer.parseInt(animalIdString);
		}
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session hSession = factory.getCurrentSession();
		
		Animals animals = hSession.get(Animals.class, animalId);
		session.setAttribute("animals", animals);
		
		RequestDispatcher rd = request.getRequestDispatcher("/wey/animal/UpdateAnimal.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
