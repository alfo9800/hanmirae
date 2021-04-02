package org.edu.controller;

import java.util.List;

import javax.inject.Inject;

import org.edu.dao.IF_MemberDAO;
import org.edu.vo.MemberVO;
import org.edu.vo.PageVO;
import org.hsqldb.lib.SimpleLog;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JsonDataController {
	@Inject
	private IF_MemberDAO memberDAO;
	
	private Logger logger = Logger.getLogger(SimpleLog.class);
		
	//RestAPI 인증server (안드로이드앱에서 로그인에 사용됨)
	@RequestMapping(value="/android/login",method=RequestMethod.POST)
	public ResponseEntity<MemberVO> androidLogin(@RequestParam("txtUsername") String user_id, @RequestParam("txtPassword") String user_pw) {		
		ResponseEntity<MemberVO> entity = null;
		
		//여기에서 MemberVO memberVO = memberDAO.readMember(user_id); 에러처리함.
		try {
			MemberVO memberVO = memberDAO.readMember(user_id);
			String bcryptPassword = memberVO.getUser_pw();
			
			//스프링 시큐리티 4.x bcryptPasswordEncoder로 암호화된 값을 비교
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10); //매개변수 10은 암호화 강도.
			if(passwordEncoder.matches(user_pw, bcryptPassword)) {
				logger.debug("계정정보 일치");
				entity = new ResponseEntity<>(memberVO, HttpStatus.OK); //code 200
			}else {
				logger.debug("계정정보 불일치");
				entity = new ResponseEntity<>(HttpStatus.NO_CONTENT); //code 204
			}
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST); //code 
		}
		
		return entity; //json데이터(key:value)로 반환값 리턴
	}
	
	//RestAPI서버 회원목록을 출력 기능
	@RequestMapping(value="/android/list",method=RequestMethod.POST)
	public ResponseEntity<List<MemberVO>> androidMember() {
		ResponseEntity<List<MemberVO>> entity = null;
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);
		pageVO.setPerPageNum(10);
		pageVO.setQueryStartNo(1000); //쿼리에서 1000명까지 허용
		
		try {
			entity = new ResponseEntity<>(memberDAO.selectMember(pageVO),HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
