package org.edu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		@Override
		public BoardVO readBoard(Integer bno) throws Exception {
			//게시물 상세보기 mapper쿼리 연결
			return sqlSession.selectOne("boardMapper.readBoard", bno);
		}
		@Override
		public List< HashMap<String,Object> > readAttach(Integer bno) throws Exception {
			//게시물에 딸린 첨부파일 보기 mapper쿼리 연결
			return sqlSession.selectList("boardMapper.readAttach", bno);
		}
		@Override
		public void updateViewCount(Integer bno) throws Exception {
			//게시물 상세보기 시 조회수 +1 업데이트 처리 mapper쿼리 연결
			sqlSession.update("boardMapper.updateViewCount", bno);
		}

		@Override
		public void insertBoard(BoardVO boardVO) throws Exception {
			//게시물 등록 mapper쿼리 연결
			sqlSession.insert("boardMapper.insertBoard", boardVO);
		}

		@Override
		public void deleteBoard(Integer bno) throws Exception {
			//게시물 삭제 mapper쿼리 연결
			sqlSession.delete("boardMapper.deleteBoard", bno);
		}

		@Override
		public void updateBoard(BoardVO boardVO) throws Exception {
			//게시물 수정 mapper쿼리 연결
			sqlSession.update("boardMapper.updateBoard", boardVO);
		}

		@Override
		public void insertAttach(String save_file_name, String real_file_name) throws Exception {
			//첨부파일 입력 mapper쿼리 연결
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("save_file_name", save_file_name);
			paramMap.put("real_file_name", real_file_name);
			sqlSession.insert("boardMapper.insertAttach", paramMap);
		}

		@Override
		public void deleteAttach(String save_file_name) throws Exception {
			//해당 게시물의 첨부파일 삭제 mapper쿼리 연결
			sqlSession.delete("boardMapper.deleteAttach", save_file_name);
		}

		@Override
		public void deleteAttachAll(Integer bno) throws Exception {
			//해당 게시물의 첨부파일 모두 삭제 mapper쿼리 연결
			sqlSession.delete("boardMapper.deleteAttachAll", bno);
		}

	
}
