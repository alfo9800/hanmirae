package org.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; //bind는 묶는다는 의미. /admin요청경로와 admin/home.jsp를 묶는다는 의미
import org.springframework.web.bind.annotation.RequestMethod;

//스프링에서 사용가능한 클래스를 빈(커피Bean)이라고 하고, @Controller 클래스를 사용하면 됨.
@Controller
public class AdminController {

	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String admin() {
		return "admin/home";		
	}
	
}
