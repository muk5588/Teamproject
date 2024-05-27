package interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import user.dto.User;

public class AdminInterceptor implements HandlerInterceptor {
	//인터셉터 패키지 실행전 AOP로 로그인 검사 진행되었음.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	HttpSession session = request.getSession();
       User user = (User) session.getAttribute("dto1");
       if(user == null ) {
    	   session.invalidate();
           PrintWriter out = response.getWriter();
           out.println("<script>alert('잘못된 접근입니다..'); window.location.href='/';</script>");
           out.flush();
           return false;
       }
       
       int userGrade = user.getgradeno();
       if(userGrade == 5000) {
    	   //최고 관리자
    	   
       }
       
       if( userGrade == 5000 || userGrade == 0 ) {
    	   //최고 + 일반 관리자
    	   
    	}
    	
        return true;
    }
}
