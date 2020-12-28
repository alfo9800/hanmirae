package org.edu.service;

import java.util.List;

import javax.inject.Inject;

import org.edu.dao.IF_BoardDAO;
import org.edu.vo.BoardVO;
import org.edu.vo.PageVO;
import org.springframework.stereotype.Service;

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
		//DA클래스 사용코드
		return boardDAO.countBoard(pageVO);
	}
	
}
