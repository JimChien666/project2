package Members;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import Members.MemberBean;
import Members.MemberDAO;
import oracle.net.aso.c;

/**
 * Servlet implementation class MembersServlet
 */
@WebServlet("/MembersServlet")
public class MembersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	private static final String CHARSET_CODE = "UTF-8";

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(CHARSET_CODE);
		response.setContentType(CONTENT_TYPE);
		String submit = request.getParameter("submit");
		// To prevent caching
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", -1); // Prevents caching at the proxy server
		System.out.println(submit);
		if (submit.equals("submit")) {
			gotoSubmitProcess(request, response);
		} else if (submit.equals("confirm")) {
			gotoConfirmProcess(request, response);
		} else if (submit.equals("delete")) {
			gotoDelectProcess(request, response);
		} else if (submit.equals("submitUpdate")) {
			gotoUpdateProcess(request, response);
		} else if (submit.equals("selectId")) {
			gotoIdCheck(request, response);
		} else if (submit.equals("red_list")) {
			System.out.println("hi");
			gotoList(request, response);
		}
//		else if (submit.equals("brsearch")) {
//			gotoSearch(request, response);
//		} 

	}

	public void gotoSubmitProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name;
		String sex;
		String tel;
		String email;
		String address;
		String income;
		String account;
		String password;
		String memberType;
		
		name = request.getParameter("name").trim();
		sex = request.getParameter("sex").trim();
		tel = request.getParameter("tel").trim();
		email = request.getParameter("email").trim();
		address = request.getParameter("address").trim();
		income = request.getParameter("income").trim();
		account = request.getParameter("account").trim();
		password = request.getParameter("password").trim();
		memberType = request.getParameter("memberType").trim();
		
		MemberBean rel_Member = new MemberBean(name, income, tel, account, password, email, address, memberType, sex);
		request.getSession(true).setAttribute("rel_Member", rel_Member);
		request.getRequestDispatcher("./DisplayMember.jsp").forward(request, response);
	}

	public void gotoConfirmProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {

			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");

			conn = ds.getConnection();

			MemberDAO memberDAO = new MemberDAO(conn);
			MemberBean memberData = (MemberBean) request.getSession(true).getAttribute("rel_Member");
			if (memberDAO.insertMembers(memberData)) {
				System.out.println("Get some SQL commands done!");
				request.getSession(true).invalidate();
				request.getRequestDispatcher("/DoneMember.jsp").forward(request, response);
			}
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}

	}

	public void gotoDelectProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Id;
		Id = request.getParameter("Id").trim();
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");

			conn = ds.getConnection();

			MemberDAO memberDAO = new MemberDAO(conn);

			if (memberDAO.deleteMembers(Id)) {
				System.out.println("Get some SQL commands done!");
				request.getSession(true).invalidate();
				request.getRequestDispatcher("/DeleteMember.jsp").forward(request, response);
			}
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}

	}

	public void gotoIdCheck(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;
		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");

			conn = ds.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select * from Members");
			String inputcontent = request.getParameter("title2");
			System.out.println(inputcontent);
			while (rs.next()) {
				List list = new ArrayList<>();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String sex = rs.getString("sex");
				String tel = rs.getString("tel");
				String email = rs.getString("email");
				String address = rs.getString("address");
				String income = rs.getString("income");
				String account = rs.getString("account");
				String password = rs.getString("password");
				String memberType = rs.getString("memberType");

				String idString = String.valueOf(id);
//				System.out.println(idString);
//				System.out.println(inputcontent.equals(idString));
				MemberBean memberBean = new MemberBean(name, income, tel, account, password, email, address, memberType, sex);
						
				if (inputcontent.equals(idString)) {
					request.getSession(true).setAttribute("updcon", memberBean);
					request.setAttribute("idStr", idString);
					request.getRequestDispatcher("./UpdateContent.jsp").forward(request, response);
				}
			}
			return;
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
			ne.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}
	}

	public void gotoUpdateProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id;
		String name;
		String sex;
		String tel;
		String email;
		String address;
		String income;
		String account;
		String password;
		String memberType;
		
		String idStr = request.getParameter("id").trim();
//		System.out.println(idStr);
		id = Integer.parseInt(idStr);
		name = request.getParameter("name").trim();
		sex = request.getParameter("sex").trim();
		tel = request.getParameter("tel").trim();
		email = request.getParameter("email").trim();
		address = request.getParameter("address").trim();
		income = request.getParameter("income").trim();
		account = request.getParameter("account").trim();
		password = request.getParameter("password").trim();	
//		adoptedLevelId = request.getParameter("adoptedLevelId").trim();
		memberType = request.getParameter("memberType").trim();	

		MemberBean memberData = new MemberBean(name, income, tel, account, password, email, address, memberType, sex);
		memberData.setId(id);
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;

		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");

			conn = ds.getConnection();

			MemberDAO memberDAO = new MemberDAO(conn);

			if (memberDAO.updateMembers(memberData)) {
				System.out.println("Get some SQL commands done!");
				request.getSession(true).invalidate();
				request.getRequestDispatcher("./UpdateMember.jsp").forward(request, response);
			}
		} catch (NamingException ne) {
			System.out.println("Naming Service Lookup Exception");
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Connection Pool Error!");
			}
		}

	}

	public void gotoList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		DataSource ds = null;
		InitialContext ctxt = null;
		Connection conn = null;
		try {
			ctxt = new InitialContext();
			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");
			conn = ds.getConnection();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		MemberDAO memberDAO = new MemberDAO(conn);
		List<MemberBean> list = memberDAO.getAllMembers();
		
		request.setAttribute("alist", list);
		request.getRequestDispatcher("/ListMember.jsp").forward(request, response);
	}

//	private void gotoSearch(HttpServletRequest request, HttpServletResponse response) {
//
//		DataSource ds = null;
//		InitialContext ctxt = null;
//		Connection conn = null;
//		try {
//			ctxt = new InitialContext();
//			ds = (DataSource) ctxt.lookup("java:comp/env/jdbc/xe");
//			conn = ds.getConnection();
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		String a = request.getParameter("title1");
//		
//		System.out.println(a);
//		
//		MemberDAO articleDao = new MemberDAO(conn);
//		List<MemberBean> atlsrh = memberDAO.getMembersSearch(a);
//
//		System.out.println(atlsrh);
//		
//		request.setAttribute("atlsrh", atlsrh);
//		try {
//			request.getRequestDispatcher("/DisplaySearch.jsp").forward(request, response);
//		} catch (ServletException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
