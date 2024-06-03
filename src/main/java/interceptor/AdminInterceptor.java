package interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import user.dto.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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
           sendRedirectWithAlert(response, "로그인이 필요합니다.");
    	   response.sendRedirect("/");
           out.flush();
           return false;
       }
       
       int userGrade = user.getgradeno();
       if( userGrade != 5000 && userGrade != 0) {
           sendRedirectWithAlert(response, "관리자 권한이 필요합니다.");
           response.sendRedirect("/");
    	   return false;
       }
       
       String URI = request.getRequestURI();
       if (URI.startsWith("/grade/") || URI.contains("/grade/") || URI.startsWith("/shop/admin/") || URI.contains("/shop/admin/") || URI.startsWith("/order/admin/") || URI.contains("/order/admin/")) {
    	   if( userGrade != 5000) {
    		   logger.debug("requestURI :{}",URI);
               sendRedirectWithAlert(response, "관리자 페이지에 접근할 권한이 없습니다.");
               response.sendRedirect("/user/adminPage");
    		   return false;
    	   }
    	}
       logger.debug("requestURI :{}",URI);
        return true;
    }
    private void sendRedirectWithAlert(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + message + "'); window.location.href='/';</script>");
        out.flush();
    }
}
