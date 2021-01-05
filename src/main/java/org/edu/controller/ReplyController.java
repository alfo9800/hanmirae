package org.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//댓글 리스트 메서드
	@RequestMapping(value="/reply/reply_list/{bno}", method=RequestMethod.GET)
	public ResponseEntity<Map<String,Object>>reply_list() {
		ResponseEntity<Map<String,Object>> result = null;
		//-----------------------------------------------
		Map<String,Object> resultMap = new HashMap<String,Object>(); //해시맵타입으로 Json저장소 생성
		//Map변수=데이터형 [{'key':'List<>'},{'key':'Class'},{'':''},...]
		Map<String,Object> dummyMap1 = new HashMap<String,Object>();
		dummyMap1.put("rno", "1");
		dummyMap1.put("replyer", "관리자");
		dummyMap1.put("reply_text", "컨트롤1 댓글 입력1 테스트입니다.");
		Map<String,Object> dummyMap2 = new HashMap<String,Object>();
		dummyMap1.put("rno", "2");
		dummyMap2.put("replyer", "관리자2");
		dummyMap2.put("reply_text", "컨트롤2 댓글 입력2 테스트입니다.");
		
		List<Object> dummyMapList = new ArrayList<Object>();
		dummyMapList.add(0, dummyMap1);
		dummyMapList.add(1, dummyMap2);
		//-----------------------------------------------
		//위 dummyMapList대신 DB tbl_reply 테이블에서 조회된 결과값으로 대체.
		resultMap.put("replyList", dummyMapList); //Put메서드로 Key:Value Json데이터 생성.
		//resultMap을 Json데이터로 반환하려면, jackson-databind 모듈필수 (pom.xml에 추가함.)			
		result = new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK);
		//HttpStatus.NO_CONTENT 는 204 조회된 데이터가 없음.
		return result;
	}
	
	
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
