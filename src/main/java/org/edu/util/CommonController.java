package org.edu.util;

import javax.inject.Inject;

import org.edu.service.IF_MemeberService;
import org.edu.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * CommonController 공통사용(Admin, Home)
 * @author 한미래
 *
 */
@Controller
public class CommonController {
	
	@Inject
	IF_MemeberService memberService;
	
	//REST-API컨트롤러 사용 
	//아래는 Rest-API백엔드 단. //Ajax(jsp)부분은 Rest-API의 프로트엔드 단.
	@RequestMapping(value="/id_check",method=RequestMethod.GET)
	
	
	//@ResponseBody애노테이션은 json텍스트데이터를 반환하는 역할.
	@ResponseBody
	public String id_check(@RequestParam("user_id") String user_id) {
		String result = "0"; //(중복값X)아이디 중복값을 체크하는 변수기본값은 중복값 없음.
		//REST-API서비스에서는 스프링을 통해서 Ajax로 에러메세지를 받을 수 없기 때문에 여기서 에러 처리를 해야함.
		try {
			MemberVO memberVO = memberService.readMember(user_id);
			if(memberVO != null) {
				result = "1"; //(중복값O)아이디 중복값이 있을 경우
			}
		} catch (Exception e) {
			//위 readMember메서드가 에러발생시
			result = e.toString(); 
		}
		return result; //에러 -1 //성공 0 ,1
	}
}
