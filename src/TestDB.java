

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Animals;
import DAO.JdbcDao;
import DAO.Members;

/**
 * Servlet implementation class TestDB
 */
@WebServlet("/TestDB")
public class TestDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		// TODO Auto-generated method stub
		System.out.println("hi");
		System.out.println("how are u?");
		System.out.println("how are u?");
		System.out.println("Only only you~you~you~");
		System.out.println("你吃我的洨蝴蝶");
		System.out.println("然後蕭敬騰就被羅志祥打了");
		
		JdbcDao jdbcDao = new JdbcDao();
		request.setCharacterEncoding("UTF-8");
		
		System.out.println(jdbcDao.listAnimals());
		List<Animals> list = jdbcDao.listAnimals();
		for(Animals aml :list) {
			System.out.println(aml.getAge());
		}
		//Test members insert
//		Members member= new Members();
//		member.setId(2);
//		member.setAccount("account");	
//		jdbcDao.insertMembers(member);
		
		//Test members query		
		Members memberque= new Members();
		memberque.setId(1);
//		memberque.setEmail("EEE"); //員工1，Email改成EEE		
		jdbcDao.queryMembers(memberque);
		
		
		
		//Test members update		
//		Members memberupd= new Members();
//		memberupd.setId(1);
//		memberupd.setEmail("AEE"); //員工1，Email改成EEE		
//		jdbcDao.updateMembers(memberupd);
//		
		
		//Test members delete		
//		Members memberdel= new Members();
//		memberdel.setId(1);	
//		jdbcDao.deleteMembers(memberdel);
		
		System.out.println(jdbcDao.listMembers());
		List<Members> list2 = jdbcDao.listMembers();
		for(Members mem :list2) {
			
			System.out.println(mem.getEmail());//Members Email 全印
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
