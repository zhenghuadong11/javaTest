import javax.management.modelmbean.ModelMBean;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HandleTest {
public static void main(String[] args) {
	ZZTStudentModel  modelMBean = new ZZTStudentModel();
	modelMBean.setName("name");
	modelMBean.setAge(10);
	modelMBean.setsId(9);
	
	SessionFactory sFactory = null;
	Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
	sFactory = configuration.buildSessionFactory();
	
	
	Session session = sFactory.getCurrentSession();
	
	Transaction transaction = session.beginTransaction();
	session.save(modelMBean);
	transaction.commit();
	
}
}
