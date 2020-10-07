package nn;


import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.java.javaURLContextFactory;

import DAO.Animals;
import DAO.Forums;
import DAO.JdbcDao;
import DAO.Members;
import mvcdemo.bean.StudentBean;

/**
 * Servlet implementation class TestDB
 */
@WebServlet("/TestDB")
public class TestDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(CHARSET_CODE);
	    response.setContentType(CONTENT_TYPE);
		System.out.println(request.getParameter("name"));
		if (request.getParameter("submit")!=null)
		     gotoSubmitProcess(request, response);
	}
	public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	  {
	    String name;
	    String birthyear, birthmonth, birthday;
	    String id;
	    String zipcode;
	    String address;
	    String phone;
	    String mailaddress;
	   
	    name = request.getParameter("name").trim();
	    birthyear = request.getParameter("birthyear").trim();
	    birthmonth = request.getParameter("birthmonth").trim();
	    birthday = request.getParameter("birthday").trim();
	    id = request.getParameter("id").trim();
	    zipcode = request.getParameter("zipcode").trim();
	    address = request.getParameter("address").trim();
	    phone = request.getParameter("phone").trim();
	    mailaddress = request.getParameter("mailaddress").trim();
	    StudentBean reg_student =  new StudentBean(name, birthyear, birthmonth, birthday, id, zipcode, address, phone, mailaddress);
	    request.getSession(true).setAttribute("reg_student",reg_student);
	    request.getRequestDispatcher("/DisplayStudent.jsp").forward(request,response);
	  }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
