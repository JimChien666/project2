package jim;


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
import DAO.Members;
import jim.entity.Products;

/**
 * Servlet implementation class TestDB
 */
@WebServlet("/TestJim")
public class TestJim extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestJim() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("hi");		

		
		request.setCharacterEncoding("UTF-8");
		
		Forums forum = new Forums();
		forum.setId(123321);
		forum.setMemberId(7527825);
		forum.setContent("bitch yo");
//		Date date=new Date(0, 0, 0);
		

		java.util.Date date = new java.util.Date();
		
		JdbcDao jdbcDao = new JdbcDao();//new class
		List<Products> list = jdbcDao.listProducts();
		for(Products listp :list) {
			System.out.println(listp.getDescript());
		}
		
		//Test products insert
		Products product= new Products();
		
		product.setDescript("此店絕不賣滯銷書、二手書、過期書");	
		jdbcDao.insertProducts(product);
		
		
//		forum.setCreateAt(date);
//		jdbcDao.insertForum(forum);		
		
//		Animals animals=new Animals();
//		animals.setId(1);		
//		memberque.setEmail("EEE"); //���u1�AEmail�令EEE		
//		boolean aa=jdbcDao.updateAnimal(animals);
//		System.out.println(aa); //T or F
		
//		System.out.println(jdbcDao.listAnimals());
//		List<Animals> list = jdbcDao.listAnimals();
//		for(Animals aml :list) {
//			System.out.println(aml.getAge());
//		}
		//Test members insert
//		Members member= new Members();
//		member.setId(2);
//		member.setAccount("account");	
//		jdbcDao.insertMembers(member);
		
		//Test members query		
//		Members memberque= new Members();
//		memberque.setId(1);
//		memberque.setEmail("EEE"); //���u1�AEmail�令EEE		
//		jdbcDao.queryMembers(memberque);
		
		
		
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
