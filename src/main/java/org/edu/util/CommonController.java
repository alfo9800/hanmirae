package org.edu.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.edu.dao.IF_BoardDAO;
import org.edu.service.IF_MemeberService;
import org.edu.vo.BoardVO;
import org.edu.vo.MemberVO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


/**
 * CommonController 공통사용(Admin, Home)
 * @author 한미래
 *
 */
@Controller
public class CommonController {
	
	@Inject
	IF_MemeberService memberService;
	
	@Inject
	IF_BoardDAO boardDAO; //첨부파일을 개별 삭제하기 위해서 주입함.
	
	/**
	 * 첨부파일의 확장자를 비교해서 이미지인지,엑셀,한글과 같은 일반파일인지 확인하는 List객체변수
	 * 사용용도1: 게시물상세보기 첨부파일이 이미지면, 미리보기 이미지가 보이도록. 이미지가 아니면, 다운로드 링크만 보이도록.
	 * 사용용도2: 메인페이지 최근 갤러리 게시물에서 첨부파일이 있으면, 미리보기 이미지가 나오게 되는데,
	  			만약 첨부파일 이미지가 아니면, 대체이미지를 보여주기 위해 확장자 체크를 함.
	 * 변수생성 후 바로 리스트3개 입력처리.
	*/
	
	@SuppressWarnings("serial")
	private ArrayList<String> checkImgArray = new ArrayList<String>() {
		{
			add("gif");
			add("jpg");
			add("jpeg");
			add("png");
			add("bmp");
		}
	};
	
	public ArrayList<String> getCheckImgArray() {
		return checkImgArray;
	}

	public void setCheckImgArray(ArrayList<String> checkImgArray) {
		this.checkImgArray = checkImgArray;
	}
	
	//첨부파일 업로드 할 경로를 변수 값으로 가져옴. servlet-context.xml에 있는 내용 중 id값이 uploadPath를 가져와서 사용.
	@Resource(name="uploadPath")
	private String uploadPath; //위 uploadPath영역의 값을 uploadPath변수에 저장.
		
	public String getUploadPath() { //컨트롤러에서 파일 삭제 시 호출로 사용
		return uploadPath;
	}
	
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	
	//파일 다운로드 구현한 메서드
	@RequestMapping(value="download",method=RequestMethod.GET)
	@ResponseBody //이 메서드는 페이지 이동처리 아님. RestAPI처럼 현재페이지에 구현결과를 전송 받음.
	public FileSystemResource download(
				@RequestParam("save_file_name") String save_file_name,
				@RequestParam("real_file_name") String real_file_name,
				HttpServletResponse response
			) throws Exception { //파일시스템리소스로 현재페이지에서 반환 받는다.
		File file = new File(uploadPath + "/" + save_file_name); //다운받을 경로 지정.
		
		response.setContentType("application/download; utf-8"); //파일내용 중 한글이 깨지는 것을 방지
		real_file_name = URLEncoder.encode(real_file_name, "UTF-8").replaceAll("\\+", "%20"); //위에 URLEncoder는 파일명이 한글(일,중 등)일 때 깨지는 것을 방지 //replaceAll(정규식:패턴, 받을 값)
		response.setHeader("Content-Disposition", "attachment; filename=" + real_file_name);
		
		return new FileSystemResource(file); //실제 다운로드 시작
	}
	
	//파일 업로드-xml에서 지정한 폴더에 실제파일 저장을 구현한 메서드
	public String fileUpload(MultipartFile file) throws IOException {
			String realFileName = file.getOriginalFilename(); //jsp에서 전송한 파일명 ->확장자를 구하려고 사용함.
		
			//폴더에 저장할 PK(프라이머리 키)용 파일명 만들기
			UUID uid = UUID.randomUUID(); //unique ID 생성 (여러개 중의 하나/고유 아이디): 폴더에 저장할 파일명으로 사용.
			
			//String saveFileName = uid.toString() + "." + realFileName.split("\\.")[1];//문제발생 아래코드 대체
			String saveFileName = uid.toString() + "." + StringUtils.getFilenameExtension(realFileName);
			//split("정규표현식");(Reular Expression):---해석---realFileName을 .(점)으로 분할해서 배열변수로 만드는 매서드
			//ex) abc.jpg -> realFileName[0]=abc, realFileName[1]=jpg 으로 결과가 나옴.		
		
			//String[] files = new String[] {saveFileName};//saveFileName 스트링형을 배열변수 files로 형 변환. =>저장할 파일명
			byte[] fileData = file.getBytes(); //jsp폼에서 전송된 파일이 fileData변수(메모리)에 저장됨. =>저장할 파일메모리
			
			File target = new File(uploadPath, saveFileName); //파일저장 하기 로 전 설정저장.
			FileCopyUtils.copy(fileData, target);//실제로 target폴더에 파일로 저장되는 메서드. = 즉,업로드 종료.
		return saveFileName; //copy로 업로드 이후에 저장된 real_file_name 스트링문자열값 1개를 반환.
	}


	//REST-API컨트롤러 사용 
	//아래는 Rest-API백엔드 단. //Ajax(jsp)부분은 Rest-API의 프로트엔드 단.
	@RequestMapping(value="/id_check",method=RequestMethod.GET)
	
	//@ResponseBody애노테이션은 json텍스트데이터를 반환하는 역할.
	@ResponseBody
	public String id_check(@RequestParam("user_id") String user_id) {
		String result = "0"; //(중복값X)아이디 중복값을 체크하는 변수기본값은 중복값 없음.
		//REST-API서비스에서는 스프링을 통해서 Ajax로 에러메세지를 받을 수 없기 때문에 여기서 에러 처리를 해야함.
		try {
			MemberVO memberVO = memberService.readMember(user_id);
			if(memberVO != null) {
				result = "1"; //(중복값O)아이디 중복값이 있을 경우
			}
		} catch (Exception e) {
			//위 readMember메서드가 에러발생시
			result = e.toString(); 
		}
		return result; //에러 -1 //성공 0 ,1
	}
	
	@Transactional
	@RequestMapping(value="/file_delete",method=RequestMethod.POST)
	@ResponseBody //메서드 응답을 내용만 반환 받겠다고 명시-> RestAPI에서는 직접 try,catch사용. Spring(throws Exception)으로 보내면 마음대로 조절 어려움.
	public String file_delete(@RequestParam("save_file_name") String save_file_name) {
		String result = "";
		try {
			boardDAO.deleteAttach(save_file_name); //DB에서만 지워짐.			
			//실제 폴더에서 파일도 같이 삭제 구현
			File target = new File(uploadPath, save_file_name);
			if(target.exists()) {
				target.delete();//폴더에서 기존첨부파일 지우기
			}
			result = "success";
		} catch (Exception e) {	
			result = "fail : " + e.toString();
		}
		return result;
	}
	
}
