package kr.or.test;

public class ThreadApp extends Thread {

	public void run() {
		System.out.println("스레드 시작시 자동실행됩니다.!");
	}
	public static void main(String[] args) {
		// 메인스레드 시작 진입점 main()
		(new ThreadApp()).start();//아래 두 줄과 이 곳 한 줄이 같은 의미.
		//ThreadApp threadApp = new ThreadApp();
		//threadApp.satrt();
	}

}
