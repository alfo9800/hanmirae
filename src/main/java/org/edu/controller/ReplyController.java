package org.edu.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ReplayController.java 클래스
 * 댓글 구현 REST-API 전용 컨트롤러 
 * @author 한미래
 *
 */
@RestController
public class ReplyController {
	//댓글 입력 메서드
	@RequestMapping(value="/reply/reply_write",method=RequestMethod.POST)
	public ResponseEntity<String>reply_write() {
		ResponseEntity<String> responseEntity =
				new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		//ResponseEntity는 json텍스트를 반환하는데,
		//전송내용:"SUCESS",					전송상태:Httstatus.OK(200)
		//전송내용:e.getMessage() 실패메세지 값. 전송상태:HttpStatus.BAD_REQUEST(400)
		return responseEntity;
	}
	//기존 @Controller의 메서드의 반환 값은 파일 위치였음.
	//대신 @RestController의 메서드 반환 값ResponseEntity는 json텍스트로서 Ajax로 호출한 jsp로 리턴값을 보내게 됨.
}
