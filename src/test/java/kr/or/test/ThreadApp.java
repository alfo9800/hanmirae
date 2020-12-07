package kr.or.test;

public class ThreadApp extends Thread {

	public void run() {
		System.out.println("스레드 시작시 자동실행됩니다.!");
	}
	public static void main(String[] args) {
		// 메인스레드 시작 진입점 main()
		//(new ThreadApp()).start();//아래 두 줄과 이 곳 한 줄이 같은 의미.
		MyThread myThread1 = new MyThread("myThread1");
		MyThread myThread2 = new MyThread("myThread2");
		MyThread myThread3 = new MyThread("myThread3");
		myThread1.start();
		myThread2.start();
		myThread3.start();
		System.out.println("언제 출력될까요?");
	}

}


class MyThread extends Thread {
	public MyThread(String szName) {
		super(szName);
	}
	public void run() {//MyThread 스레드를 start()호출해야지만, 자동실행 되는 메서드
		for(int cnt =0;cnt<100;cnt++) {
			System.out.print(this.getName() + " ");
		}
		
	}
}