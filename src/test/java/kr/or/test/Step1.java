package kr.or.test;
/**
 * 내부변수와 배열 사용에 대해서
 * @author 한미래
 *
 */
public class Step1 {
	//전역변수(멤버변수)는 클래스 전체에 미치는 변수 = 클래스안에서 사용하는 변수
	//내부변수는 메서드 내에서만 영향을 미치는 변수=메서드(함수)안에서만 사용하는 변수
	public static void main(String[] args) {
		//필드=메서드 내부변수<->전역변수(멤버) 만들기 (아래) 
		String name;//String 문자열만 담을 수 있음.
		int age;//int 숫자만 담을 수 있음.
		String phoneNum;
		//변수에 값을 대입연산자로 대입(아래)
		name="은지원";
		age=43;
		phoneNum="010-1234-5678";
		//위 내용을 출력하는 메서드 1개 사용(아래)
		printMember(name, age, phoneNum);
		name="한미래"; age=23; phoneNum="010-1223-3456";
		printMember(name, age, phoneNum);
		name="김태리"; age=29; phoneNum="010-9876-5432";
		printMember(name, age, phoneNum);
		
		//배열을 이용해서 간편하게 입출력 해보기(아래)
		String[] names = {"은지원","한미래","김태리"};
		int[] ages = {43,23,29};
		String[] phoneNums = {"010-1111-1111","010-3333-3333","010-5555-5555"};
		printMember(names,ages,phoneNums);//오버로드형 메서드---다형성의 대표사례
	}

	private static void printMember(String[] names, int[] ages, String[] phoneNums) {
		// member를 출력하는데 배열 값을 받아서 구현
		int dataLength = names.length;//이름 배열에 있는 사람의 수 구하기
		System.out.println("매개변수로 받은 names 의 사람의 명수는: " + names.length);
		//고전for문의 반복문 형식은 for(초기값;끝값=반복의조건cnt<1;증가값){구현내용}
		for(int cnt=0;cnt<dataLength;cnt++) {
			System.out.println("cnt변수의 변화는 = " + cnt);
			System.out.println("배열의 이름은: " +names[cnt]+" | 나이는: "+ages[cnt]+" | 연락처는: "+phoneNums[cnt]);
			
		}
		
		
	}

	private static void printMember(String name, int age, String phoneNum) {
		// member를 출력하는 프린트 메서드 구현내용
		System.out.println("회원의 이름은: " +name+" | 나이는: "+age+" | 연락처는: "+phoneNum);
		
	}

}
