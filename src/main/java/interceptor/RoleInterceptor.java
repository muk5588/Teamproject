package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import user.dto.User;

public class RoleInterceptor implements HandlerInterceptor {
	//인터셉터 패키지 실행전 AOP로 로그인 검사 진행되었음.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//세션 객체를 가져옴.
		HttpSession session = request.getSession();
		//세션에서 유저객체 get
		User user = (User) session.getAttribute("dto1");
		
		return true;
	}
	
	
}
