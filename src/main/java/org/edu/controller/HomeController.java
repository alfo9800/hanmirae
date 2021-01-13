package org.edu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.edu.service.IF_BoardService;
import org.edu.vo.BoardVO;
import org.edu.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 * '해석' 앱을 위한 홈페이지 요청(request)을 처리한다.(아래)
 * http://localhost:8080/ 요청을 받았을 때, 변수를 만들어서 크롬 화면 또는 콘솔에 뿌려준다
 */
@Controller
public class HomeController {
	
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Inject
	private IF_BoardService boardService;
	
	//전체영역 홈페이지에서 스프링 진입전 발생하는 에러 페이지 처리
	@RequestMapping(value="/home/error/404",method=RequestMethod.GET)
	public String error404() throws Exception {
		return "home/error/404";
	}
	
	//사용자 홈페이지 게시판 상세보기 매핑
	@RequestMapping(value="/home/board/board_view",method=RequestMethod.GET)
	public String board_view() throws Exception {
		
		return "home/board/board_view";
	}
	
	//사용자 홈페이지 게시판 쓰기 매핑(POST) -boardVO쓰는 이유: 오버로드(매개변수의 개수 또는 타입이 틀린)메서드 이용
	//순서: jsp에서 board_write메서드 호출 -> 호출할 때 폼의 필드 값을 컨트롤러로 보냄 -> 컨트롤러에서 받을 때 사용하는 매개변수(=boardVO) -> 받은 boardVO를 DAO에서 받아서 DB테이블에 쿼리로 입력.
	@RequestMapping(value="/home/board/board_write",method=RequestMethod.POST)
	public String board_write(BoardVO boardVO) throws Exception {
		//위에서 받은 boardVO를 서비스로 보내기
		
		return "redirect:/home/board/board_view";
	}
	//사용자 홈페이지 게시판 쓰기 매핑(GET)
	@RequestMapping(value="/home/board/board_write",method=RequestMethod.GET)
	public String board_write() throws Exception {
		
		return "home/board/board_write";
	}
	
	//사용자 홈페이지 게시판 리스트 매핑
	@RequestMapping(value="/home/board/board_list",method=RequestMethod.GET)
	public String board_list(@ModelAttribute("pageVO") PageVO pageVO, Model model) throws Exception {
		//페이징 처리 추가
		if(pageVO.getPage() == null) {
			pageVO.setPage(1);
		}
		pageVO.setPerPageNum(5); //페이지 하단 페이징 번호 갯수
		pageVO.setQueryPerPageNum(10); //1페이지당 보여줄 게시물 갯수
		int totalCount = boardService.countBoard(pageVO); //페이징의 게시물 전체개수 구하기
		pageVO.setTotalCount(totalCount);
		
		List<BoardVO> board_list = boardService.selectBoard(pageVO);
		model.addAttribute("board_list", board_list);
		return "home/board/board_list";
	}
	
	//사용자 홈페이지 회원 마이페이지 접근 매핑
	@RequestMapping(value="/member/mypage",method=RequestMethod.GET)
	public String mypage() throws Exception {
		
		return "home/member/mypage";
	}
	
	//사용자 홈페이지 회원가입 접근 매핑	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join() throws Exception {
		
		return "home/join";
	}
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home() throws Exception {
		
		return "home/home";
	}
		
	
}
