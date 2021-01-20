package org.edu.service;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

import org.edu.dao.IF_MemberDAO;
import org.edu.vo.MemberVO;
import org.edu.vo.PageVO;
import org.springframework.stereotype.Service;

/**
 * MemberServiceImpl는 DAO 메서드 호출을 구현
 * @author 한미래
 *
 */
@Service
public class MemberServiceImpl implements IF_MemberService  {

	@Inject
	IF_MemberDAO memberDAO;//IF_MemberDAO 주입받아서 memberDAO 오브젝트 생성.
	
	@Override
	public List<MemberVO> selectMember(PageVO pageVO) throws Exception {
		//회원정보 서비스조회 DAO클래스의 호출
		return memberDAO.selectMember(pageVO);
	}

	@Override
	public int countMember(PageVO pageVO) throws Exception {
		//검색된 회원의 전체 명수 구하기
		return memberDAO.countMember(pageVO);
	}

	@Override
	public MemberVO readMember(String user_id) throws Exception {
		//선택한 회원의 상세 정보 구하기
		return memberDAO.readMember(user_id);
	}

	@Override
	public void deleteMember(String user_id) throws Exception {
		//선택한 회원의 정보를 삭제하기
		memberDAO.deleteMember(user_id);
	}

	@Override
	public void updateMember(MemberVO memberVO) throws Exception {
		//선택한 회원의 정보를 수정하기
		memberDAO.updateMember(memberVO);
	}

	@Override
	public void insertMember(MemberVO memberVO) throws Exception {
		//jsp폼에서 입력한 회원정보 등록하기
		memberDAO.insertMember(memberVO);
		
	}

	@Override
	public void herokuJobMethod() throws Exception {
		//월~금 오전 8시부터 오후11시 까지(미국 시간으로 23시부터 14시까지) 헤로쿠앱에 20분 간격으로 URL 접근
		String urlStr = "https://hanmirae.herokuapp.com"; //본인 앱 주소
		URL url = new URL(urlStr);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection(); //오픈 접속시작
		urlConnection.setUseCaches(false); //기존접속된 정보가 있든지 없든지 무조건 접속
		urlConnection.setReadTimeout(10000); //접속대기시간을 10초로 설정. 헤로쿠는 20초이상 이어야만 활성화됨.
		
		//개발자 확인 코드
		if(urlConnection !=null && urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
			System.out.println("헤로쿠 앱이 활성화 상태입니다.");
		}else {
			System.out.println("헤로쿠 앱이 비활성화 상태입니다.");
		}
	}
	
}
