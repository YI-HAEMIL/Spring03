package securityTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

//** 로그인 성공이후 특정동작을 하도록 제어함
//=> AuthenticationSuccessHandler interface 를 구현하여 작성함
//=> 로그인한 사용자에게 부여된 권한(Authentication) 객체를 이용해서
// 사용자가 가진 모든 권한을 문자열로 확인하고 처리해줌.   
//=> Authentication : 입증, 증명, 인증
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		List<String> roleNames = new ArrayList<>();
		
		System.out.println("** authentication.getAuthorities() =>"+authentication.getAuthorities());
		
		// authentication.getAuthorities() : Collection<....>
		authentication.getAuthorities().forEach(authority -> {
			roleNames.add(authority.getAuthority());
		});
		
		// authentication.getAuthorities() 객체의 값들을 하나씩 roleNames 에 담는 반복문
		// => for 문으로 변형해보면
		// for (String authority : authentication.getAuthorities()) {
		// roleNames.add(authority.getAuthority()); }

		/*
		 	*** 람다식, 또는 람다 함수
		 	=> 익명 함수(Anonymous functions)를 지칭하는 용어로써 JAVA8 버전부터 지원.
		 	=> 매개변수 -> {실행문 ...}
		 	   매개 변수를 이용해서 중괄호 블록을 실행한다는 의미
		 	   예제 : for (String s : list) {
		 	   System.out.println(s); } list.forEach(s -> System.out.println(s));
		 */

		if (roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/green/adminf"); //atestf, adminf
		} else if (roleNames.contains("ROLE_USER")) {
			response.sendRedirect("/green/mlist");
		} else
			response.sendRedirect("/green/home");
	}
}
