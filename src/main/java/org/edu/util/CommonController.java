package org.edu.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.edu.service.IF_MemeberService;
import org.edu.vo.MemberVO;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
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
	
	/**
	 * 첨부파일의 확장자를 비교해서 이미지인지,엑셀,한글과 같은 일반파일인지 확인하는 List객체변수
	 * 사용용도1: 게시물상세보기 첨부파일이 이미지면, 미리보기 이미지가 보이도록. 이미지가 아니면, 다운로드 링크만 보이도록.
	 * 사용용도2: 메인페이지 최근 갤러리 게시물에서 첨부파일이 있으면, 미리보기 이미지가 나오게 되는데,
	  			만약 첨부파일 이미지가 아니면, 대체이미지를 보여주기 위해 확장자 체크를 함.
	 * 변수생성 후 바로 리스트3개 입력처리.
	*/
	@SuppressWarnings("serial")
	private ArrayList<String> extNameArray = new ArrayList<String>() {
		{
			add("gif");
			add("jpg");
			add("png");
		}
	};
	//첨부파일 업로드 할 경로를 변수 값으로 가져옴. servlet-context.xml에 있는 내용 중 id값이 uploadPath를 가져와서 사용.
	@Resource(name="uploadPath")
	private String uploadPath; //위 uploadPath영역의 값을 uploadPath변수에 저장.
		
	public String getUploadPath() {
		return uploadPath;
	}
	
	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	//파일 업로드-xml에서 지정한 폴더에 실제파일 저장을 구현한 메서드
	public String[] fileUpload(MultipartFile file) throws IOException {
			String realFileName = file.getOriginalFilename(); //jsp에서 전송한 파일명 ->확장자를 구하려고 사용함.
		
			//폴더에 저장할 PK(프라이머리 키)용 파일명 만들기
			UUID uid = UUID.randomUUID(); //unique ID 생성 (여러개 중의 하나/고유 아이디): 폴더에 저장할 파일명으로 사용.
			String saveFileName = uid.toString() + "." + realFileName.split("\\.")[1];
			//split("정규표현식");(Reular Expression):---해석---realFileName을 .(점)으로 분할해서 배열변수로 만드는 매서드
			//ex) abc.jpg -> realFileName[0]=abc, realFileName[1]=jpg 으로 결과가 나옴.		
		
			String[] files = new String[] {saveFileName};//saveFileName 스트링형을 배열변수 files로 형 변환. =>저장할 파일명
			byte[] fileData = file.getBytes(); //jsp폼에서 전송된 파일이 fileData변수(메모리)에 저장됨. =>저장할 파일메모리
			
			File target = new File(uploadPath, saveFileName); //파일저장 하기 로 전 설정저장.
			FileCopyUtils.copy(fileData, target);//실제로 target폴더에 파일로 저장되는 메서드. = 즉,업로드 종료.
		return files; //1개이상의 파일 업로드 시 저장된 파일명을 배열로 저장한 변수
	}//다중파일 업로드를 구현하고 싶으면, git의 칠판에 블로그 링크 참조.


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
}
