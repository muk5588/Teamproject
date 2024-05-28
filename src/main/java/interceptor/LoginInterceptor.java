package interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import user.dto.User;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("dto1");
		if(user == null ) {
    	   session.invalidate();
           PrintWriter out = response.getWriter();
//           out.println("<script>alert('로그인상태가 아닙니다');</script>");
           out.println("<script>window.location.href='/login';</script>");
           out.flush();
           return false;
       }
		return true;
	}
	
}
