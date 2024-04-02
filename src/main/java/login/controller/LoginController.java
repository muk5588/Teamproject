package login.controller;

import java.net.InetAddress;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.focs.common.CommandMap;
import com.focs.common.util.CommonUtils;
import com.focs.common.util.CustomUserDetails;
import com.focs.common.util.DateUtil;
import com.focs.common.util.Sha256Util;
import com.focs.login.dao.LoginDAO;
import com.focs.login.service.LoginService;
import com.focs.login.service.LoginServices;
import com.focs.pak.service.PakProManagementService;
import com.focs.usr.dao.UserDao;

import net.sf.json.JSONObject;

@Controller
public class LoginController implements LogoutHandler {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
    @Autowired
    LoginServices loginServices;
    
    @Autowired
    LoginService loginService;

	@Autowired
	UserDao userDao;

    @Resource(name="loginDAO")
	private LoginDAO loginDAO;
    
    @Resource(name="pakProManagementService")	
	private PakProManagementService pakProManagementService;
    
    @RequestMapping(value="/")
	public String homePage (HttpServletRequest request, Model model,Map<String, Object> map)throws Exception{

    	model.addAttribute("ROLELIST",userDao.getUsrGroupList(map));
		map.put("SORT", "ROLENAME");
		model.addAttribute("ROLELIST1",userDao.getUsrGroupList(map));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "index.single";
//		}
//		return "redirect:/home";
	}

    @RequestMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "/login";
    }

    @GetMapping(value="/index")
    public String login2 (HttpServletRequest request, Model model,Map<String, Object> map)throws Exception{

    	model.addAttribute("ROLELIST",userDao.getUsrGroupList(map));
		map.put("SORT", "ROLENAME");
		model.addAttribute("ROLELIST1",userDao.getUsrGroupList(map));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if ("anonymousUser".equals(authentication.getPrincipal()) && "P".equals(request.getParameter("login"))) {
        	model.addAttribute("changePwd", "Y");
        	return "index.single";
        } else if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
        	return "index.single";
        }
        return "index.single";
//        return "redirect:/homepage/noticeList";
    }
    
    @GetMapping(value="/login")
    public String login () {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
    		return "index.single";
    	}
    	return "index.single";
