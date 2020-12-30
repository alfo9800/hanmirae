package org.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.edu.dao.IF_BoardDAO;
import org.edu.vo.BoardVO;
import org.edu.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //명시적으로 어떤 클래스를 입력함. //그래야 스프링 빈으로 사용할 수 있음.
public class BoardServiceImpl implements IF_BoardService {

	@Inject //DAO클래스를 주입 받아 변수 생성
	private IF_BoardDAO boardDAO;
		
	@Override
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception {
		//DAO클래스 사용코드
		return boardDAO.selectBoard(pageVO);
	}

	@Override
	public int countBoard(PageVO pageVO) throws Exception {
		//DAO클래스 사용코드
		return boardDAO.countBoard(pageVO);
	}

	//아래 구현한 메서드 중 1번째 메서드: 조회 수 업데이트 처리 후 조회 에러발생 시 업데이트한 조회수를  원상복귀 시키는 작업이 트랜잭션 처리(나중에 작업 처리)
	@Transactional
	@Override
	public BoardVO readBoard(Integer bno) throws Exception {
		//bno번호에 해당하는 게시물조회 쿼리DAO 연결 + (updateViewCount)해당게시물 조회수 업데이트
		boardDAO.updateViewCount(bno); //구현한 메서드1 
		return boardDAO.readBoard(bno); //구현한 메서드2
	}

	@Override
	public List<String> readAttach(Integer bno) throws Exception {
		//bno번호에 해당하는 첨부파일조회 쿼리DAO 연결
		return boardDAO.readAttach(bno);
	}

	@Transactional
	@Override
	public void insertBoard(BoardVO boardVO) throws Exception {
		//게시물 등록 쿼리DAO 연결
		boardDAO.insertBoard(boardVO);
		
		//첨부파일 등록 쿼리DAO 연결
		String[] save_file_names = boardVO.getSave_file_names();
		String[] real_file_names = boardVO.getReal_file_names();
		
		//첨부파일 여러개 일 때의 상황
		int index = 0;
		String real_file_name = "";
			if(save_file_names == null) {return;}
			for(String save_file_name: save_file_names) { //첨부파일 1개 일 때는 1번만 반복함.
				real_file_name = real_file_names[index];
		boardDAO.insertAttach(save_file_name, real_file_name);
		index = index +1;
		}
	}

	@Override
	public void deleteBoard(Integer bno) throws Exception {
		//게시물 삭제 쿼리DAO 연결
		boardDAO.deleteBoard(bno);
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		//게시물 수정 쿼리DAO 연결
		boardDAO.updateBoard(boardVO);
	}
	
}
