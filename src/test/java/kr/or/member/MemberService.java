package kr.or.member;

import kr.or.member.MemberVO;

public class MemberService {
	public void printMember(MemberVO[] members) {// members는 레코드가 여러개=여러줄
		// 서비스 구현 메서드 내용-향상된 for반복문 사용
		int cnt = 0;
		for (MemberVO member : members) {// members 여러 레코드 중 1개의 레코드를 member오브젝트 객체로 대입
			cnt = cnt + 1; //위에 int형이 0부터 시작이기에 '+1'을 해야 출력 시 1부터 보여진다.
			System.out.println(cnt + "번째 레코드는 " + member.toString());
		}
		//비지니스 로직에서 처리하는 내용은- 데이터를 가공한다는 의미는
		//위처럼 데이터 select가져오기. insert입력하기. update수정하기
		//delete삭제하기 => CRUD를 구현하는 내용
	}
}
