package kr.or.test;

public class LoopApp {

	public static void main(String[] args) {
		// 반복문 중에 for문
		// 메서드에서 사용하는 변수 (아래2줄)
		int[] numbers = {11,21,31,41,51,61,71,81,91,101};//배열변수 선언
		int cnt;//반복횟수 카운트(세는)하는 역할
		//(아래 해석)for문(반복의 시작값정의; 반복의 종료값정의; 반복할 때 증가하는 값) {반복할 때 구현내용}
		for(cnt=0;cnt<10;cnt++) {//cnt++ => cnt=cnt+1; 의미는 1씩 증가하는 의미
			//System.out.println("현재 카운터는" + numbers[cnt]);
			//(위 해석)10번 반복을 하는데, numbers[0], numbers[1], ..... numbers[9] 출력
			//println함수는 한 번 출력 후 newline 엔터치는 역할.
		}
		
		//향상된 for문, 스프링jsp에서 제일많이 사용하게 될 구문.(아래)
		String[] names = {" 사용자1"," 사용자2"," 사용자3"," 사용자4"," 사용자5"};
		//자바에서 String형 큰따옴표는 문자열데이터 감싸줄 때, char형 작은따옴표는 1개 문자데이터를 감싸줄 때  ex)String"한미래". char'한'.
		//char형을 쓰는 경우: 임베디드장비와 자바프로그램 통신 시 사용
		//임베디드(아두이노) 프로그램에서 char 사용하는 이유: (프로그램 용량이 작아)메모리를 효율적으로 사용하기 위해
		//배열타입[]=1차원배열=가로선(1row. 하나의 라인.)
		//향상된 for문에서는 시작값,종료값,증가값이 필요 없음.
		for(String username:names) {
			//System.out.println("등록된 사용자 이름은" + username);//names라는 변수선언에서 'username'을 뽑아 출력한다.
		}
		
		//향상된 for문, 회원정보를 jsp로 출력하기. 현장과 비슷하게 구현
		String[][] members = {
				{"admin","관리자","admin@aka.com","true","2020-12-04","ROLE_ADMIN"},
				{"user","사용자","user@aka.com","false","2020-12-04","ROLE_USER"}
		};
		//배열타입[][]=2차원배열=표 배열(1row, 2row. 리스트가 있는 줄. 여러개 라인)
		System.out.println("=====회원리스트 입니다.=====");
		System.out.println("user_id | user_name | email | use | regdate | levels");
		for(String[] member:members) {
			System.out.println(member[0]+" | "+member[1]+" | "+member[2]+" | "+member[3]+" | "+member[4]+" | "+member[5]);
		}
	}

}
