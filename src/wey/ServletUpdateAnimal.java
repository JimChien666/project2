package wey;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletUpdateAnimal")
public class ServletUpdateAnimal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletUpdateAnimal() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int animalId = Integer.parseInt(request.getParameter("animalId").trim());
		int memberId = Integer.parseInt(request.getParameter("memberId").trim());
		String acceptionId = request.getParameter("acceptionId").trim();
		int breedId = Integer.parseInt(request.getParameter("breedId").trim());
		int gender = Integer.parseInt(request.getParameter("gender").trim());
		String coatColor = request.getParameter("coatColor").trim();
		int isAdoptionAvailable = Integer.parseInt(request.getParameter("isAdoptionAvailable").trim());
		String note = request.getParameter("note").trim();
		Date createdAt = null;
		Date updatedAt = null;
		Date deletedAt = null;
	   
		ValueObjectAnimal reg_Animal = new ValueObjectAnimal(animalId, memberId, acceptionId, breedId, gender, coatColor, isAdoptionAvailable, note, createdAt, updatedAt, deletedAt);
		DaoAnimal daoAnimal = new DaoAnimal();
		if (daoAnimal.updateAnimal(reg_Animal)){
          System.out.println("Get some SQL commands done!");
          request.getSession(true).invalidate();
          request.getRequestDispatcher("/ServletReadAnimal").forward(request,response);
        }else {
        	System.out.println("ServletUpdateAnimal error");
        }
	}

}
