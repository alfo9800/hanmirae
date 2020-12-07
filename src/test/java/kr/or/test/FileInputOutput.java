package kr.or.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputOutput {


	public static void main(String[] args) throws IOException {
		// 내PC에 저장된 텍스트메모장파일 읽어들이고, 출력하기 구현
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		//(아래)오브젝트(=실행가능한 클래스형)변수 생성, 인스턴스(=실행가능한 클래스)
		try {
			fileInputStream = new FileInputStream("C:\\egov\\workspace\\hanmirae\\manifest.yml");
			//fileOutputStream = new FileOutputStream("");//신규파일에 저장하는 클래스명령어 집합
			//지금 까지는 반복문으로 for문을 사용했는데, for는 (시작;끝;증가)가 필요해서 제약이 있었는데, 대신 while(~하는 동안) {구현을 반복}
			int byte_content;
			while( (byte_content = fileInputStream.read()) != -1 ) {//-1은 문서 끝을 읽어들일 때 까지
				System.out.println("바이트형 문자 읽어들이기 " + (char)byte_content);//char가 들어가지 않으면 그냥 아라비아 숫자만 나옴.
			}
		} catch (FileNotFoundException e) {
			System.out.println("경로에서 파일을 찾을 수 없습니다." + e.toString());
			e.printStackTrace();
		}
		
	}

}
