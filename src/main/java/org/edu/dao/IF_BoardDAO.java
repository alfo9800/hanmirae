package org.edu.dao;

import java.util.List;

import org.edu.vo.BoardVO;
import org.edu.vo.PageVO;

public interface IF_BoardDAO {
	public List<BoardVO> selectBoard(PageVO pageVO) throws Exception; //(List형) BoardVO를 가져다가 쓴다는 의미 //select(매개변수 PageVO형 pageVO를 쓰겠다는 의미
	public int countBoard(PageVO pageVO) throws Exception;
	//게시물 상세조회 시작
	public BoardVO readBoard(Integer bno) throws Exception;
	public List<String> readAttach(Integer bno) throws Exception;
	public void updateViewCount(Integer bno) throws Exception;
	//게시물 상세조회 끝
	
}
