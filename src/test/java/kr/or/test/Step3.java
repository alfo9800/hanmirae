package kr.or.test;

import java.util.Calendar;

/**
 * 외부Enum클래스를 이용해서 열거형 자료형 테스트
 * @author 한미래
 *
 */
public class Step3 {

	public static void main(String[] args) {
		// 메인 메서드 시작(=스레드 시작)
		Week today = null; //Week자료형용 today변수생성/선언 (= String today=null; 앞과 같은 의미라고 보면 됨.)
		MemberVO memberVO = new MemberVO(); //MemberVO자료형용 memberVO변수 선언
		
		//현재 날짜가 무슨 요일인지 한글로 확인 하는 프로그램(아래)
		Calendar calendar = Calendar.getInstance(); //오브젝트선언 /calender(객체 변수=인스턴스변수=오브젝트)
		//calendar오브젝트에서 현재 요일을 반환받기(1부터7까지 반환)
		int intWeek = calendar.get(calendar.DAY_OF_WEEK);
		//스위치조건문으로 1~7까지 반환값을 한글로 변환(아래)
		switch(intWeek) {//switch로 조건을 판단. 개발자가 if문으로 쓸 지,switch문으로 쓸 지 결정.
		//아래 요일은 Week.java을 만들어 놓아서 가져온 것.
		case 1:
			today = Week.일;break;//break는 case문장을 빠져나가는 명령
		case 2:
			today = Week.월;break;
		case 3:
			today = Week.화;break;
		case 4:
			today = Week.수;break;
		case 5:
			today = Week.목;break;
		case 6:
			today = Week.금;break;
		case 7:
			today = Week.토;break;
		}
		
		System.out.println("오늘은 " +today+ "요일 입니다.");
		
		//이번엔 if조건문 사용.
		if(today == Week.수) {
			System.out.println("수요일에는 영화를 봅니다.");
		} else {
			System.out.println("열심히 자바 공부를 합니다.");
		}
		
		//Calendar클래스는 달력에서 날짜, 본인 컴퓨터 날짜라서 사용자가 날짜를 수정하면 원하는 결과 출력되지 않음.
		//이 때 해결책: 서버시간을 가져와서 출력하면, 사용자가 날짜를 수정하지 못하게 됨.
	}

}
