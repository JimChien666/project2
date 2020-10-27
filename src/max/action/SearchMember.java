package max.action;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import max.model.Members;
import max.util.HibernateUtil;

public class SearchMember {

	private static Session session;

	public void processAction1() {

		Members mBean = session.get(Members.class, 42);
		if (mBean != null) {
			System.out.println("account:" + mBean.getAccount());
			System.out.println("name:" + mBean.getName());
			System.out.println("sex:" + mBean.getSex());
			System.out.println("income:" + mBean.getIncome());
			System.out.println("tel:" + mBean.getTel());
			System.out.println("address:" + mBean.getAddress());
			System.out.println("email:" + mBean.getEmail());
			System.out.println("adoptedLevelId:" + mBean.getAdoptedLevelId());

		} else {
			System.out.println("data not found!!");
		}

	}
	
	public void processQueryAll() {
		Query<Members> query=session.createQuery("From Members",Members.class);
		List<Members>list=query.list();
		for(Members mBean:list) {
			
			System.out.println("account:" + mBean.getAccount());
			System.out.println("name:" + mBean.getName());
			System.out.println("sex:" + mBean.getSex());
			System.out.println("income:" + mBean.getIncome());
			System.out.println("tel:" + mBean.getTel());
			System.out.println("address:" + mBean.getAddress());
			System.out.println("email:" + mBean.getEmail());
			System.out.println("adoptedLevelId:" + mBean.getAdoptedLevelId());
		}
	}
	
	public void processDelete(Members mBean) {
		session.delete(mBean);
	}

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		session = factory.getCurrentSession();
		session.beginTransaction();

		SearchMember action1 = new SearchMember();
		action1.processDelete(new Members());
		action1.processQueryAll();
		
		session.getTransaction().commit();
		HibernateUtil.closeSessionFactory();

	}
}