//        return "redirect:/homepage/noticeList";
    }

	@RequestMapping("/myPage.do")
	public String myPage(HttpServletRequest request, Model model) throws Exception{
		return "/login/myPage.usrpopup";
	}

	@Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        try {
            String sessionId = null;
            if (authentication.getDetails() instanceof WebAuthenticationDetails) {
                WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
                sessionId = details.getSessionId();
            }
            loginServices.saveLogoutLog(authentication.getName(), sessionId);
            //SessionListener 에서 처리
            //loginServices.deleteToken(authentication.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping("/login/loginProc.do")
	public String loginProc(CommandMap commandMap, HttpServletRequest request) throws Exception{
		String usercode = request.getParameter("USERCODE"); 
        String userpswd = request.getParameter("USERPSWD");
        commandMap.put("USERCODE", usercode);
        commandMap.put("USERPSWD", userpswd);
	
        System.out.println("commandMap@@@@@="+commandMap.getMap().toString());
        
        //String password = (String) param.get("userPassword");
        // salt 생성
		java.util.Random random = new java.util.Random();
		byte[] saltBytes = new byte[8];
		random.nextBytes(saltBytes);
		
		StringBuffer salt = new StringBuffer();
		for (int i = 0; i < saltBytes.length; i++)
		{
		    // byte 값을 Hex 값으로 바꾸기.
			salt.append(String.format("%02x",saltBytes[i]));
		}
		// param.put("salt", salt.toString());
		
		// Password와 랜덤으로 생성한 salt를 섞어서 SHA256 암호화를 한다.
		String encrypt = Sha256Util.getEncrypt(userpswd, saltBytes);
		
		System.out.println("encrpt!! [" + encrypt +"]");
		
		// spring에서 재공하는 bcrypt알고리즘을 사용하여 암호화를 한다.
		BCryptPasswordEncoder encpwd = new BCryptPasswordEncoder();
		String encrypt2 = encpwd.encode(userpswd);
		System.out.println("encrypt2 [" + encrypt2 + "]");
		
		// spring에서 재공하는 standard passwrod 알고리즘을 사용하여 암호화한다. 기본 값이 SHA256이다.
		StandardPasswordEncoder stencpwd = new StandardPasswordEncoder();
		String encrypt3 = stencpwd.encode(userpswd);
		System.out.println("encrypt3 [" + encrypt3 + "]");
		
		String authCode = request.getParameter("AUTHCODE");
		System.out.println("authCode@@@"+ authCode);
		//stencpwd.matches(rawPassword, encodedPassword);
		
		System.out.println("commandMap="+commandMap.getMap().toString());
		try{
			//관리자 승인 전
			int appStt = userDao.getloginApp(commandMap.getMap());
			System.out.println("appStt"+appStt);
			if(appStt == 1) {
				return "redirect:/index?login=X";
			}
			
			List<Map<String, Object>> resultInfo = (List<Map<String, Object>>) loginService.loginProc(commandMap.getMap());
			
			List<Map<String, Object>> resultInfo2 = (List<Map<String, Object>>) loginService.getloginMenuList(commandMap.getMap());
			
			List<Map<String, Object>> pass = (List<Map<String, Object>>) loginService.passCh(commandMap.getMap());
			
			
			if(resultInfo.size() > 0){
				
				String s_usercode = "";
				String s_userpswd = "";
				
				 //비밀번호가 없을경우
			    if(resultInfo.get(0).get("USERPSWD") == null){
			    	UserDTO sessionUser = new UserDTO();
			    	resultInfo.get(0).put("LOGIN", "N");
			    	
			    	BeanUtils.populate(sessionUser, resultInfo.get(0));
			    	
			    	System.out.println("-------비밀번호없는경우------resultInfo.get(0)"+resultInfo.get(0).toString());
					
					HttpSession session = request.getSession();
			    	
					session.setAttribute("USER_INFO", sessionUser);
			    	
			    	//return "redirect:/domfpl/change_password.do";
//			    	return "redirect:/index.do?login=C";
			    	return "redirect:/index?login=C";
			    }
				
			    for (String mapkey : resultInfo.get(0).keySet()){
			        //System.out.println("key:"+mapkey+",value:"+resultInfo.get(0).get(mapkey));
			    	
			    	if(mapkey.equals("USERCODE")) s_usercode = resultInfo.get(0).get(mapkey).toString();
			    	if(mapkey.equals("USERPSWD")) s_userpswd = resultInfo.get(0).get(mapkey).toString();
			    }
			    
				/*
				 * 2016.01.29 비밀 번호컬럼의 암호화 진행이 되면 
				 * 비밀번호를 SHA256으로 암호화 하여 처리 하는 로직을 추가 해야함 
			     */
			    //로그인 성공
				//if(usrid.equals(s_usrid) && usrpw.equals(s_usrpw)){
			    
			    System.out.println("usercode : "+usercode);
			    System.out.println("s_usercode : "+s_usercode);
			    System.out.println("stencpwd : "+stencpwd);
			    System.out.println("userpswd : "+userpswd);
			    System.out.println("s_userpswd : "+s_userpswd);
			    
			    if(usercode.equals(s_usercode) && stencpwd.matches(userpswd, s_userpswd)){
			    	
			    	UserDTO sessionUser = new UserDTO();
			    	resultInfo.get(0).put("LOGIN", "Y");
			    	sessionUser.setMENULIST(resultInfo2);
			    	System.out.println("----LOGIN=Y---------resultInfo.get(0)"+resultInfo.get(0).toString());
			    	
			    	BeanUtils.populate(sessionUser, resultInfo.get(0));
			    	
					HttpSession session = request.getSession();
					session.setAttribute("USER_INFO", sessionUser);
					UserDTO sessionUser2 = (UserDTO) session.getAttribute("USER_INFO");
					HashMap<String, Object>loginMap = new HashMap<>();

					String ip = request.getRemoteAddr();
					if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
						InetAddress inetAddress=InetAddress.getLocalHost();
						ip = inetAddress.getHostAddress();
					}

					loginMap.put("USERID", sessionUser2.getUSERNAME());
					loginMap.put("IPADDRESS", ip);
					loginMap.put("SESSIONID", session.getId());

					loginDAO.insertLoginLog(loginMap);

					//-------------------------------------------------------- ksj 20160922 rcm spot test data insert 
//			        session.setAttribute("SELECTED_ARP", "EBB");  //기본 선택 공항 저장
			        //----------------------------------------------------
					Calendar cal = Calendar.getInstance();
					String Year = Integer.toString(cal.get(Calendar.YEAR));
					String Month = Integer.toString(cal.get(Calendar.MONTH)+1);
					String Date = Integer.toString(cal.get(Calendar.DATE));
					if(Month.length() != 2){
						Month = '0'+Month;
					}
					String abc = Year+Month+Date;
					String passDate = "";
					String passInt = "";
					if(resultInfo.get(0).get("PSWDDATE") == null) {
						passDate = abc;
						passInt = "0";
					} else {
						passDate = resultInfo.get(0).get("PSWDDATE").toString();
						passInt = pass.get(0).get("CDNAME1").toString();
					}
					String CDNAME1 = DateUtil.getDateString(DateUtil.addDate(passDate, Integer.parseInt(passInt)), "yyyyMMdd");
					if(Integer.parseInt(abc) >= Integer.parseInt(CDNAME1)){
//						return "redirect:/index.do?login=C";
						return "redirect:/index?login=C";
					}else{
//						return "redirect:/index.do";
						return "redirect:/index";
					}
				}
				//로그인 실패
				//로그인 메시지를 추가 하여 JSP에서 로그인 유무 처리???
				else{
					System.out.println("-----resultInfo.get(0)"+resultInfo.get(0).toString());
//					return "redirect:/index.do?login=P";
					return "redirect:/index?login=P";
				}
				
			}
			
		}catch(Exception e){
    		StackTraceElement[] elem = e.getStackTrace();log.debug(e.getMessage());
    		
			for ( int i = 0; i < elem.length; i++ ){
				log.debug(elem[i].toString());
				System.out.println(elem[i]);
			}
			
    	}
		
//		return "redirect:/index.do?login=U";
		return "redirect:/index?login=U";
		
	}	

	@RequestMapping("/login/passwordChange.do")
	public String passwordChange(CommandMap commandMap, HttpServletRequest request, Model model) throws Exception{
		String usercode = request.getParameter("USERCODE"); 
		String userpswd = request.getParameter("USERPSWD");
		String userpswdC = request.getParameter("USERPSWD1");
		commandMap.put("USERCODE", usercode);
		commandMap.put("USERPSWD", userpswd);
		commandMap.put("USERPSWD1", userpswdC);
		
		System.out.println("commandMap="+commandMap.getMap().toString());
		
		//String password = (String) param.get("userPassword");
		// salt 생성
		java.util.Random random = new java.util.Random();
		byte[] saltBytes = new byte[8];
		random.nextBytes(saltBytes);
		
		StringBuffer salt = new StringBuffer();
		for (int i = 0; i < saltBytes.length; i++)
		{
			// byte 값을 Hex 값으로 바꾸기.
			salt.append(String.format("%02x",saltBytes[i]));
		}
		// param.put("salt", salt.toString());
		
		// Password와 랜덤으로 생성한 salt를 섞어서 SHA256 암호화를 한다.
		String encrypt = Sha256Util.getEncrypt(userpswd, saltBytes);
		String encryptC = Sha256Util.getEncrypt(userpswdC, saltBytes);
		
		System.out.println("encrpt [" + encrypt +"]");
		System.out.println("encrptC [" + encryptC +"]");
		
		// spring에서 재공하는 bcrypt알고리즘을 사용하여 암호화를 한다.
		BCryptPasswordEncoder encpwd = new BCryptPasswordEncoder();
		String encrypt2 = encpwd.encode(userpswd);
		String encryptC2 = encpwd.encode(userpswdC);
		System.out.println("encrypt2 [" + encrypt2 + "]");
		System.out.println("encryptC2 [" + encryptC2 + "]");
		
		// spring에서 재공하는 standard passwrod 알고리즘을 사용하여 암호화한다. 기본 값이 SHA256이다.
		StandardPasswordEncoder stencpwd = new StandardPasswordEncoder();
		String encrypt3 = stencpwd.encode(userpswd);
		String encryptC3 = stencpwd.encode(userpswdC);
		System.out.println("encrypt3 [" + encrypt3 + "]");
		System.out.println("encryptC3 [" + encryptC3 + "]");
		
		
		//stencpwd.matches(rawPassword, encodedPassword);
		
		log.debug("commandMap="+commandMap.getMap().toString());
		
		
		try{
			
			List<Map<String, Object>> resultInfo = (List<Map<String, Object>>) loginService.loginProc(commandMap.getMap());
			
			if(resultInfo.size() > 0){
				
				String s_usercode = "";
				String s_userpswd = "";
				
			    for (String mapkey : resultInfo.get(0).keySet()){
			        //System.out.println("key:"+mapkey+",value:"+resultInfo.get(0).get(mapkey));
			    	
			    	if(mapkey.equals("USERCODE")) s_usercode = resultInfo.get(0).get(mapkey).toString();
			    	if(mapkey.equals("USERPSWD")) s_userpswd = resultInfo.get(0).get(mapkey).toString();
			    	
			    }
			    
				/*
				 * 2016.01.29 비밀 번호컬럼의 암호화 진행이 되면 
				 * 비밀번호를 SHA256으로 암호화 하여 처리 하는 로직을 추가 해야함 
			     */
			    //로그인 성공
				//if(usrid.equals(s_usrid) && usrpw.equals(s_usrpw)){
			    
			    //비밀번호가 없을경우
			   if(s_userpswd.length() == 0){
			    	UserDTO sessionUser = new UserDTO();
			    	
			    	BeanUtils.populate(sessionUser, resultInfo.get(0));
			    	
			    	System.out.println("-------비밀번호없는경우------resultInfo.get(0)"+resultInfo.get(0).toString());
					
					HttpSession session = request.getSession();
			    	
					session.setAttribute("USER_INFO", sessionUser);
			    	
			    	//return "redirect:/domfpl/change_password.do";
			    	return "redirect:/index.do?login=C";
			    }
			    
			    
			    
			    
			    if(usercode.equals(s_usercode) &&
			       stencpwd.matches(userpswd, s_userpswd)){
			    	
			    	//UserDTO sessionUser = new UserDTO();
			    	
			    	//System.out.println("-------------resultInfo.get(0)"+resultInfo.get(0).toString());
			    	
			    	//BeanUtils.populate(sessionUser, resultInfo.get(0));
					
					//HttpSession session = request.getSession();
			    	
					//session.setAttribute("USER_INFO", sessionUser);
			    	try{
						commandMap.put("USERPSWD", encryptC3);
						commandMap.put("DIVCODE", "");
						commandMap.put("AUTHCODE", "");
						commandMap.put("USERNAME", "");
						commandMap.put("PSWDDATE", "");
						commandMap.put("HPNO", "");
						commandMap.put("ENGNAME", "");
						commandMap.put("POSNAME", "");
						commandMap.put("TEAMNAME", "");
						commandMap.put("REGUSER", "");
						commandMap.put("UPTCODE", "PW");

						loginService.passwordSave(commandMap.getMap());
						
						model.addAttribute("RESULT_CODE", "0000");
						model.addAttribute("RESULT_MESG", "SUCCESS");
						return "jsonView";
						
						
							
						
					}catch(Exception e){
						StackTraceElement[] elem = e.getStackTrace();log.debug(e.getMessage());
						
						for ( int i = 0; i < elem.length; i++ ){
							log.debug(elem[i].toString());
							System.out.println(elem[i]);
						}
						
					}
				
					
				}
				//로그인 실패
				//로그인 메시지를 추가 하여 JSP에서 로그인 유무 처리???
				else{
					
					return "redirect:/index.do?login=P";
				}
				
			}
			
		}catch(Exception e){
    		StackTraceElement[] elem = e.getStackTrace();log.debug(e.getMessage());
    		
			for ( int i = 0; i < elem.length; i++ ){
				log.debug(elem[i].toString());
				System.out.println(elem[i]);
			}
			
    	}
		
		return "redirect:/index.do?login=U";
		
	}
	@RequestMapping("/login/passwordSave.do")
	public String passwordSave(CommandMap commandMap, HttpServletRequest request, Model model) throws Exception{
	
		
		String usercode = request.getParameter("USERCODE"); 
		String userpswd = request.getParameter("USERPSWD");
		//String userpswdC = request.getParameter("USERPSWD1");
		commandMap.put("USERCODE", usercode);
		commandMap.put("USERPSWD", userpswd);
		
		System.out.println("passwordSave commandMap="+commandMap.getMap().toString());
		
		//String password = (String) param.get("userPassword");
		// salt 생성
		java.util.Random random = new java.util.Random();
		byte[] saltBytes = new byte[8];
		random.nextBytes(saltBytes);
		
		StringBuffer salt = new StringBuffer();
		for (int i = 0; i < saltBytes.length; i++)
		{
			// byte 값을 Hex 값으로 바꾸기.
			salt.append(String.format("%02x",saltBytes[i]));
		}
		// param.put("salt", salt.toString());
		
		// Password와 랜덤으로 생성한 salt를 섞어서 SHA256 암호화를 한다.
		String encrypt = Sha256Util.getEncrypt(userpswd, saltBytes);
		//String encryptC = Sha256Util.getEncrypt(userpswdC, saltBytes);
		
		System.out.println("encrpt [" + encrypt +"]");
		//System.out.println("encrpt [" + encryptC +"]");
		
		// spring에서 재공하는 bcrypt알고리즘을 사용하여 암호화를 한다.
		BCryptPasswordEncoder encpwd = new BCryptPasswordEncoder();
		String encrypt2 = encpwd.encode(userpswd);
		//String encryptC2 = encpwd.encode(userpswdC);
		System.out.println("encrypt2 [" + encrypt2 + "]");
		//System.out.println("encrypt2 [" + encryptC2 + "]");
		
		// spring에서 재공하는 standard passwrod 알고리즘을 사용하여 암호화한다. 기본 값이 SHA256이다.
		StandardPasswordEncoder stencpwd = new StandardPasswordEncoder();
		String encrypt3 = stencpwd.encode(userpswd);
		//String encryptC3 = stencpwd.encode(userpswdC);
		System.out.println("encrypt3 [" + encrypt3 + "]");
		//System.out.println("encrypt3 [" + encryptC3 + "]");
		
		
		//stencpwd.matches(rawPassword, encodedPassword);
		
		log.debug("commandMap="+commandMap.getMap().toString());
		try{
			log.debug("passwordSave  commandMap="+commandMap.getMap().toString());
			commandMap.put("USERPSWD", encrypt3);
			commandMap.put("DIVCODE", "");
			commandMap.put("AUTHCODE", "");
			commandMap.put("USERNAME", "");
			commandMap.put("PSWDDATE", "");
			commandMap.put("HPNO", "");
			commandMap.put("ENGNAME", "");
			commandMap.put("POSNAME", "");
			commandMap.put("TEAMNAME", "");
			commandMap.put("REGUSER", "");
			commandMap.put("UPTCODE", "PW");
			
			loginService.passwordSave(commandMap.getMap());
			
			model.addAttribute("RESULT_CODE", "0000");
			model.addAttribute("RESULT_MESG", "SUCCESS");
			return "jsonView";
			
			
			
			
		}catch(Exception e){
			StackTraceElement[] elem = e.getStackTrace();log.debug(e.getMessage());
			
			for ( int i = 0; i < elem.length; i++ ){
				log.debug(elem[i].toString());
				System.out.println(elem[i]);
			}
			
		}
		
		return "redirect:/index.do?login=U";
		
	}	
	@RequestMapping("/login/logout.do")
	public String logout(CommandMap commandMap, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		UserDTO sessionUser2 = (UserDTO) session.getAttribute("USER_INFO");
		HashMap<String, Object>loginMap = new HashMap<>();

		String ip=request.getRemoteAddr();
		if(ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")){
			InetAddress inetAddress=InetAddress.getLocalHost();
			ip = inetAddress.getHostAddress();
		}

		loginMap.put("USERID", sessionUser2.getUSERNAME());
		loginMap.put("IPADDRESS", ip);
		loginMap.put("SESSIONID", session.getId());

		loginDAO.insertLogoutLog(loginMap);

		session.invalidate();
		
		return "redirect:/index";
		
	}
	/**
     * Simply selects the home view to render by returning its name.
     */
