package org.edu.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.edu.vo.BoardVO;
import org.edu.vo.PageVO;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements IF_BoardDAO {

	@Inject //sqlSession템플릿을 주입 받아서 변수로 지정
	private SqlSession sqlSession;
		
	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		//mapper쿼리 지정 (=mapper.xml에 접근하는 방법)		
		return sqlSession.selectList("boardMapper.selectBoard", pageVO);
	} 	//sqlSession템플릿(select,update,delete같은 메서드가 포함됨)
		//sqlSession템플릿이란? mapper쿼리(위 쿼리 포함+insert등)를 실행 할 때, 개발자가 DB커넥션, 디스커넥션을 생각할 필요없이 사용가능한 메서드 집합을 구성해 놓은 것.

	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		//sqlSession템플릿 사용해서 게시물개수 구하기. &mapper 연결 
		return sqlSession.selectOne("boardMapper.countBoard", pageVO);
	}
	
}
