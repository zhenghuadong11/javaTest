import javax.management.modelmbean.ModelMBean;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HandleTest {
public static void main(String[] args) {

	final ZZTStudentModel  modelMBean = new ZZTStudentModel();
	modelMBean.setName("name");
	modelMBean.setAge(10);
	modelMBean.setsId(9);
	
	HibernateHandle hibernateHandle = new HibernateHandle();
	hibernateHandle.databaseHandle(new HibernateHandleContent() {
		
		public void databaseHandle(Session session) {
			session.save(modelMBean);
		}
	});
	

	

	
}
}