//    @RequestMapping(value = "login", method = RequestMethod.GET)
//    public void login(HttpSession session) {
//    	//log.info("Welcome login! {}", session.getId());
//    	System.out.println("Welcome login! :" + session.getId());
//    }
//     
//    @RequestMapping(value = "logout", method = RequestMethod.GET)
//    public void logout(HttpSession session) {
//        CustomUserDetails userDetails = (CustomUserDetails)session.getAttribute("userLoginInfo");
//         
//        //log.info("Welcome logout! {}, {}", session.getId(), userDetails.getUsername());
//        System.out.println("Welcome logout! ["+session.getId() + "],["+userDetails.getUsername()+"]");
//         
//         
//        session.invalidate();
//    }
     
    @RequestMapping(value = "login_success", method = RequestMethod.GET)
    public void login_success(HttpSession session) {
        CustomUserDetails userDetails = (CustomUserDetails)SecurityContextHolder.getContext().getAuthentication().getDetails();
         
        //log.info("Welcome login_success! {}, {}", session.getId(), userDetails.getUsername() + "/" + userDetails.getPassword());
        System.out.println("Welcome login_success! ["+ session.getId() + "], [" +userDetails.getUsername()+ "], ["+userDetails.getPassword() +"]");
        session.setAttribute("userLoginInfo", userDetails);
    }
     
    @RequestMapping(value = "page1", method = RequestMethod.GET)
    public void page1() {      
    }
     
    @RequestMapping(value = "login_duplicate", method = RequestMethod.GET)
    public void login_duplicate() {    
    	log.info("Welcome login_duplicate!");
    }
	
    
    @RequestMapping("/usermenList.do")
	@ResponseBody
	public JSONObject usermenList(CommandMap commandMap, HttpServletRequest request) throws Exception{
		
		
		JSONObject json = new JSONObject();System.out.println("commandMap="+commandMap.getMap().toString());
		try{
			
			List<Map<String, Object>> resultList = (List<Map<String, Object>>) loginService.usermenList(commandMap.getMap());
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("rows", resultList); 
			
			json = JSONObject.fromObject(model);
			
			System.out.println("#####################"+resultList.size());
		
		}catch(Exception e){
    		StackTraceElement[] elem = e.getStackTrace();log.debug(e.getMessage());
    		
			for ( int i = 0; i < elem.length; i++ )
				log.debug(elem[i].toString());
			
			
    	}
		
		return json;
		
	}

	@RequestMapping("/usermenDup.do")
	@ResponseBody
	public JSONObject usermenDup(CommandMap commandMap, HttpServletRequest request) throws Exception{
		
		
		JSONObject json = new JSONObject();log.debug("commandMap="+commandMap.getMap().toString());
		try{
			
			List<Map<String, Object>> resultList = (List<Map<String, Object>>) loginService.usermenDup(commandMap.getMap());
			
			Map<String, Object> model = new HashMap<String, Object>();
			
			model.put("rows", resultList); 
			
			json = JSONObject.fromObject(model);
			
			log.debug("#####################"+resultList.size());
		
		}catch(Exception e){
    		StackTraceElement[] elem = e.getStackTrace();log.debug(e.getMessage());
    		
			for ( int i = 0; i < elem.length; i++ )
				log.debug(elem[i].toString());
			
			
    	}
		
		return json;
		
	}
	
	
	@RequestMapping("/usermenDel.do")
	@ResponseBody
	public JSONObject usermenDel(CommandMap commandMap, HttpServletRequest request) throws Exception{
		
		JSONObject json = new JSONObject();log.debug("commandMap="+commandMap.getMap().toString());
		
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("USERCODE", CommonUtils.NullToEmpty(request.getParameter("USERCODE")));
		loginService.usermenDel(param);
		return json;
		
	}

	
	@RequestMapping("/usermenUpt.do")
	@ResponseBody
	public JSONObject usermenUpt(CommandMap commandMap, HttpServletRequest request) throws Exception{
		
		//String userpswd = request.getParameter("USERPSWD");
		
		//StandardPasswordEncoder stencpwd = new StandardPasswordEncoder();
		//String encrypt3 = stencpwd.encode(userpswd);
		//System.out.println("encrypt3 [" + encrypt3 + "]");
		
		JSONObject json = new JSONObject();log.debug("commandMap="+commandMap.getMap().toString());
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("USERCODE", CommonUtils.NullToEmpty(request.getParameter("USERCODE")));
		param.put("DIVCODE", CommonUtils.NullToEmpty(request.getParameter("DIVCODE")));
		param.put("AUTHCODE", CommonUtils.NullToEmpty(request.getParameter("AUTHCODE")));
		param.put("USERNAME", CommonUtils.NullToEmpty(request.getParameter("USERNAME")));
		param.put("USERPSWD", "");
		param.put("PSWDDATE", CommonUtils.NullToEmpty(request.getParameter("PSWDDATE")));
		param.put("HPNO", CommonUtils.NullToEmpty(request.getParameter("HPNO")));
		param.put("ENGNAME", CommonUtils.NullToEmpty(request.getParameter("ENGNAME")));
		param.put("POSNAME", CommonUtils.NullToEmpty(request.getParameter("POSNAME")));
		param.put("TEAMNAME", CommonUtils.NullToEmpty(request.getParameter("TEAMNAME")));
		
		param.put("REGUSER", CommonUtils.getUserId(request));
		param.put("UPTCODE", "UR");
		
		
		loginService.usermenUpt(param);
		
		
		return json;
		//return dataManagementService.aircraftUpt(commandMap.getMap());
		
	}
	
	@RequestMapping("/login/passwordReset.do")
	@ResponseBody
	public JSONObject passwordReset(CommandMap commandMap, HttpServletRequest request) throws Exception{
		
		
		
		JSONObject json = new JSONObject();log.debug("commandMap="+commandMap.getMap().toString());
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("USERCODE", CommonUtils.NullToEmpty(request.getParameter("USERCODE")));
		
		
		loginService.passwordReset(param);
		
		
		return json;
		//return dataManagementService.aircraftUpt(commandMap.getMap());
		
	}
    

	///////////////////////////////모바일/////////////////////////////////////
	
	@RequestMapping("/login/loginProcMobile.do")
	public String loginProcMobile(CommandMap commandMap, HttpServletRequest request, Model model) throws Exception{
		
		String usercode = request.getParameter("USERCODE"); 
        String userpswd = request.getParameter("USERPSWD");
        String errcnt = request.getParameter("ERRCNT");
        String auth_seq = (String) commandMap.get("AUTH_SEQ");
        
        log.debug("commandMap="+commandMap.getMap().toString());
        
        //String password = (String) param.get("userPassword");
        // salt 생성
		java.util.Random random = new java.util.Random();
		byte[] saltBytes = new byte[8];
		random.nextBytes(saltBytes);
		
		StringBuffer salt = new StringBuffer();
		for (int i = 0; i < saltBytes.length; i++)
		{
		    // byte 값을 Hex 값으로 바꾸기.
			salt.append(String.format("%02x",saltBytes[i]));
		}
		// param.put("salt", salt.toString());
		
		// Password와 랜덤으로 생성한 salt를 섞어서 SHA256 암호화를 한다.
		String encrypt = Sha256Util.getEncrypt(userpswd, saltBytes);
		
		System.out.println("encrpt [" + encrypt +"]");
		
		// spring에서 재공하는 bcrypt알고리즘을 사용하여 암호화를 한다.
		BCryptPasswordEncoder encpwd = new BCryptPasswordEncoder();
		String encrypt2 = encpwd.encode(userpswd);
		System.out.println("encrypt2 [" + encrypt2 + "]");
		
		// spring에서 재공하는 standard passwrod 알고리즘을 사용하여 암호화한다. 기본 값이 SHA256이다.
		StandardPasswordEncoder stencpwd = new StandardPasswordEncoder();
		String encrypt3 = stencpwd.encode(userpswd);
		System.out.println("encrypt3 [" + encrypt3 + "]");
		
		
		//stencpwd.matches(rawPassword, encodedPassword);
		
		System.out.println("commandMap="+commandMap.getMap().toString());
        String rtn = "";
		try{
			
			List<Map<String, Object>> resultInfo = (List<Map<String, Object>>) loginService.loginProcMobile(commandMap.getMap());
			
			if ((resultInfo == null) || (resultInfo.size()<= 0)){
//				return "redirect:/mobile/index.do?login=C";
				
				commandMap.put("ERRCNT", errcnt);
				model.addAttribute("LOGINM", commandMap.getMap() );
				System.out.println("resultInfo is null goto URL:"+"/mobile/loginDR");
//				return "/mobile/loginDR";
				return "/mobile/loginD";
			}
			
			model.addAttribute("loginInfo",resultInfo.get(0).toString());
			model.addAttribute("loginTest",resultInfo); //비밀번호 2전째인증
			
			if(resultInfo.size() > 0){
				
				String s_usercode = "";
				String s_userpswd = "";
				
			    for (String mapkey : resultInfo.get(0).keySet()){
			        //System.out.println("key:"+mapkey+",value:"+resultInfo.get(0).get(mapkey));
			    	
			    	if(mapkey.equals("USERCODE")) s_usercode = resultInfo.get(0).get(mapkey).toString();
			    	if(mapkey.equals("USERPSWD")) s_userpswd = resultInfo.get(0).get(mapkey).toString();
			    }
			   
			    //로그인 성공
				//if(usrid.equals(s_usrid) && usrpw.equals(s_usrpw)){
			    
			    //비밀번호가 없을경우
			    if(s_userpswd.length() == 0){
			    	UserDTO sessionUser = new UserDTO();
			    	resultInfo.get(0).put("LOGIN", "N");

			    	BeanUtils.populate(sessionUser, resultInfo.get(0));
			    	
			    	System.out.println("------비밀번호없는경우-@@------resultInfo.get(0)"+resultInfo.get(0).toString());
					
					HttpSession session = request.getSession();
			    	
					session.setAttribute("USER_INFO", sessionUser);
			    	
			    	//return "redirect:/domfpl/change_password.do";
					commandMap.put("ERRCNT", errcnt);
					model.addAttribute("LOGINM", commandMap.getMap() );
					System.out.println("resultInfo is null goto URL:"+"/mobile/loginDR");
					
//					return "/mobile/loginDR";
					return "/mobile/loginD";
//			    	return "redirect:/mobile/index.do?login=C";
			    } 
			    
			    if(usercode.equals(s_usercode) && stencpwd.matches(userpswd, s_userpswd)){
			    	
			    	UserDTO sessionUser = new UserDTO();
			    	sessionUser.setAUTH_SEQ(auth_seq);
			    	resultInfo.get(0).put("LOGIN", "Y");
			    	System.out.println("---모바일LOGIN=Y----------resultInfo.get(0)"+resultInfo.get(0).toString());
			    	
			    	BeanUtils.populate(sessionUser, resultInfo.get(0));
					
					HttpSession session = request.getSession();
					session.setAttribute("USER_INFO", sessionUser);
					 
					String DIVCODE = sessionUser.getDIVCODE();
					String LOGIN=sessionUser.getLOGIN();
					String AUTHCODE = sessionUser.getAUTHCODE();
					System.out.println("+==========================="+DIVCODE+":"+LOGIN+":"+AUTHCODE+":");
				 	if(LOGIN=="Y" && DIVCODE=="FUEL"||AUTHCODE=="FUEL"){
						rtn = "/mobile/fuelLogin";
					}else if("Y".equals(LOGIN) && "CREW".equals(DIVCODE)||"CREW".equals(AUTHCODE)){
						rtn =  "/mobile/wbsList";
					}else if("Y".equals(LOGIN) && "ADMIN".equals(DIVCODE)||"ADMIN".equals(AUTHCODE)){						
						rtn =  "/mobile/wbsList";
					}else if("Y".equals(LOGIN) && "DEICING".equals(DIVCODE)||"DEICING".equals(AUTHCODE)){						
						rtn =  "/mobile/deicingLogin";		
					}else if("Y".equals(LOGIN) && "CATERING".equals(DIVCODE)||"CATERING".equals(AUTHCODE)){
						rtn =  "/mobile/cateringLogin";
					}else if("Y".equals(LOGIN) && "CLEANING".equals(DIVCODE)||"CLEANING".equals(AUTHCODE)){
						rtn = "/mobile/cleaningLogin";
					}else if("Y".equals(LOGIN) &&  "".equals(DIVCODE)){
						rtn = "/mobile/loginDR";
					}

				 	System.out.println(">>>>>>>>>>>>>>>>>>>>>>> goto Final URL:"+rtn);
					return rtn;
				}  else {//로그인 실패
					commandMap.put("ERRCNT", errcnt);
					model.addAttribute("LOGINM", commandMap.getMap() );
					System.out.println("resultInfo is null goto URL:"+"/mobile/loginDR");
					return "/mobile/loginD";
//					return "/mobile/loginDR";
//					return "redirect:/mobile/index.do?login=P";
				}
				
			}
			
		}catch(Exception e){
    		StackTraceElement[] elem = e.getStackTrace();log.debug(e.getMessage());
    		
			for ( int i = 0; i < elem.length; i++ ){
				log.debug(elem[i].toString());
				System.out.println(elem[i]);
			}
			
    	}

		return "redirect:/mobile/index.do?login=U";
		
	}	
	
