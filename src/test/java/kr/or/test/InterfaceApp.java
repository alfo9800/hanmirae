package kr.or.test;

public class InterfaceApp {

	public static void main(String[] args) {
		// 자바프로그램은 항상 main() 진입점에서 실행하는 것. 단, 웹 프로그램은 해당 안됌.
		AllCalc allCalc = new AllCalc();// 실행 오브젝트변수 allCalc 생성
		allCalc.add(5, 5);
		allCalc.sub(5, 5);
		allCalc.mul(5, 5);
		allCalc.div(5, 5);
	}

}

//인터페이스 실습(아래) 명세표-메서드명만 있고, 구현내용이 없는 구조
interface CalcAddSub {
	void add(double dx, double dy);
	void sub(double dx, double dy);
}
interface CalcMulDiv extends CalcAddSub {
	void mul(double dx, double dy);
	void div(double dx, double dy);
}
class AllCalc implements CalcAddSub, CalcMulDiv { //implements 뜻은 구현하다 라는 의미. 뒤에 있는 클래스 명세표 기준으로 구현한다. 

	@Override
	public void mul(double dx, double dy) {
		// 구현내용
		System.out.println("곱하기 결과는" + dx*dy);
		
	}

	@Override
	public void div(double dx, double dy) {
		// 구현내용
		System.out.println("나누기 결과는" + dx/dy);
		
	}

	@Override
	public void add(double dx, double dy) {
		// 구현내용
		System.out.println("더하기 결과는" + (dx+dy));
		
	}

	@Override
	public void sub(double dx, double dy) {
		// 구현내용
		System.out.println("빼기 결과는" + (dx-dy));
		
	}

		
}

