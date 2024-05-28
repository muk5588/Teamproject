package interceptor;

import java.beans.Encoder;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import user.dto.User;

public class AdminInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);
	//인터셉터 패키지 실행전 AOP로 로그인 검사 진행되었음.
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	logger.debug("어드민 인터셉터 실행");
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
