package tw.wey.zOld;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ServletDeleteAnimal")
public class ServletDeleteAnimal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletDeleteAnimal() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/ReadAllAnimals").forward(request,response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//取得欲修改的ID，轉為數字
		String animalIdString = request.getParameter("animalId");
		int animalId = 0;
		if(animalIdString!=null) {
			animalId = Integer.parseInt(animalIdString);
		}
		DaoAnimal daoAnimal = new DaoAnimal();
		Boolean flag = daoAnimal.deleteAnimal(animalId);
		if (flag) {
			session.setAttribute("AnimalDeleteMsg", "動物編號(" + animalId + ")刪除成功");
		}else {
			session.setAttribute("AnimalDeleteMsg", "動物編號(" + animalId + ")刪除失敗");
		}
		System.out.println("Delete animal No."+animalId+".");
		request.getRequestDispatcher("/ReadAllAnimals").forward(request,response);
		return;
	}

}
