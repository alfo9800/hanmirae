package org.edu.service;

import javax.inject.Inject;

import org.edu.dao.IF_BoardTypeDAO;
import org.edu.vo.BoardTypeVO;
import org.springframework.stereotype.Service;

@Service
public class BoardTypeServiceImpl implements IF_BoardTypeService {

	@Inject
	private IF_BoardTypeDAO boardTypeDAO;
	
	@Override
	public BoardTypeVO view_board_type(String board_type) throws Exception {
		//게시판 타입 수정 기존 값 불러오는 DAO호출
		return boardTypeDAO.view_board_type(board_type);
	}

	@Override
	public void update_board_type(BoardTypeVO boardTypeVO) throws Exception {
		//게시판 타입 수정용 DAO호출
		boardTypeDAO.update_board_type(boardTypeVO);
	}

	@Override
	public void insert_board_type(BoardTypeVO boardTypeVO) throws Exception {
		//게시판 타입 신규등록 DAO호출
		boardTypeDAO.insert_board_type(boardTypeVO);
	}

	@Override
	public void delete_board_type(String board_type) throws Exception {
		//게시판 타입 삭제 DAO호출
		boardTypeDAO.delete_board_type(board_type);
	}

}
