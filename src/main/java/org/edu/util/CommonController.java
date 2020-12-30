package org.edu.util;

import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.edu.service.IF_MemeberService;
import org.edu.vo.MemberVO;
import org.springframework.stereotype.Controller;
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
	
	//첨부파일의 확장자를 비교해서 이미지인지,엑셀,한글과 같은 일반파일인지 확인하는 List객체변수
	//변수생성 후 바로 리스트3개 입력처리.
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
	//파일 업로드-xml에서 지정한 폴더에 실제파일 저장: 메서드구현
	public String[] fileUpload(MultipartFile file) {
		String realFileName = file.getOriginalFilename(); //jsp에서 전송한 파일명
		//폴더에 저장할 PK(프라이머리 키)용 파일명 만들기
		UUID uid = UUID.randomUUID(); //unique ID 생성 (여러개 중의 하나)
		
		return null;
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
}
