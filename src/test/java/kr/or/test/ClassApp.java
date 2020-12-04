package kr.or.test;
//오리지널 자바프로그램 형식: 아래 하는 이유는 자바이론공부-> 스프링 프로그램과는 형식이 다름.
public class ClassApp {

	public static void main(String[] args) {
		// 이 클래스의 진입메서드 main 실행
		Circle c = new Circle(5);//c라는 인스턴스(오브젝트 클래스)변수 c 생성. new 키워드 생성자Circle(반지름 값5)
		//위에서 new 키워드는 메모리에 프로그램 실행공간을 메모리에 새로 할당한다는 뜻.
		Circle c2 = new Circle(10);
		//Circle클래스를 c 이름으로 prefix해서 사용하는 것임.
		//자바크로그램의 특징은 OOP프로그래밍의 대표적인 사례, Objet=위 에서 new 키워드로 생성한 c오브젝트,
		//자바에서 OOP를 사용방식을 설명하는 예, 스프링에서 는 new키워드 대신 @Inject를 주로 사용.
		double print_result = c.gethanmirae();
		System.out.println("첫 번째 원의 넓이는" + print_result + "입니다.");
		double print_result2 = c2.gethanmirae();
		System.out.println("두 번째 원의 넓이는" + print_result2 + "입니다.");
	}

}

class Circle {
	int r;//멤버변수 반지름. 이 클래스에서 전연변수
	public Circle(int a) {//클래스 생성자 메소드 ->(a가 필요한 이유) a인자=매개변수=파라미터 값은 new키워드로 오브젝트 만들어질 때 발생
		r=a;
	}
	public double gethanmirae() {
		return r*r*3.14; //원의 넓이를 반환
	}
}