package org.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.edu.vo.MemberVO;
import org.springframework.stereotype.Repository;

@Repository
public class MemeberDAOImpl implements IF_MemberDAO {
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<MemberVO> selectMember() throws Exception {
		// mapper.xml에 접근하는 방법
		//오브젝트의 메서드를 실행할 때, '.'을 눌렀을 때 입력힌트가 나오지 않으면, ctrl+space 키를 눌러 메서드 나오게 하도록 함.
		return sqlSession.selectList("memberMapper.selectMember");
		//sqlSession템플릿의 selectList메서드를 실행하면, memberMapper.SelectMember를 반환하여 나온다.
	}

}
