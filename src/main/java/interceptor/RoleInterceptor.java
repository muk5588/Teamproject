package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import user.dto.User;

@Component
public class RoleInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RoleInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("인터셉터 실행");
		//세션 객체를 가져옴.
		HttpSession session = request.getSession();
		//세션에서 유저객체 get
		User user = (User) session.getAttribute("dto1");
		if(user == null) {
			session.invalidate();
			response.sendRedirect("/login");
			return false;
		}
//		PrintWriter out = response.getWriter();
//            response.setCharacterEncoding("UTF-8");
//	        out.println("<script>alert('로그인이 필요합니다.'); location.href='/login';</script>");
//		out.flush();
		
		return true;
	}
	
	
}
