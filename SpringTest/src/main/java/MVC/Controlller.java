package MVC;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class Controlller implements Controller{

	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("afas");
		
//		Model model = new Model();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg", "今天天气好热");
		modelAndView.setViewName("/index.jsp");
		return modelAndView;
	
	}
   
	
}
