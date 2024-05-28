package interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import user.dto.User;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
    	logger.debug("어드민 인터셉터 실행");
		HttpSession session = request.getSession();
       User user = (User) session.getAttribute("dto1");
       PrintWriter out = response.getWriter();
       if(user == null ) {
    	   session.invalidate();
//           out.println("<script>alert('로그인상태가 아닙니다');</script>");
//    	   out.println("<script>window.location.href='/';</script>");
    	   response.sendRedirect("/");
           out.flush();
           return false;
       }
       
       int userGrade = user.getgradeno();
       if( userGrade != 5000 && userGrade != 0) {
    	   response.sendRedirect("/");
    	   return false;
       }
       
       String URI = request.getRequestURI();
       if (URI.startsWith("/grade/") || URI.contains("/grade/") || URI.startsWith("/shop/admin/") || URI.contains("/shop/admin/") || URI.startsWith("/order/admin/") || URI.contains("/order/admin/")) {
    	   if( userGrade != 5000) {
    		   logger.debug("requestURI :{}",URI);
    		   response.sendRedirect("/user/adminPage");
    		   return false;
    	   }
    	}
       logger.debug("requestURI :{}",URI);
    	
        return true;
    }
}
