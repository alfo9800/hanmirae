package org.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.edu.dao.IF_MemberDAO;
import org.edu.vo.MemberVO;
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
	public List<MemberVO> selectMember() throws Exception {
		//회원정보 서비스조회 DAO클래스의 호출
		return memberDAO.selectMember();
	}
	
}
