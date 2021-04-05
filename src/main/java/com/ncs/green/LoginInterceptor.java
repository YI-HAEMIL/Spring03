package com.ncs.green;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//** 로그인처리를 담당하는 인터셉터 구현
//** HandlerInterceptorAdapter
//=> HandlerInterceptor 인터페이스를 사용하기 편리하게 구현해 놓은 추상 클래스
//오버라이딩 단축키 : shift + alt + s + v 

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	// preHandle(): Controller 요청 호출 전에 수행
	// return true : 계속진행
	// return false : 인터셉터, 컨트롤러 실행 중지됨 (만약 필터의 응답처리가 있다면 실행됨)  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1) session 객체 가져오기
		HttpSession session = request.getSession(false);
		
		// 2) Login 확인하기 > 결과 처리
		if (session != null && session.getAttribute("loginID") != null) {
			// Login 되어있음 > Controller 처리
			return true;
			
		} else { // Login 안 되어있음 > Login 유도
			// 2.1) redirect (loginForm 요청)
			// response.sendRedirect("/green/mloginf");
			// 2.2) forward
			request.setAttribute("message", "로그인 후 이용하실 수 있습니다");
			String url = "/WEB-INF/views/member/loginForm.jsp";
			request.getRequestDispatcher(url).forward(request, response);
			return false;
		}
	}
	
	// postHandle(): Controller 요청 호출 후 view페이지 출력 직전에 수행
	// -> 매개변수 ModelAndView 객체에 컨트롤러에서 전달해온 Model 객체가 전달됨으로
	// view페이지 랜더링 되기전 ModelAndView 객체 조작 가능
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}
	
	// afterCompletion(): 컨트롤러가 수행되고 view페이지 랜더링 후 실행됨
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
	
	// afterConcurrentHandlingStarted():
	// Servlet3.0 부터 비동기 요청 가능하고,
	// 비동기 요청시에는 postHandle(), afterCompletion() 은 실행되지않고 이 메서드가 실행됨
	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}
} //class
