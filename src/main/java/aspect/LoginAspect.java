package aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Aspect
public class LoginAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoginAspect.class);

    @Before("execution(* interceptor..*(..))")
    public void checkLoginStatus() throws IOException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getResponse();
        HttpSession session = request.getSession();
        Object user = session.getAttribute("dto1");
        if (user == null) {
            // 로그인x
            // JS를 이용하여 alert 띄우고, 이동할 경로 처리,
            PrintWriter out = response.getWriter();
	        //이전 페이지로
            String previousUrl = request.getHeader("Referer");
            out.println("<script>alert('로그인이 필요합니다.'); window.location.href='" + previousUrl + "';</script>");
            out.flush();
            //로그인 페이지로
//	        out.println("<script>alert('로그인이 필요합니다.'); location.href='/login';</script>");
//	        out.flush();
            return;
        } 
        //이외의 경우 로그인 된 상태.
    }
}
