package kr.or.test;


/**
 * 클래스자료형 사용에 대해
 * @author 한미래
 *
 */
class MemberVO {
	//멤버변수 만들기(아래)
	private String name;
	private int age;
	private String phoneNum;
	
	//멤버변수는 보안 때문에 private으로 만들었는데, get,set은 public으로 생성됨.(class안에서 읽어들이기 편하게)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	@Override
	public String toString() {
		return "디버그용 MemberVO [name=" + name + ", age=" + age + ", phoneNum=" + phoneNum + "]";
	}
	
}
class MemberService {//회원출력에 관련된 메서드를 모아 놓음.(아래)
	public void printMember(MemberVO[] members) {//members는 레코드가 여러개=여러줄
		//서비스 구현 메서드 내용-향상된 for반복문 사용
		int cnt=0;
		for(MemberVO member:members) {//members 여러 레코드 중 1개의 레코드를 member 오브젝트 객체로 
			cnt = cnt + 1;
			System.out.println(cnt+ "번째 레코드는 " + member.toString());
			
		}
	}
}

public class Step2 {

	public static void main(String[] args) {
		// 실행 메서드
		MemberVO memberVO = new MemberVO();
		memberVO.setName("은지원");
		memberVO.setAge(43);
		memberVO.setPhoneNum("010-1111-2222");
		MemberVO memberVO2 = new MemberVO();
		memberVO2.setName("장동윤");
		memberVO2.setAge(29);
		memberVO2.setPhoneNum("010-3333-4444");
		MemberVO memberVO3 = new MemberVO();
		memberVO3.setName("김남길");
		memberVO3.setAge(41);
		memberVO3.setPhoneNum("010-2222-8888");
		//위 각각 3개의 레코드를 1군데 담을 배열 클래스오브젝트를 하나 생성
		MemberVO[] members = new MemberVO[3];
		members[0] = memberVO;
		members[1] = memberVO2;
		members[2] = memberVO3;
		
		//전에 사용했던 출력보다는 개선된 방식의 출력 (아래) - MemberService 서비스 전용클래스 만들어서 처리
		MemberService memberService = new MemberService();//자바 오브젝트객체 생성하는 방법
		memberService.printMember(members);//서비스클래스의 메소드 실행
	}

}
