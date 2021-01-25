package org.edu.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.edu.vo.BoardVO;
import org.edu.vo.PageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * DebugAdvice클래스로서 디버그를 Advice라는 AOP기능을 사용하게 디버그를 실행하게 된다.
 * @author 한미래
 *
 */
@Component //스프링빈으로 사용하겠다는 명시
@Aspect //AOP기능을 사용하겠다는 명시
public class DebugAdvice {
	private static final Logger logger = LoggerFactory.getLogger(DebugAdvice.class); //System.out.println(); 대신 쓰는 것.
	/**
	 * @throws Throwable 
	 * @Around 애노테이션 클래스는 메서드 실행에 직접 관여함.
	 * Around클래스 타입의 파라미터(매개변수)로 ProceedingJoinPoint타입의 클래스사용
	 * ProceedingJoinPoint클래스는: JoinPoint(=실행되는 지점)의 모든 메서드를 가지면서, 직접 타겟클래스의 메소드를 실행 할 수 있는 기능이 있는 클래스.
	 * 
	 * ProceedingJoinPoint.proceed()메서드는 특이하게도 Exception클래스보다 상위로 예외를 전달.
	 * (즉, 일반 Exception에러처리보다 외부에서 ProceedingJoinPoint.proceed()메서드 먼저 처리 된다.)
	 * proceed()메서드생성에 Throwable을 사용하고, 시간을 체크하는 기능을 작성할 수 있음.
	 * 시간 체크하는 기능 필요이유? 개발자가 만든 다양한 메서드의 시작과 끝 시간을 체크 가능.
	 * ex) 개발자가 만든 클래스를 실행 시켰을 때, 5~10분 이상 느리게 진행되는 현상이 생김. 어느 메서드에서 시간 얼만큼 소요되는지 확인해야 지만. 트리블 슈팅이 가능하다.
	 */
	
	//아래 @Around()애노테이션 클래스의 ()는 디버그할 영역 지정.
	@Around("execution(* org.edu.controller.AdminController.*(..))") //*(..) -> controller 안에 있는 '모든 메서드'를 뜻한다.
	//@Around("execution(* org.edu.service.MemberService.*(..))") 
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("AOP 디버그 시작 ===============================");
		long startTime = System.currentTimeMillis(); //현재 컴퓨터시간을 저장하는 변수
		
		logger.info(Arrays.toString(pjp.getArgs())); //pjp클래스 매개변수 값을 get으로 가져와서(반환받아서) toString형 변환출력.
		//위는 현재 시간체크하는 메서드가 어떤 메서드인지 눈으로 확인하려고 logger.debug로 출력
		
		Object result = pjp.proceed(); //AdminController에 있는 메서드가 실행됨.(시간이 소요)
		long endTime = System.currentTimeMillis(); //현재 컴퓨터시간을 저장하는 변수
		logger.info(pjp.getSignature().getName() + "메서드명의 실행시간은:" + (double)(endTime-startTime)/1000 + "초 입니다.");
		
		logger.info("AOP 디버그 끝 =================================");
		return result;
	}
	
	//현재 클래스명은 디버그용AOP였지만.. (AOP는 예전자바코딩의 인터셉터AdviceCintroller와 같은 가능)
	//추가로 다중게시판용 세션관리 AOP기능 사용
	@Around("execution(* org.edu.controller.*Controller.*(..))")
	public Object sessionManager(ProceedingJoinPoint pjp) throws Throwable {
	   //AOP에서 RequestContextHodler클래스를 이용해서 HttpServletRequest사용하기
	   HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	   
	   //컨트롤러 클래스에서 매개변수로 받는 값 초기화
	   PageVO pageVO = null;
	   BoardVO boardVO = null;
	   String board_type = null;
	   
	   //컨트롤러 클래스에서 실행되는 모든 메서드 = *Controller.*(..) 의 매개변수 값을 꺼내오기 (향상된 for문 사용)
	   for(Object object:pjp.getArgs()) { //Args:Arguments는 예로, board_update의 메서드를 뽑아온다
			   logger.info("jsp를 통해서 호출된 컨트롤러의 메소드 매개변수 꺼내오기: " +object);
			   if(object instanceof PageVO) { //instanceof는 객체타입을 비교하는 연산자(그래서 뒤에 들어오는 객체타입은 대문자!)
				   pageVO = (PageVO) object;
				   board_type = pageVO.getBoard_type(); //세션변수로 사용할 값을 발생.jsp에서 발생한 notice,gallery들이 여기서 발생.
		   }else if(object instanceof BoardVO) {
			   boardVO = (BoardVO) object;
		   }
	   }
	   if(request != null) { //jsp에서 요청사항이 발생될때만 실행
		   HttpSession session = request.getSession();
		   if(board_type != null) {
			   session.setAttribute("session_board_type", board_type); 
		   } 
		   
		   //pageVO와 BoardVO에서 session변수로 get/set해서 사용X
		   if(session.getAttribute("session_board_type") != null) {
				   board_type = (String) session.getAttribute("session_board_type");
			   }
		   		if(pageVO != null) {
		   		 pageVO.setBoard_type(board_type);
		   		}
		   		if(boardVO != null) {
		   		 boardVO.setBoard_type(board_type);
		   		}
	   		}
			   Object result = pjp.proceed();
			   return result;
	}
}
