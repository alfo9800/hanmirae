package org.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping; //bind는 묶는다는 의미. /admin요청경로와 admin/home.jsp를 묶는다는 의미
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//스프링에서 사용가능한 클래스를 빈(커피Bean)이라고 하고, @Controller 클래스를 사용하면 됨.
@Controller
public class AdminController {

	@RequestMapping(value="/admin/board/board_list",method=RequestMethod.GET)
	public String board_list () throws Exception {
		return "admin/board/board_list";
	}
	
	//메서드 오버로딩(예로, 동영상 로딩중..., 로딩된 매개변수가 다르면, 메서드 이름을 중복해서 사용가능함.
	@RequestMapping(value="/admin/member/member_write",method=RequestMethod.POST)
	public String member_write_(@RequestParam("user_name") String user_name) throws Exception {
		//아래 get방식의 폼 풀력 화면에서 데이터 전송받은 내용을 처리하는 바인딩.
		//DB베이스 입력/출력/삭제/수정 처리 다음에...
		return "redirect:/admin/member/member_list";
	}
	
	
	@RequestMapping(value="/admin/member/member_write",method=RequestMethod.GET)
	public String member_write() throws Exception {
		return "admin/member/member_write";
	}
	
	//member_list.jsp에서 데이터를 수신하는 역할 @RequestParam("키 이름") 리퀘스트파라미터 클래스 사용
	//현재 컨트롤러 클래스에서 member_view.jsp로 데이터를 보내는 역할. Model 클래스 사용
	//데이터 흐름: member_list.jsp -> @RequestParam("user_id)수신, Model송신 -> member_view.jsp
	@RequestMapping(value="/admin/member/member_view",method=RequestMethod.GET)
	public String member_view(@RequestParam("user_id") String user_id, Model model ) throws Exception {
		//위에서 수신한 user_id를 개발자가 만든 user_id2이름으로 member_view.jsp로 보냄.
		model.addAttribute("user_id2", user_id + "<script>alert('어서오세요');</script> 님");
		return "admin/member/member_view";
	}
	
	@RequestMapping(value="/admin/member/member_list",method=RequestMethod.GET)
	public String member_list(Model model) throws Exception {
		String[][] members = {
				{"admin","찐관리자","admin@aka.com","true","2020-12-04","ROLE_ADMIN"},
				{"user","일반사용자","user@aka.com","false","2020-12-04","ROLE_USER"}
		};
		//{"user_id":"admin","user_name":"관리자",...} 나중에 해시#데이터가 만들어진다. (해시..>그물-낚시)
		model.addAttribute("members", members);
		return "admin/member/member_list";//member_list.jsp로 msmbers변수명으로 데이터를 전송.
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String admin() throws Exception {
		return "admin/home";		
	}
	
}
