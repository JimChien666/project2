package wey.animal;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletReadAnimal")
public class ServletReadAnimal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletReadAnimal() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		DaoAnimal daoAnimal = new DaoAnimal();
		List<ValueObjectAnimal> list = daoAnimal.listAnimals();
		request.setAttribute("AnimalsList", list);
		
//		DaoFilesOfAnimal daoFilesOfAnimal = new DaoFilesOfAnimal();
//		List<ValueObjectFilesOfAnimal> list2 = daoFilesOfAnimal.listFilesOfAnimal();
//		request.setAttribute("listFilesOfAnimal", list2);
		
		RequestDispatcher rd = request.getRequestDispatcher("/wey/animal/ReadAnimal.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
