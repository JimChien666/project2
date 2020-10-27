package max.model;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.query.Query;



public class MembersDAO {
	
	

	private Session session;

	public MembersDAO(Session session) {
		this.session=session;
	}
	
	public Members insert(Members members) {
		Members result=session.get(Members.class, members.getId());
		
		if(result==null) {
			session.save(members);
			return members;
		}
		
		return null;
	}
	
	public List<Members> select(int id) {
		Query<Members> query=session.createQuery("from Members where id="+id,Members.class);
		List<Members> list=query.list();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getId()!=id) {
				return null;
			}
		}
		return list;
	}
	
	public List<Members> selectAll() {
		Query<Members> query=session.createQuery("From Members",Members.class);
		List<Members>list=query.list();
		return list;
	}
	
	public Members update(int id,Members member) {
		Members result=session.get(Members.class,id);
		if(result!=null) {
		
			result.setName(member.getName());
			result.setTel(member.getTel());
			result.setIncome(member.getIncome());
			result.setAddress(member.getAddress());
			result.setEmail(member.getEmail());
		
		}
		return result;
	}
	
	public boolean delete(int id) {
		Members result=session.get(Members.class,id);
		if(result!=null) {
			session.delete(result);
			return true;
		}
		return false;
	}



	


	}
		
		
	


