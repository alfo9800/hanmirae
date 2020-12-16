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
		return sqlSession.selectList("memberMapper.selectMember");
		//sqlSession템플릿의 selectList메서드를 실행하면, memberMapper.SelectMember를 반환하여 나온다.
	}

	@Override
	public void insertMember(MemberVO memberVO) throws Exception {
		// mapper.xml에 접근하는 방법
		sqlSession.insert("memberMapper.insertMember", memberVO);
		
	}

	@Override
	public void deleteMember(String user_id) throws Exception {
		// mapper.xml에 접근하는 방법
		sqlSession.delete("memberMapper.deleteMember", user_id);		
	}

	@Override
	public MemberVO readMember(String user_id) throws Exception {
		// mapper.xml에 접근하는 방법
		return sqlSession.selectOne("memberMapper.readMember", user_id); //.selectOne()은 sql세션템플릿 안 메서드이다.
	}

	@Override
	public void updateMember(MemberVO memberVO) throws Exception {
		// mapper.xml에 접근하는 방법
		sqlSession.update("memberMapper.updateMember", memberVO);//(쿼리 위치, 매개변수)
	}

	
	
	
}
