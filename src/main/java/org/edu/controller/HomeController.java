package org.edu.controller;

import java.io.File;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.edu.dao.IF_BoardDAO;
import org.edu.service.IF_BoardService;
import org.edu.service.IF_MemberService;
import org.edu.util.CommonController;
import org.edu.util.SecurityCode;
import org.edu.vo.AttachVO;
import org.edu.vo.BoardVO;
import org.edu.vo.MemberVO;
import org.edu.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private IF_MemberService memberService;
	
	@Inject
	private IF_BoardDAO boardDAO;
	
	@Inject
	private SecurityCode securityCode;
	
	@Inject
	private CommonController commonController;
	
	//전체영역 홈페이지에서 스프링 진입전 발생하는 에러 페이지 처리
	@RequestMapping(value="/home/error/404",method=RequestMethod.GET)
	public String error404() throws Exception {
		return "home/error/404";
	}
	
	//사용자 홈페이지 게시판 삭제 매핑
	@RequestMapping(value="/home/board/board_delete",method=RequestMethod.POST)
	public String board_delete(RedirectAttributes rdat, @RequestParam("bno") Integer bno, @RequestParam("page") Integer page) throws Exception {
		//부모게시판에 첨부파일 존재 시, 첨부파일 삭제 후 게시물  삭제
		List<AttachVO> delFiles = boardService.readAttach(bno);
		if(!delFiles.isEmpty()) {
			for(AttachVO file_name:delFiles) {
				File target = new File(commonController.getUploadPath(),file_name.getSave_file_name());
				if(target.exists()) {
					target.delete(); //실제업로드 된 파일 지우기
				}
			}
		}
		//DB에서 부모게시판에 댓글 존재 시, 댓글 삭제후 게시물 삭제
		boardService.deleteBoard(bno);
		
		rdat.addFlashAttribute("msg", "삭제"); //msg변수값은 URL에 표시가 나오지 않게 숨겨서 board_list로 보냄.
		return "redirect:/home/board/board_list?page="+page; //쿼리스트링 변수는 URL에 표시가 됨.
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
	
	@RequestMapping(value="/home/board/board_update",method=RequestMethod.POST)
	public String board_update(RedirectAttributes rdat, @RequestParam("file") MultipartFile[] files, BoardVO boardVO, PageVO pageVO) throws Exception {		
		//첨부파일 업로드
		List<AttachVO> delFiles = boardService.readAttach(boardVO.getBno());
		String[] save_file_names = new String[files.length];
		String[] real_file_names = new String[files.length];
		int index = 0;
		for(MultipartFile file:files) { //여기서 file이 신규 파일 업로드 할 것을 뜻 함.
			if(file.getOriginalFilename() != "") {			
				
				int sun = 0;
				for(AttachVO file_name:delFiles) { //실제 폴더에서 기존 첨부파일 삭제처리
					if(index == sun) {
						File target = new File(commonController.getUploadPath(),file_name.getSave_file_name()); //삭제할 파일 경로 지정
						if(target.exists()) {
							target.delete(); //기존 첨부파일 폴더에서 지우기
							boardDAO.deleteAttach(file_name.getSave_file_name()); //DB에서 기존파일 지우기
						}
					}
					sun = sun +1;
				}
				//신규파일 폴더에 업로드 처리
				save_file_names[index] = commonController.fileUpload(file); //신규파일 폴더에 업로드
				real_file_names[index] = file.getOriginalFilename(); //신규파일 저장.
			}else {
				save_file_names[index] = null; //신규파일 폴더에 업로드
				real_file_names[index] = null; //신규파일명 저장
			}
			index = index +1;
		}
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
		
		//시큐어코딩 추가
		String xssData= boardVO.getContent();
		boardVO.setContent(securityCode.unscript(xssData));			
		
		boardService.updateBoard(boardVO); //DB에 신규파일 저장기능 호출
		//게시판테이블 업데이트 + 첨부파일테이블 업데이트
		rdat.addFlashAttribute("msg", "수정"); //header.jsp에 상단을 통해 나타남.
		
		return "redirect:/home/board/board_view?bno="+boardVO.getBno()+"&page="+pageVO.getPage();
	}
	
	@RequestMapping(value="/home/board/board_update",method=RequestMethod.GET)
	public String board_update(Model model, @ModelAttribute("pageVO") PageVO pageVO, @RequestParam("bno") Integer bno) throws Exception {
		BoardVO boardVO = boardService.readBoard(bno);		
		//첨부파일처리
		List<AttachVO> files = boardService.readAttach(bno);
			//아래는 List<AttachVO>의 세로배치를 가로배치로 변경할 때 필요
		String[] save_file_names = new String[files.size()];
		String[] real_file_names = new String[files.size()];
		int cnt=0;
			//세로데이터를 가로데이터로 변경 로직
		for(AttachVO file_name:files) {
			save_file_names[cnt] = file_name.getSave_file_name();
			real_file_names[cnt] = file_name.getReal_file_name();
			cnt = cnt+1;
		}
		//배열형 출력
		boardVO.setSave_file_names(save_file_names);
		boardVO.setReal_file_names(real_file_names);
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
		//보안코딩으로 script 제거
		String xssData= boardVO.getContent();
		boardVO.setContent(securityCode.unscript(xssData));
		
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
	
	//사용자 홈페이지 회원 마이페이지 수정 매핑
	@RequestMapping(value="/member/mypage_update",method=RequestMethod.POST)
	public String mypage_update(HttpServletRequest request,MemberVO memberVO, RedirectAttributes rdat) throws Exception {
		if(memberVO.getUser_pw() != "") {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String user_pw_encode = passwordEncoder.encode(memberVO.getUser_pw());
			memberVO.setUser_pw(user_pw_encode);
		}
		
		memberService.updateMember(memberVO);	
		HttpSession session = request.getSession();
		session.setAttribute("session_username", memberVO.getUser_name()); //기본 세션 덮어쓰기.
		
		rdat.addFlashAttribute("msg", "회원수정"); //model로 값을 보내지 못하는 이유는 return값이 redirect이기 때문에.
		return "redirect:/member/mypage";
	}
	
	//사용자 홈페이지 회원 마이페이지 접근 매핑
	@RequestMapping(value="/member/mypage",method=RequestMethod.GET)
	public String mypage(HttpServletRequest request,Model model) throws Exception {
		//마이페이지는 로그인 상태만 접근 가능하기 때문에, 로그인 세션변수 중 로그인아이디 변수 session_userid
		//로그인 세션 변수 가져오기 (=> HttpServeletRequst request로 가져옴)
		HttpSession session = request.getSession();
		MemberVO memberVO = memberService.readMember((String) session.getAttribute("session_userid"));
		model.addAttribute("memberVO", memberVO);
		
		return "home/member/mypage";
	}
	
	//사용자 홈페이지 회원가입 접근 매핑	
	@RequestMapping(value="/join",method=RequestMethod.GET)
	public String join() throws Exception {
		
		return "home/join";
	}
	
	//사용자 홈페이지 루트(최상위) 접근 매핑
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home(Model model) throws Exception{
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);
		pageVO.setPerPageNum(5);//하단페이징
		pageVO.setQueryPerPageNum(5);
		List<BoardVO> board_list = boardService.selectBoard(pageVO);
		//System.out.println("디버그" + board_list);
		model.addAttribute("board_list", board_list);
		
		//첨부파일 1개만 model클래스를 이용해서 jsp로 보냅니다.
		String[] save_file_names = new String[board_list.size()];
		int cnt = 0;
		for(BoardVO boardVO:board_list) {//board_list변수에는 최대 5개의 레코드가 존재함.
			List<AttachVO> file_list = boardService.readAttach(boardVO.getBno());
			//System.out.println("디버그-file_list" + file_list);
			if(file_list.size() == 0) {//첨부파일이 없을떄
				save_file_names[cnt] = "";
				System.out.println("디버그-[" + cnt + "]" + save_file_names[cnt]);
				//continue;//컨티뉴 아래는 실행 하지 않고 건너띔
			} else {
				for(AttachVO file_name:file_list) {
					String save_file_name = file_name.getSave_file_name();
					String extName = FilenameUtils.getExtension(save_file_name);
					boolean imgCheck = commonController.getCheckImgArray().contains(extName.toLowerCase());
					if(imgCheck) {//첨부파일이 이미지일때
						save_file_names[cnt] = save_file_name;
						System.out.println("디버그[" + cnt + "]" + save_file_names[cnt]);
						break;//이중 반복문에서 현재 for문만 종료
					} else {//첨부파일이 엑셀,한글같은 파일일때
						save_file_names[cnt] = "";
						System.out.println("디버그[" + cnt + "]" + save_file_names[cnt]);
					}
				}
			}
			cnt = cnt + 1;
		}
		
		model.addAttribute("save_file_names", save_file_names);
		return "home/home";
	}
		
	
}
