package kr.or.member;

import java.io.IOException;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;

import kr.or.member.MemberService;
import kr.or.member.MemberVO;

public class MainController {

	public static void main(String[] args) throws IOException {
		// 실행 메서드
		MemberVO memberVO = new MemberVO();
		memberVO.setName("홍길동");
		memberVO.setAge(43);
		memberVO.setPhoneNum("010-1111-2222");
		MemberVO memberVO2 = new MemberVO();
		memberVO2.setName("성춘향");
		memberVO2.setAge(19);
		memberVO2.setPhoneNum("010-3333-4444");
		MemberVO memberVO3 = new MemberVO();
		memberVO3.setName("이몽룡");
		memberVO3.setAge(23);
		memberVO3.setPhoneNum("010-2222-8888");
		// 위 각각 3개의 레코드를 1군데 담을 배열 클래스오브젝트를 하나 생성
		MemberVO[] members = new MemberVO[3];
		members[0] = memberVO;
		members[1] = memberVO2;
		members[2] = memberVO3;

		// 전에 사용했던 출력보다는 개선된 방식의 출력 (아래) - MemberService 서비스 전용클래스 만들어서 처리
		MemberService memberService = new MemberService();// 자바 오브젝트객체 생성하는 방법
		memberService.printMember(members);// 서비스클래스의 메소드 실행
		
		//서버시간 가져오기 코딩(아래)
		//1.서버시간을 가져올 수 있는 commons-net 라이브러리를 추가.(메이븐 디펜던시 사용)
		NTPUDPClient timeClient = new NTPUDPClient();
		timeClient.setDefaultTimeout(1000);
		//네트워크로 서버시간을 가져올때, 서버의 응답이 1초를 넘기면...재접속을 하시오.(timeout)
		timeClient.open();
		//pool.ntp.org는 서버시간을 보내 줄 실제 주소.
		InetAddress adress = InetAddress.getByName("pool.ntp.org");
		TimeInfo timeInfo = timeClient.getTime(adress);
		//서버시간을 담은 timeinfo 객체변수를 사용(아래)
		//TimeStamp 1970년 부터 초단위로 계산된 현재까지의 초를 합친 결과 값
		long returnTime = timeInfo.getMessage().getTransmitTimeStamp().getTime();
		//위 반환받은 타임스탬프초 값을 사람이 알아볼 수 있는 시간으로 변환
		Date nowDate = new Date(returnTime);
		//System.out.println("현재 서버시간은-미국 " + nowDate);
		//Wed Dec 09 11:24:03 KST -> 2020/12/09 11:24:03 AM
		//SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		//System.out.println("현재 서버시간은-한국 " + formatDate.format(nowDate));
		LocalDateTime localDateTime = nowDate.toInstant()
				.atZone(ZoneId.systemDefault())
				.toLocalDateTime();
		System.out.println("Server 시간: " + localDateTime);
		System.out.println("로컬PC 시간 : " + localDateTime.now());
	}
}
