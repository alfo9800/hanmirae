package org.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.edu.dao.IF_MemberDAO;
import org.edu.vo.MemberVO;
import org.edu.vo.PageVO;
import org.springframework.stereotype.Service;

/**
 * MemberServiceImpl는 DAO 메서드 호출을 구현
 * @author 한미래
 *
 */
@Service
public class MemberServiceImpl implements IF_MemeberService  {

	@Inject
	IF_MemberDAO memberDAO;//IF_MemberDAO 주입받아서 memberDAO 오브젝트 생성.
	
	@Override
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception {
		//회원정보 서비스조회 DAO클래스의 호출
		return memberDAO.selectMember(pageVO);
	}

	@Override
	public int countMember(PageVO pageVO) throws Exception {
		//검색된 회원의 전체 명수 구하기
		return memberDAO.countMember(pageVO);
	}

	@Override
	public MemberVO readMember(String user_id) throws Exception {
		//선택한 회원의 상세 정보 구하기
		return memberDAO.readMember(user_id);
	}

	@Override
	public void deleteMember(String user_id) throws Exception {
		//선택한 회원의 정보를 삭제하기
		memberDAO.deleteMember(user_id);
	}

	@Override
	public void updateMember(MemberVO memberVO) throws Exception {
		//선택한 회원의 정보를 수정하기
		memberDAO.updateMember(memberVO);
	}
	
}
