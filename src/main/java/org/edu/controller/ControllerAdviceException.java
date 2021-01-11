package org.edu.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice //컨트롤러에서 발생되는 사항. 즉, (에러처리&CRUD액션을 중간에 가로채는 기능) 클래스 명시
public class ControllerAdviceException {

	//ModelAndView클래스는 jsp로 반환 시 값을 저장하는 역할
	@ExceptionHandler(Exception.class) //-> 언제 아래의 메서드가 실행될지 명시하는 클래스. 컨트롤로에서 예외(에러)가 발생했을 때 아래 메서드를 자동 실행.
	public ModelAndView errorModelAndView(Exception ex, HttpServletRequest request) {
		//AdminController에서 사용한 Model클래스는 jsp값을 보낼 때 변수.
		//이곳에서 사용한 ModelAndView클래스는 jsp로 값을 보내고, 반환값으로 model값을 보낼 jsp경로도 지정
		ModelAndView modelAndView = new ModelAndView();
		
		//model값을 보낼 jsp경로 지정하는 명령-> setViewName
		modelAndView.setViewName("home/spring_error"); //반환한 jsp위치 지정
		modelAndView.addObject("exception", ex); //만약 위 Http~~대신 Model model이라면. -> model.addAttribute("exception", ex); / return "home/error";로 사용한다.
		//-------------------------------------------------------------------------------------------------------------------------------------------------------
		String referer = request.getHeader("Referer"); //이전페이지로 이동 할 때 필요한 링크 값 = 크롬 확인 가능한 헤더값
		request.getSession().setAttribute("prevPage", referer); //session이란: 클라이언트가 서버에 접속 시 서버에서 발생하는 고유 변수 값. prevPage세션키를 하나 생성. 저장된 prevPage키를 jsp에서 사용하게 됨.
		//결과로 jsp에서 사용가능한 변수 2가지 : 오브젝트변수(클래스변수)인 exception, 세션변수인 prevPage.
		
		return modelAndView;
	}
}
