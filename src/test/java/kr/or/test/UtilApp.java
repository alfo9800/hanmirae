package kr.or.test;

public class UtilApp {

	public static void main(String[] args) {
		// 클래스의 실행 진입점
		// java.lang Math클래스를 사용해서 랜덤하게 숫자를 뽑아 보자.(아래)
		// 제비뽑기로 생각하면, 꽝이 0
		System.out.println("1부터 30까지 랜덤한 숫자 출력하기 = "
				+ (int)(Math.random() *30)//+1을 하면 1부터, +1없으면 0부터 이게 실핼 됨.(즉, 0(꽝)이 나오지 않게 하려면 30 +1 하면 됨.)
				//결과가 float(실수)로 나오기 때문에, 정수형(int)으로 형변환 해서 출력
				//아이디 자동생성기 만들때, math클래스의 랜덤메서드 사용함.
				);
		

	}

}
