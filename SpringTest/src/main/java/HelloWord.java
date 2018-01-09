import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.sun.org.apache.xml.internal.utils.Hashtree2Node;

public class HelloWord {
	private String name;
	
public void sayHello() {
	System.out.println(name + "say hello");
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

}

class HelloWordTest{
	public static void main(String[] args) {
//		HelloWord helloWord = null;
//		Resource resource = new ClassPathResource("ApplicationConetxt.xml");
//		BeanFactory beanFactory = new XmlBeanFactory(resource);
//		
//		helloWord = (HelloWord) beanFactory.getBean("helloWorld");
//		helloWord.sayHello();
		
		Hello hello = new Hello();
		(hello).setName("fas");
		
		String string = hello.getName();
		System.out.println(string);
//		hello.getName();
	}
	
	
}
