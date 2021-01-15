package org.edu.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import org.edu.service.IF_BoardService;
import org.edu.util.CommonController;
import org.edu.util.SecurityCode;
import org.edu.vo.AttachVO;
import org.edu.vo.BoardVO;
import org.edu.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@Inject
	private SecurityCode securityCode;
	
	@Inject
	private CommonController commonController;
	
	//전체영역 홈페이지에서 스프링 진입전 발생하는 에러 페이지 처리
	@RequestMapping(value="/home/error/404",method=RequestMethod.GET)
	public String error404() throws Exception {
		return "home/error/404";
	}
	
	//사용자 홈페이지 게시판 상세보기 매핑
	@RequestMapping(value="/home/board/board_view",method=RequestMethod.GET)
	public String board_view(@RequestParam("bno") Integer bno, @ModelAttribute("pageVO") PageVO pageVO, Model model) throws Exception {
		BoardVO boardVO = boardService.readBoard(bno);
		//내용에 대한 시큐어코딩
		String xssData = securityCode.unscript(boardVO.getContent());
		boardVO.setContent(xssData); //악성코드 제거한 결과를 다시 set 지정.
				
		//첨부파일 데이터 jsp 뷰 단으로 보내기
		List<AttachVO> files = boardService.readAttach(bno);
		String[] save_file_names = new String[files.size()];
		String[] real_file_names = new String[files.size()];
		int cnt = 0;
		for(AttachVO file_name:files) { //위에 files데이터에서 값을 뽑아 오는 로직
			save_file_names[cnt] = file_name.getSave_file_name();
			real_file_names[cnt] = file_name.getReal_file_name();
			cnt = cnt +1;
		}
		
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		//===============================================
		model.addAttribute("boardVO", boardVO);
		//model.addAttribute("pageVO", pageVO); //위 @modelAttribute로 대체. jps로 오브젝트를 전송
		
		//업로드 한 내용이 이미지인지 일반문서와 구분하는 역할을 jsp로 보냄.(아래)
		model.addAttribute("checkImgArray", commonController.getCheckImgArray());
		return "home/board/board_view";
	}
	
	@RequestMapping(value="/home/board/board_update",method=RequestMethod.GET)
	public String board_update(Model model, @ModelAttribute("pageVO") PageVO pageVO, @RequestParam("bno") Integer bno) throws Exception {
		BoardVO boardVO = boardService.readBoard(bno);
		//콘텐츠 내용 시큐어 코딩 처리
		String xssData = securityCode.unscript(boardVO.getContent());
		boardVO.setContent(xssData);
		//첨부파일처리는 다음주에...
		model.addAttribute("boardVO", boardVO);

		return "home/board/board_update";
	}
	
	//사용자 홈페이지 게시판 쓰기 매핑(POST) -boardVO쓰는 이유: 오버로드(매개변수의 개수 또는 타입이 틀린)메서드 이용
	//순서: jsp에서 board_write메서드 호출 -> 호출할 때 폼의 필드 값을 컨트롤러로 보냄 -> 컨트롤러에서 받을 때 사용하는 매개변수(=boardVO) -> 받은 boardVO를 DAO에서 받아서 DB테이블에 쿼리로 입력.
	@RequestMapping(value="/home/board/board_write",method=RequestMethod.POST)
	public String board_write(RedirectAttributes rdat, @RequestParam("file") MultipartFile[] files, BoardVO boardVO) throws Exception {
		//위에서 받은 boardVO를 서비스로 보내기
		String[] save_file_names = new String[files.length];
		String[] real_file_names = new String[files.length];
		int index = 0;
		for(MultipartFile file:files) {
			if(file.getOriginalFilename() != "") {
			save_file_names[index] = commonController.fileUpload(file); //UUID 고유값
			real_file_names[index] = commonController.fileUpload(file); //진짜 파일명
			}
			index = index +1;
		}
		
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		boardService.insertBoard(boardVO); //실제 DB에 인서트
		rdat.addFlashAttribute("msg", "저장"); //header.jsp에서 출력한다.
		
		return "redirect:/home/board/board_list";
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
