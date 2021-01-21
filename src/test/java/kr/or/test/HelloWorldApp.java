package kr.or.test;

public class HelloWorldApp {

	public static void main(String[] args) {
		// 위 main()메서드는 자바프로그램의 실행 진입지점입니다.
		System.out.println("헬로우월드!");
		//plusFunc(3,4); //플러스 함수를 실행 할 때 매개변수 값을 줍니다.
		
		String testNull = null;
		String testEmpty = "";
		System.out.println("null출력:"+testNull+"empty출력:"+testEmpty);
		//System.out.println("isEmpty메서드null값 출력:"+testNull.isEmpty()); //에러 NullPointer
		System.out.println("isEmpty메서드공백값 출력:"+testEmpty.isEmpty());
	}
	
	
	
	//메서드의 매개변수에 대해서
	public static void plusFunc(int a, int b) {
		System.out.println("매개변수 a + b =" + (a+b) + "입니다.");
	}
}
