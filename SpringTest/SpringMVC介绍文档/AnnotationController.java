package MVC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.core.io.OutputDecorator;

import lombok.Cleanup;



@Controller
public class AnnotationController {
	
	
	//所有的请求进来就先进入这里。然后设置了日期的格式
	//一般不使用
	@InitBinder
	public void InitBinder(WebDataBinder binder){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		simpleDateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(simpleDateFormat, true));
	}
	
	@RequestMapping("/method")
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("afas");
		
//		Model model = new Model();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg", "今天天气好热");
		modelAndView.setViewName("/index.jsp");
		return modelAndView;
	
	}
	
	
	@RequestMapping("/method2")
	public ModelAndView method2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("method2");
		
//		Model model = new Model();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg", "今天天气好热");
		modelAndView.setViewName("/index.jsp");
		return modelAndView;
	
	}
	@RequestMapping("/method3")
	public ModelAndView method3(HttpServletRequest request, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("method3" + session);
		
//		Model model = new Model();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg", "今天天气好热");
		modelAndView.setViewName("/index.jsp");
		return modelAndView;
	
	}
	@RequestMapping("/method4")
//	这个注解表示，传递的参数为name1可以被那个
	public ModelAndView method4(@RequestParam("name1") String name) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("method4" + name);
		
//		Model model = new Model();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg", "今天天气好热");
		modelAndView.setViewName("/index.jsp");
		return modelAndView;
	
	}
	@RequestMapping("/method5")
	public ModelAndView method5(Hello hello) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("method4" + hello.getName());
		
//		Model model = new Model();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg", "今天天气好热");
		
		modelAndView.setViewName("/index.jsp");
		
		/*
		 *   key == 类型的小写，多几个就是后面的会覆盖前面的
		 *   
		 * */
		modelAndView.addObject("afa");
		return modelAndView;
	}
	
	/*
	 *    地址栏传参
	 * */
	@RequestMapping("/delete/{delId}")
	public ModelAndView delete(@PathVariable("delId") long id) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("method4" + id);
		
//		Model model = new Model();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("msg", "今天天气好热");
		modelAndView.setViewName("/index.jsp");
		return modelAndView;
	
	}
	/*
	 *    地址栏传参
	 * */
	
	//@ModelAttribute("ooo")  //和@RequestMapping配合使用可以修改返回值的key
	@RequestMapping("value")
	public @ResponseBody String value() throws Exception {
		// TODO Auto-generated method stub
	
		System.out.println("value");
	
		return "hahaaha";
	
	}
	@RequestMapping(value = "/value2",method = RequestMethod.GET)//和@RequestMapping配合使用可以修改返回值的key
	public @ResponseBody Hello value2() throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("value");
	
		return new Hello("nihao","我们");
	
	}
	@RequestMapping("/fileupload")
	public @ResponseBody String upload(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,String username) throws IOException {
    
		System.out.println(file);
		String savePath = "/Users/apple/Desktop/uploadTest/";
		System.out.println(savePath+file.getOriginalFilename());

		InputStream iStream = file.getInputStream();

		FileUtils.copyInputStreamToFile(iStream, new File(savePath+file.getOriginalFilename()));
		
       iStream.close();

	   return "{a:fa}";
	}
	/**
	 * 上传多个附件的操作类
	 * @param multiRequest
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/doMultiUpload", method=RequestMethod.POST)
	public String doUploadFile2(MultipartHttpServletRequest multiRequest) throws IOException{

		Iterator<String> filesNames = multiRequest.getFileNames(); //获得所有的文件名
		while(filesNames.hasNext()){    //迭代，对单个文件进行操作
			String fileName =filesNames.next();
			MultipartFile file =  multiRequest.getFile(fileName);
			if(!file.isEmpty()){
//				log.debug("Process file: {}", file.getOriginalFilename());
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File("d:\\temp\\imooc\\", 
						System.currentTimeMillis()+ file.getOriginalFilename()));
			}

		}

		return "success";
	}
	@RequestMapping("/downLoad")
	public @ResponseBody void downLoad(HttpServletResponse response) throws IOException{
		@Cleanup ServletOutputStream oServletOutputStream = response.getOutputStream();
		
		@Cleanup FileInputStream iStream = new FileInputStream("/Users/apple/Desktop/testResourse/管理--下单(1).jpg");
		
		String string = "哈哈";
		//这个子可以设置为中文名字下载
		string = new String(string.getBytes("UTF-8"), "ISO8859-1");
		System.out.println(string);
		//设置文件下载头，表示下载为文件
		response.setHeader("Content-Disposition","attachment;filename="+string+".png");

		
	    IOUtils.copy(iStream, oServletOutputStream);
		
	}
	
/*
 *     日期使用 @DateTimeFormat(pattern="yyyy-MM-dd") 告诉要解析成什么格式
 * 
 * */	
	@RequestMapping("/dateHandle")
	public @ResponseBody void dateHandle(@DateTimeFormat(pattern="yyyy-MM-dd") Date date) throws IOException{
		System.out.println(date);
	   
	}
	
	@RequestMapping("/backData")
	@ResponseBody
	public Hello backDate2(){
	    
		return new Hello("name", "user", new Date());
	}
	
}
