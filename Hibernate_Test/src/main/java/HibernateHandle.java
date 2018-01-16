import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateHandle {
	static SessionFactory sessionFactory = null;
	
    static{
    	Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
    	sessionFactory = configuration.buildSessionFactory();
    }
    
    static public Session getCurrentSession(){
    	return sessionFactory.getCurrentSession();
    }
    
    public void databaseHandle(HibernateHandleContent content){
    	Session session = null;

		try {
			session = HibernateHandle.getCurrentSession();
			Transaction transaction = session.beginTransaction();
		    content.databaseHandle(session);
		    transaction.commit();
		} catch (Exception e) {
			if (session != null && session.getTransaction().isActive()) {
				session.getTransaction().rollback();
				if (session.isOpen()) {
					session.close();
				}
			}
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
    	
    	
    }
    
}
