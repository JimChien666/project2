package max.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/ActionUpdate.do")
public class ActionUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processAction(request, response);
		
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		if(request.getParameter("id")!=null) {
			gotoModify(request,response);
			
		
			
		}
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		MembersDAO mDAO=new MembersDAO(session);
		int id = Integer.parseInt(request.getParameter("id"));
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

		
		mDAO.update(id,newMembers);
//		RequestDispatcher rd = request.getRequestDispatcher("/AdminLTE-3.0.5/addExample2.jsp");
//		rd.forward(request, response);

		
	
	}

	private void gotoModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		
		int id = Integer.parseInt(request.getParameter("id"));
		MembersDAO mDAO=new MembersDAO(session);
		List<Members> list = mDAO.select(id);//選
		request.setAttribute("list", list);//包
		RequestDispatcher rd = request.getRequestDispatcher("/max/update.jsp");//送
		rd.forward(request, response);
		
		
		
		
	}

}