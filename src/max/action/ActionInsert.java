package max.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import max.model.Members;
import max.model.MembersDAO;
import max.util.HibernateUtil;



@WebServlet("/ActionInsert.do")
public class ActionInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	processAction(request,response)	;
	
	

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	processAction(request,response)	;
	}
	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");  //setup response character encoding type
		response.setContentType("text/html");   //setup response content type
		response.setCharacterEncoding("UTF-8");	
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session=factory.getCurrentSession();
		
		try {
			
			
			MembersDAO mDAO=new MembersDAO(session);
//			String id=request.getParameter("id");		
			String account = request.getParameter("account");			
			String password = request.getParameter("password");
			String name = request.getParameter("name");	
			String sex = request.getParameter("sex");
			String tel = request.getParameter("tel");
			String email = request.getParameter("email");
			String income = request.getParameter("income");
			String address = request.getParameter("address");
			int adoptedLevelId =  Integer.parseInt(request.getParameter("adoptedLevelId"));
			String memberType = request.getParameter("memberType");
 
			Members newMembers = new Members( memberType, name, sex, tel, income, account, password, email, address, adoptedLevelId);
			
			mDAO.insert(newMembers);

//			request.getRequestDispatcher("/max/addExampleAddSuccess.jsp").forward(request, response);
		
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();

		}finally {

			System.out.println("Session Closed");
			
		}
	}
}		