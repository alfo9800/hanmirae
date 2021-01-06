package org.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.edu.dao.IF_BoardDAO;
import org.edu.dao.IF_ReplyDAO;
import org.edu.vo.BoardVO;
import org.edu.vo.PageVO;
import org.edu.vo.ReplyVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@Inject
	private IF_ReplyDAO replyDAO;
	
	@Inject
	private IF_BoardDAO boardDAO;
	
	//댓글 리스트 메서드
	@RequestMapping(value="/reply/reply_list/{bno}/{page}", method=RequestMethod.POST) //특이사항:RestAPIdptj Select는 GET이 기본.
	public ResponseEntity<Map<String,Object>>reply_list(@PathVariable("bno") Integer bno, @PathVariable("page") Integer page) throws Exception {
		//페이징 계산식 처리
		PageVO pageVO = new PageVO();
		pageVO.setPage(page); //조건은 Ajax로 호출 시 page변수는 반드시 보내야 합니다.
		pageVO.setPerPageNum(3); //페이지 하단에 보이는 페이징 번호의 갯수
		pageVO.setQueryPerPageNum(5); //한 페이지 당 보여줄 댓글 갯수
		//현재 게시물에 달린 댓글 갯수 구하기는: 게시물관리테이블에 있는 reply_count를 가져다가 사용할 것임. 그래서 따로 만들지 않음.		
		int replyCount = replyDAO.selectReplyCount(bno);
		pageVO.setTotalCount(replyCount); //전체 댓글 개수 구해서 set하는 순간이 필수. prev,next구할 때 필요.

		//-----------------------------------------------
		System.out.println("디버그: 패스베리어블 변수는 " + bno);
		ResponseEntity<Map<String,Object>> result = null;
		//-----------------------------------------------
		Map<String,Object> resultMap = new HashMap<String,Object>(); //해시맵타입으로 Json저장소 생성
		//Map변수=데이터형 [{'key':'List<>'},{'key':'Class'},{'':''},...]
		Map<String,Object> dummyMap1 = new HashMap<String,Object>();
		dummyMap1.put("rno", "1");
		dummyMap1.put("replyer", "관리자1");
		dummyMap1.put("reply_text", "컨트롤1 댓글 입력1 테스트입니다.");
		Map<String,Object> dummyMap2 = new HashMap<String,Object>();
		dummyMap2.put("rno", "2");
		dummyMap2.put("replyer", "관리자2");
		dummyMap2.put("reply_text", "컨트롤2 댓글 입력2 테스트입니다.");
		
		List<Object> dummyMapList = new ArrayList<Object>();
		dummyMapList.add(0, dummyMap1);
		dummyMapList.add(1, dummyMap2);
		//resultMap.put("replyList", dummyMapList); //Put메서드로 Key:Value Json데이터 생성.	
		//-----------------------------------------------
		
		//위 dummyMapList대신 DB tbl_reply 테이블에서 조회된 결과값으로 대체.
		try {
			List<ReplyVO> replyList = replyDAO.selectReply(bno,pageVO);
			if(replyList.isEmpty()) {
				//result = null; //jsp에서 받는 값이 text일 때 적용.
				resultMap.put("replyList", null);
				result = new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.NO_CONTENT); //코드204
			}else {
				resultMap.put("replyList", replyList);
				resultMap.put("pageVO", pageVO); //페이징처리 때문에 추가함.
				//resultMap을 Json데이터로 반환하려면, jackson-databind 모듈필수 (pom.xml에 추가함.)
				result = new ResponseEntity<Map<String,Object>>(resultMap, HttpStatus.OK); //코드200
			}
		} catch (Exception e) {
			result = new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR); //코드 500
		}	
		
		return result;
	}
	
	//댓글 삭제 메서드
	@RequestMapping(value="/reply/reply_delete/{bno}/{rno}",method=RequestMethod.DELETE)
	public ResponseEntity<String> reply_delete(@PathVariable("bno") Integer bno, @PathVariable("rno") Integer rno) {
		ResponseEntity<String> result = null;
		try {
			replyDAO.deleteReply(rno);
			result = new ResponseEntity<String>("success",HttpStatus.OK);
			replyDAO.updateCountReply(bno); //서브쿼리를 사용해서 reply_count필드를 업데이트
		} catch (Exception e) {
			result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return result;
	}
	
	//댓글 수정 메서드
	@RequestMapping(value="/reply/reply_update",method=RequestMethod.PATCH) //post(전통방식),patch(최근방식:RestAPI)
	public ResponseEntity<String> reply_update(@RequestBody ReplyVO replyVO) {
		ResponseEntity<String> result = null;
		try {
			replyDAO.updateReply(replyVO);
			result = new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return result;
	}
	
	//댓글 입력 메서드
	@RequestMapping(value="/reply/reply_write",method=RequestMethod.POST)
	public ResponseEntity<String>reply_write(@RequestBody ReplyVO replyVO) {
		//@RequestBody클래스는 ajax로 보내온 폼데이터응 ReplyVO클래스레 바인딩 시켜주는 애노테이션클래스이다.
		//ResponseEntity<String> responseEntity =	new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		//ResponseEntity는 json텍스트를 반환하는데,
		//전송내용:"SUCESS",					전송상태:Httstatus.OK(200)
		//전송내용:e.getMessage() 실패메세지 값. 전송상태:HttpStatus.BAD_REQUEST(400)
		ResponseEntity<String> result = null;
		
		/* 여기서try/catch(예외처리)를 하는 이유=상위 메서드(Spring)로 보내지 않는 이유:
			:RestAPI에서 예외 메세지를 개발자가 제공하기 위해서. */
		//ResponseEntity객체클래스형 String값을 ajax로 호출한 페이지 반환.
		try {
			replyDAO.insertReply(replyVO);
			result = new ResponseEntity<String>("success", HttpStatus.OK);
			replyDAO.updateCountReply(replyVO.getBno());
		} catch (Exception e) {
			result = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return result;
	}
	//기존 @Controller의 메서드의 반환 값은 파일 위치였음.
	//대신 @RestController의 메서드 반환 값ResponseEntity는 json텍스트로서 Ajax로 호출한 jsp로 리턴값을 보내게 됨.
}
