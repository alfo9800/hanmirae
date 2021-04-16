package org.edu.dao;

import java.util.List;

import org.edu.vo.ChartVO;
import org.edu.vo.MemberVO;
import org.edu.vo.PageVO;
import org.springframework.http.ResponseEntity;

/**
 * 회원관리 DAO 메서드 명세(=목차) 인터페이스 명시.
 * @author 한미래
 *
 */
public interface IF_MemberDAO {
	public int countMember(PageVO pageVO) throws Exception;
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception;
	public void insertMember(MemberVO memberVO) throws Exception;
	public void deleteMember(String user_id) throws Exception;
	public MemberVO readMember(String user_id) throws Exception; //회원상세보기 이므로 void가 아닌 MemberVO만 있으면 됨.
	public void updateMember(MemberVO memberVO) throws Exception;
	public ChartVO getData(); //chart test용 RestAPI
	public void insertData(ChartVO chartVO);
	public void updateData(ChartVO chartVO); //chart test용 RestAPI
	public void delData();
}

//위 throws 스프링을 예외보내면, 나중에 예외처리 전용 error.jsp를 만들어서 에러메세지를 뿌리는 사용자단 사용할 것임.