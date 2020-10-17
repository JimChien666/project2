package wey.animal;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletPreUpdateAnimal")
public class ServletPreUpdateAnimal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletPreUpdateAnimal() {
        super();
    }

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
		
		DaoAnimal daoAnimal = new DaoAnimal();
		ValueObjectAnimal valueObjectAnimal = daoAnimal.getAnimal(animalId);
		session.setAttribute("valueObjectAnimal", valueObjectAnimal);
		
		RequestDispatcher rd = request.getRequestDispatcher("/wey/animal/UpdateAnimal.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
