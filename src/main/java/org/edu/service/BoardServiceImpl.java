package org.edu.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.edu.dao.IF_BoardDAO;
import org.edu.dao.IF_ReplyDAO;
import org.edu.vo.BoardVO;
import org.edu.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service //명시적으로 어떤 클래스를 입력함. //그래야 스프링 빈으로 사용할 수 있음.
public class BoardServiceImpl implements IF_BoardService {

	@Inject //댓글DAO클래스 주입
	private IF_ReplyDAO replyDAO;
	
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
	public List<HashMap<String, Object>> readAttach(Integer bno) throws Exception {
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
				if(save_file_name != null) {
					real_file_name = real_file_names[index];
					boardDAO.insertAttach(save_file_name, real_file_name);
					}
				index = index +1;
		}
	}
	
	@Transactional
	@Override
	public void deleteBoard(Integer bno) throws Exception {
		//첨부파일 먼저 삭제 후 게시물 삭제
		boardDAO.deleteAttachAll(bno);
		//댓글도 모두 삭제
		replyDAO.deleteReplyAll(bno);
		//게시물 삭제 쿼리DAO 연결
		boardDAO.deleteBoard(bno);
	}

	@Override
	public void updateBoard(BoardVO boardVO) throws Exception {
		//게시물 수정 쿼리DAO 연결
		boardDAO.updateBoard(boardVO);
		
		//조건: 기존 첨부파일 DB를 삭제한 후
		
		//첨부파일 수정 쿼리DAO 연결(=insert)
		Integer bno = boardVO.getBno();
		String[] save_file_names = boardVO.getSave_file_names();
		String[] real_file_names = boardVO.getReal_file_names();
		//첨부파일 여러개 일 때의 상황
		int index = 0;
		String real_file_name = "";
			if(save_file_names == null) {return;} //배열첨부파일이 없으면 진행 빠져나감.
			for(String save_file_name: save_file_names) { //첨부파일 1개 일 때는 1번만 반복함.
				if(save_file_name != null) { //첨부파일배열에서 배열 값이 있는 경우만
					real_file_name = real_file_names[index];
					boardDAO.updateAttach(save_file_name, real_file_name, bno);
					index = index +1;
				}
			}
		}
	
}
