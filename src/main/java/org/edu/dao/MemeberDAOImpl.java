package org.edu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.edu.vo.MemberVO;
import org.edu.vo.PageVO;
import org.springframework.stereotype.Repository;

@Repository
public class MemeberDAOImpl implements IF_MemberDAO {
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception {
		// mapper.xml에 접근하는 방법
		//매개변수를 1개로 만들어서 값을 2개로  집어넣는 방식: 해시데이터를 만들어서 구현.
		/*
		 * Map<String, Object> paramMap = new HashMap<String,Object>();
		 * paramMap.put("search_type", search_type); paramMap.put("search_keyword",
		 * search_keyword);
		 */
		return sqlSession.selectList("memberMapper.selectMember", pageVO);
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

	@Override
	public int countMember(PageVO pageVO) throws Exception {
		// mapper.xml에 접근하는 방법
		return sqlSession.selectOne("memberMapper.countMember", pageVO);
	}

	
	
	
}
