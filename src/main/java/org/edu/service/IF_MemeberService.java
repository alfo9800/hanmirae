package org.edu.service;

import java.util.List;

import org.edu.vo.MemberVO;

/**
 * IF_MemberService클래스는 DAO를 호출하는 역할.
 * @author 한미래
 *
 */
public interface IF_MemeberService {
	public List<MemberVO> selectMember(String search_type,String search_keyword) throws Exception;
	
}
