package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import user.dto.UserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;



public class CommonUtils {
	private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);
	
	  
	
	public static String getRandomString(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	

	
	public static String getUserId(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		UserDTO sessionUser = (UserDTO) session.getAttribute("USER_INFO");
		String USERID = "";
		if(sessionUser != null){
			
			USERID = sessionUser.getUSERCODE();
		}
		
		return USERID;
	}

	//관리자코드?
	public static String getAuthCode(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		UserDTO sessionUser = (UserDTO) session.getAttribute("USER_INFO");
		String AUTHCODE = "";
		if(sessionUser != null){
			
			AUTHCODE = sessionUser.getAUTHCODE();
		}
		
		return AUTHCODE;
	}
	
	public static String getADMINYN(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		UserDTO sessionUser = (UserDTO) session.getAttribute("USER_INFO");
		String ADMINYN = "";
		if(sessionUser != null){
			
			ADMINYN = sessionUser.getADMINYN();
		}
		
		return ADMINYN;
	}
	public static String getLoginYN(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		UserDTO sessionUser = (UserDTO) session.getAttribute("USER_INFO");
		String LOGIN = "";
		if(sessionUser != null){
			
			LOGIN = sessionUser.getLOGIN();
		}
		
		return LOGIN;
	}
	public static String getUserName(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		UserDTO sessionUser = (UserDTO) session.getAttribute("USER_INFO");
		String UserName = "";
		if(sessionUser != null){
			
			UserName = sessionUser.getUSERNAME();
		}
		
		return UserName;
	}

	
	public static String getHeader(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		UserDTO sessionUser = (UserDTO) session.getAttribute("USER_INFO");
		String Header = "";
		if(sessionUser != null){
			
			Header = sessionUser.getHEADER();
		}
		
		return Header;
	}
	public static void setHeader(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		UserDTO sessionUser = (UserDTO) session.getAttribute("USER_INFO");
		String head = getHeader(request);
		
		if(sessionUser != null){
			if("ON".equals(head)){
				
				sessionUser.setHEADER("");
			}else{
				sessionUser.setHEADER("ON");
				
			}
		}
		
	}
	public static String NullToEmpty(Object obj){
		String str = "";
		if(obj == null){
			str = "";
		}else{

			str = obj.toString();
		}


		return str;
	}
}
