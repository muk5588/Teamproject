package interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import user.dto.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		logger.debug("로그인 인터셉터 실행");
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("dto1");
		if (user == null) {
			logger.debug("로그인되지 않은 사용자입니다. 세션을 무효화하고 루트 페이지로 리디렉션합니다.");
			session.invalidate();
			response.sendRedirect("/");
			return false;
		}

		logger.debug("로그인된 사용자: {}", user.getNickname()); // 사용자 이름이 있다고 가정한 예

		return true;
	}
}