//모바일로그아웃
	@RequestMapping("/login/logoutMobile.do")
	public String logoutMobile(CommandMap commandMap, HttpServletRequest request) throws Exception{
		HttpSession session = request.getSession();
		session.invalidate();
		
		return "redirect:/mobile/index.do";
		
	}
    
	//1.모바일 admin로그인화면
	@RequestMapping("/mobile/adminLogin.do")
	public String getProWorkMobile(HttpServletRequest request, Model model) throws Exception{
		
		return "/mobile/adminLogin";
	}
	
	//2.모바일 crew로그인화면
	@RequestMapping("/mobile/crewLogin.do")
	public String getcrewLoginMobile(HttpServletRequest request, Model model) throws Exception{

		return "/mobile/crewLogin";	
	}
	
	//3.모바일 fuel로그인화면	
	@RequestMapping("/mobile/fuelLogin.do")
	public String getfuelLoginMobile(HttpServletRequest request, Model model) throws Exception{

		return "/mobile/fuelLogin";	
	}

	//4.모바일 cleaning로그인화면		
	@RequestMapping("/mobile/cleaningLogin.do")
	public String getcleaningLoginMobile(HttpServletRequest request, Model model) throws Exception{

		return "/mobile/cleaningLogin";	
	}
	
	//5.모바일 deicing로그인화면		
	@RequestMapping("/mobile/deicingLogin.do")
	public String getdeicingLoginMobile(HttpServletRequest request, Model model) throws Exception{
		
		return "/mobile/deicingLogin";	
	}
	//6.모바일 catering로그인화면		
	@RequestMapping("/mobile/cateringLogin.do")
	public String getcateringLoginMobile(HttpServletRequest request, Model model) throws Exception{

		return "/mobile/cateringLogin";	
	}
}
