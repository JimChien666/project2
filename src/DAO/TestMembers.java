package DAO;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import DAO.Forums;

/**
 * Servlet implementation class TestDB
 */
@WebServlet("/TestMembers")
public class TestMembers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestMembers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hi");
		
		JdbcDao jdbcDao = new JdbcDao();
		request.setCharacterEncoding("UTF-8");
//		Members member = new Members();
//		member.setName("nn");
//		member.setAccount("s1875678");
//		member.setPassword("123");
//		member.setEmail("a3363170@yahoo.com.tw");
//		member.setIncome(8000);
//		member.setPhone("0936236145");
//		jdbcDao.insertMembers(member);

		//Test members insert
		jdbcDao.queryMembers("2001");
		//Test members query		
		Members member= new Members();
		
		
		
//		
		
		
		//Test members update		
//		Members memberupd= new Members();
//		memberupd.setId(1);
//		memberupd.setEmail("AEE"); //���u1�AEmail�令EEE		
//		jdbcDao.updateMembers(memberupd);
//		
		
		//Test members delete		
//		Members memberdel= new Members();
//		memberdel.setId(1);	
//		jdbcDao.deleteMembers(memberdel);
		
//		System.out.println(jdbcDao.listMembers());
//		List<Members> list2 = jdbcDao.listMembers();
//		for(Members mem :list2) {
//			
//			System.out.println(mem.getEmail());//Members Email ���L
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
