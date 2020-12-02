package org.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping; //bind는 묶는다는 의미. /admin요청경로와 admin/home.jsp를 묶는다는 의미
import org.springframework.web.bind.annotation.RequestMethod;

//스프링에서 사용가능한 클래스를 빈(커피Bean)이라고 하고, @Controller 클래스를 사용하면 됨.
@Controller
public class AdminController {

	@RequestMapping(value="/admin/board_list",method=RequestMethod.GET)
	public String board_list () {
		return "admin/board/board_list";
	}
	
	@RequestMapping(value="/admin/member_write",method=RequestMethod.POST)
	public String member_write_do() {
		//아래 get방식의 폼 풀력 화면에서 데이터 전송받은 내용을 처리하는 바인딩.
		//DB베이스 입력/출력/삭제/수정 처리 다음에...
		return "redirect:/admin/member_list";
	}
	
	
	@RequestMapping(value="/admin/member/member_write",method=RequestMethod.GET)
	public String member_write() {
		return "admin/member/member_write";
	}
	
	@RequestMapping(value="/admin/member/member_view",method=RequestMethod.GET)
	public String member_view() {
		return "admin/member/member_view";
	}
	
	@RequestMapping(value="/admin/member_list",method=RequestMethod.GET)
	public String member_list() {
		return "admin/member/member_list";
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String admin() {
		return "admin/home";		
	}
	
}
