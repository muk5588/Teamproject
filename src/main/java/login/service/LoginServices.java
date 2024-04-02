package login.service;


import user.dto.UserDTO;
import login.dao.LoginDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service
public class LoginServices implements UserDetailsService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LoginDAO DAO;

    @Autowired
    LoginService loginService;
    

    @Autowired(required = false)
    private HttpServletRequest request;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HashMap<String, Object> filters = new HashMap<>();
        filters.put("username", username);
        filters.put("userType", request.getParameter("userType"));
//        HashMap<String, Object> userDetails = DAO.selectUserByName(filters);
//        if (userDetails != null) {
        return User.builder().username("ADMIN")
                .password("bc038a01ecaaaffb7e40e3d1c506b804423713f5fcd78a699c518ccc5127f2b4de71e96e5c31e9a4")
                .authorities(new SimpleGrantedAuthority("ADMIN"))
                .build();
//        }
//        return null;
    }

    public MultipleAuthentication loginAuthentication(String userName, String password, String sessionId) {
        List<GrantedAuthority> auth = new ArrayList<>();
        CommandMap commandMap = new CommandMap();
        commandMap.put("USERCODE", userName);
        commandMap.put("USERPSWD", password);

        Random random = new Random();
        byte[] saltBytes = new byte[8];
        random.nextBytes(saltBytes);
        StringBuffer salt = new StringBuffer();
        for (byte saltByte : saltBytes) {
            salt.append(String.format("%02x", saltByte));
        }
        String encrypt = Sha256Util.getEncrypt(password, saltBytes);
        BCryptPasswordEncoder encpwd = new BCryptPasswordEncoder();
        String encrypt2 = encpwd.encode(password);
        StandardPasswordEncoder stencpwd = new StandardPasswordEncoder();
        String encrypt3 = stencpwd.encode(password);
        try {
            List<Map<String, Object>> resultInfo = loginService.loginProc(commandMap.getMap());

            List<Map<String, Object>> pass = loginService.passCh(commandMap.getMap());
            if (resultInfo.size() > 0) {
                String s_usercode = "", s_userpswd = "", s_authcode = "ROLL_NULL";
                if (resultInfo.get(0).get("USERPSWD") == null) {
                    UserDTO sessionUser = new UserDTO();
                    resultInfo.get(0).put("LOGIN", "N");
                    BeanUtils.populate(sessionUser, resultInfo.get(0));
                    System.out.println("-------비밀번호없는경우------resultInfo.get(0)" + resultInfo.get(0).toString());
                    HttpSession session = request.getSession();
                    session.setAttribute("USER_INFO", sessionUser);
//                    return "redirect:/index?login=C";
                }
                for (String mapkey : resultInfo.get(0).keySet()) {
                    if (mapkey.equals("USERCODE")) s_usercode = resultInfo.get(0).get(mapkey).toString();
                    if (mapkey.equals("USERPSWD")) s_userpswd = resultInfo.get(0).get(mapkey).toString();
                    if (mapkey.equals("AUTHCODE")) s_authcode = resultInfo.get(0).get(mapkey).toString();
                }
                if (userName.equals(s_usercode) && stencpwd.matches(password, s_userpswd)) {
                    List<Map<String, Object>> resultInfo2 = loginService.getloginMenuList(commandMap.getMap());
                    UserDTO sessionUser = new UserDTO();
                    resultInfo.get(0).put("LOGIN", "Y");
                    sessionUser.setMENULIST(resultInfo2);
                    BeanUtils.populate(sessionUser, resultInfo.get(0));
                    HttpSession session = request.getSession();
                    session.setAttribute("USER_INFO", sessionUser);
                    UserDTO sessionUser2 = (UserDTO) session.getAttribute("USER_INFO");
                    HashMap<String, Object> loginMap = new HashMap<>();
                    String ip = request.getRemoteAddr();
                    if (ip.equalsIgnoreCase("0:0:0:0:0:0:0:1")) {
                        InetAddress inetAddress = InetAddress.getLocalHost();
                        ip = inetAddress.getHostAddress();
                    }

                    loginMap.put("USERID", sessionUser2.getUSERNAME());
                    loginMap.put("IPADDRESS", ip);
                    loginMap.put("SESSIONID", session.getId());

                    Calendar cal = Calendar.getInstance();
                    String Year = Integer.toString(cal.get(Calendar.YEAR));
                    String Month = Integer.toString(cal.get(Calendar.MONTH) + 1);
                    String Date = Integer.toString(cal.get(Calendar.DATE));
                    if (Month.length() == 1) {
                        Month = '0' + Month;
                    }
                    if (Date.length() == 1) {
                    	Date = '0' + Date;
                    }
                    String abc = Year + Month + Date;
                    String passDate, passInt;
                    if (resultInfo.get(0).get("PSWDDATE") == null) {
                        passDate = abc;
                        passInt = "0";
                    } else {
                        passDate = resultInfo.get(0).get("PSWDDATE").toString();
                        passInt = pass.get(0).get("CDNAME1").toString();
                    }

                    String CDNAME1 = DateUtil.getDateString(DateUtil.addDate(passDate, Integer.parseInt(passInt)), "yyyyMMdd");
                    if (Integer.parseInt(abc) >= Integer.parseInt(CDNAME1)) {
                        auth.add(new SimpleGrantedAuthority("ROLE_NULL"));
                        return new MultipleAuthentication(false, auth);
                    } else {
                        auth.add(new SimpleGrantedAuthority("ROLE_" + s_authcode));
                        return new MultipleAuthentication(true, auth);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        auth.add(new SimpleGrantedAuthority("ROLE_NULL"));
        return new MultipleAuthentication(false, auth);
    }

//        try {
//            filters.put("username", userName);
//            filters.put("userType", userType);
//            HashMap<String, Object> userDetails = DAO.selectUserByName(filters);
//            if (userDetails != null) {
//                if (getEncrypt(password, userDetails.get("LOGIN_ID").toString()).equals(userDetails.get("LOGIN_PASSWORD").toString())) {
//                    HashMap<String, Object> dataLog = new HashMap<>();
//                    dataLog.put("USER_ID", userDetails.get("ID").toString());
//                    dataLog.put("IP_ADDRESS", getIpAddress());
//                    dataLog.put("SESSION_ID", sessionId);
//                    auth.add(new SimpleGrantedAuthority(userDetails.get("USER_ROLE").toString()));
//                    dataLog.put("ROLE_ID", userDetails.get("USER_ROLE").toString());
//                    DAO.insertUserLog(dataLog);
//                    return new MultipleAuthentication(true, auth);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    public void saveLogoutLog(String UserName, String SessionId) throws Exception {
        try {
            HashMap<String, Object> filters = new HashMap<>();
            HashMap<String, Object> dataLogOutLog = new HashMap<>();

            filters.put("username", UserName);

            HashMap<String, Object> userDetails = DAO.selectUserByName(filters);

            dataLogOutLog.put("USER_ID", UserName);
            dataLogOutLog.put("IP_ADDRESS", getIpAddress());
            dataLogOutLog.put("SESSION_ID", SessionId);
            dataLogOutLog.put("ROLE_ID", userDetails.get("USER_ROLE").toString());

//            logger.debug("SASDASDASD   :::::::" + dataLogOutLog);

            DAO.insertLogOutLog(dataLogOutLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUserEventLog(String requestCode, String requestMethod, String requestURL, String SessionId, String userName) {
        try {
            if (!userName.equals("anonymousUser") && !requestCode.equals("404")) {
                HashMap<String, Object> filters = new HashMap<>();
                HashMap<String, Object> dataEventLog = new HashMap<>();

                filters.put("username", userName);

                HashMap<String, Object> userDetails = DAO.selectUserByName(filters);

                dataEventLog.put("ROLE_ID", userDetails.get("USER_ROLE").toString());

                dataEventLog.put("USER_ID", userName);
                dataEventLog.put("SESSION_ID", SessionId);
                dataEventLog.put("REQUEST_CODE", Integer.parseInt(requestCode));
                dataEventLog.put("REQUEST_METHOD", requestMethod);
                dataEventLog.put("REQUEST_URL", requestURL);

                logger.debug("SASDASDASD   :::::::" + dataEventLog);


                DAO.insertUserEventLog(dataEventLog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getIpAddress() throws Exception {
        String systemIpAddress = "";
        try {
            URL url_name = new URL("https://api64.ipify.org/");
            BufferedReader sc = new BufferedReader(new InputStreamReader(url_name.openStream()));
            systemIpAddress = sc.readLine().trim();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return systemIpAddress;
    }

    public String getEncrypt(String source, String salt) {
        return getEncrypt(source, salt.getBytes());
    }

    public String getEncrypt(String source, byte[] salt) {
        String result = "";
        byte[] a = source.getBytes();
        byte[] bytes = new byte[a.length + salt.length];
        System.arraycopy(a, 0, bytes, 0, a.length);
        System.arraycopy(salt, 0, bytes, a.length, salt.length);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bytes);
            byte[] byteData = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte byteDatum : byteData) {
                sb.append(Integer.toString((byteDatum & 0xFF) + 256, 16).substring(1));
            }
            result = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    public String generateSalt() {
        Random random = new Random();
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        StringBuilder sb = new StringBuilder();
        for (byte b : salt) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}

