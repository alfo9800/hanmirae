package kr.or.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * OpenAPI클래스로 HRD-Net에서 제공하는 구직자훈련과정API 목록을 출력하는 앱
 * @author 한미래
 */
public class OpenAPI {
	
	//외부연계 역할 메서드
	public static void serviceApi() {
		BufferedReader bufferedReader = null; //HRD-Net에서 전송받은 데이터를 임시 저장하는 공간.
		String urlStr = "http://www.hrd.go.kr/hrdp/api/apipo/APIPO0101T.do?srchTraEndDt=20210317&pageSize=10&srchTraArea1=44&srchTraOrganNm=%ED%9C%B4%EB%A8%BC%EA%B5%90%EC%9C%A1%EC%84%BC%ED%84%B0&srchTraStDt=20201108&sortCol=TR_STT_DT&authKey=XwG9BpRdfR1eXASUmvVrDnus1BlGDbVm&sort=ASC&returnType=XML&outType=1&pageNum=1&srchTraPattern=N1&srchPart=-99&apiRequstPageUrlAdres=/jsp/HRDP/HRDPO00/HRDPOA60/HRDPOA60_1.jsp&apiRequstIp=27.117.246.232";
		try {
			URL url = new URL(urlStr);
			try {
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				urlConnection.setRequestMethod("GET");//URL쿼리스트링 파라미터를 보낸다는 의미.= GET방식.
				bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"euc-kr"));
				String result="";
				String line;
				int cnt = 0;
				//While반복문 시작
				while( (line=bufferedReader.readLine()) !=null ) {
					cnt = cnt + 1;
					result = result + line +"\n"; // \n은 Newline을 나타내는 기호. 결과적으로는 엔터와 같은 의미.					
				}
				System.out.println("버퍼리더로 읽어들인 최종결과 값은 아래와 같습니다. while반복 횟수는" + cnt);
				//System.out.println(result);
				
				//XmlUtils.java 클래스를 이용해서 xml태그내용을 눈에 보기 쉽게 출력
				String result_xmlUtils = XmlUtils.formatXml(result);
				System.out.println(result_xmlUtils);
			} catch (IOException e) {
				//Http웹접근에러 상황 발생 했을 때
				System.out.println("Http 웹접근 에러입니다. 왜냐하면" + e.toString());
			}
		} catch (MalformedURLException e) {
			//외부연계 URL주소형식이 잘못 되었을 때 에러상황발생
			System.out.println("URL주소형식이 잘못되었습니다. 왜냐하면" + e.toString());
		}
	}
	//static메서드는 new키워드로 객체오브젝트 생성없이 바로 접근이 가능한 메서드를 말함.
	
	public static void main(String[] args) {
		serviceApi();
		/*
		   //일반 메소드와 스태틱 메서드 
		   StaticTest staticTest = new StaticTest();
		   staticTest.test2();//노스테틱 오브젝트생성 후 접근 가능 StaticTest.test();
		   //스태틱 메서드는 오브젝트 생성없이 클래스로 바로 접근이 가능한 메서드. 변수도 가능함.
		 */
		/*
		  int sum = 0;
		  while(sum < 10) { 
			  sum = sum +1; //sum = sum +1 누적로직을 확인
			  System.out.println("지금까지 누적된 값은" + sum);
		  }
		 */		
	}

}

class StaticTest {
	public static void test() {
		//스태틱메서드는 클래스를 읽어들이는 순간, 메모리 기준으로 고정메모리를 할당받음.
		System.out.println("Static Test");
	}
	public void test2() {//일반메서드는 new키워드로 오브젝트를 생성할 때 메모리 할당받음.
		System.out.println("normal test");
	}
}
