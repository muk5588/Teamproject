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
		int grade = user.getgradeno();
		String URI = request.getRequestURI();
		String categoryNoStr = request.getParameter("categoryNo");
		logger.debug("categoryNoStr : {}",categoryNoStr);
		logger.debug("URI : {}",URI);
		//없을경우
		if( categoryNoStr == null) {
			if(grade != 5000 && grade != 0 && grade != 3 ) {
				//전체 조회 가능한 사용자가 아닌경우..
				response.sendRedirect("/");
				return false;
			}else {
				return true;
			}
		}
		 try {
             int categoryNo = Integer.parseInt(categoryNoStr);
             if( grade == 5000 || grade == 0 || grade == 3) {
        		 return true;
             }else if (grade == 2) {
				if(categoryNo == 52) {
					//이벤트 글쓰기, 수정 막기
					response.sendRedirect("/");
					return false;
				}else {
					return true;
				}
			}else if( grade == 1) {
				if( categoryNo == 52 || categoryNo == 51) {
					response.sendRedirect("/");
					return false;
				}else {
					return true;
				}
			}
             
         } catch (NumberFormatException e) {
        	 //String -> int 형변환 fail
        	 response.sendRedirect("/");
        	 return false;
         }
		response.sendRedirect("/");
		return false;
	}
	
	
}
