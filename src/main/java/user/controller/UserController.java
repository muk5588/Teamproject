package user.controller;

import board.dto.Board;
import board.service.BoardService;
import grade.dto.Grade;
import grade.service.GradeService;
import login.dto.AccessHistory;
import login.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import user.dto.EmailCheck;
import user.dto.User;
import user.service.UserService;
import util.Paging;
import util.UserPaging;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private UserService service;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private BoardService boardService;
    /**
     * 화면부분
     * */
    //관리자페이지
    @RequestMapping("/adminPage")
    public String adminPage(Model model, @RequestParam(defaultValue ="0") int curPage){
    	String URL = "/user/adminPage";
        Paging paging = new Paging();
        paging = boardService.getAdminPaging(curPage, paging);
        logger.debug("admin paging : {}", paging);
        
        List<AccessHistory> list2  = loginService.loginHistory(paging);
        logger.debug("AccessHistory : {}", list2);
        List<Grade> list3 = gradeService.gradeList();
        logger.debug("Grade : {}", list3);
        logger.debug("admin paging : {}", paging);
        model.addAttribute("URL", URL);
        model.addAttribute("list2", list2);
        model.addAttribute("list3", list3);
        model.addAttribute("paging", paging);
        return "user/adminPage";
    }
    @RequestMapping("/insertUser")
    //가입페이지
    public String userInsert() {
        return "user/userInsert";
    }

    @RequestMapping("/userList")
    //유저전체리스트 페이지
    public String userList( Model model,@RequestParam(defaultValue ="0") int curPage
    		,@RequestParam(required = false,name = "searchKind") String searchKind
    		,@RequestParam(required = false, name = "search") String search) {
    	String url = "/user/userList";
    	UserPaging paging = new UserPaging();
    	if( search != null && searchKind != null ) {
    		logger.debug("@!#!@@@@@@");
    		paging.setSearch(search);
    		paging.setSearchKind(searchKind);
    		logger.debug("paging1 : {}", paging);
    	}
    	paging = service.getUserListPaging(paging,curPage);
    	if( search != null && searchKind != null ) {
    		paging.setSearch(search);
    		paging.setSearchKind(searchKind);
    		logger.debug("paging3 : {}", paging);
    	}
        List<User> list = service.userPagingList(paging);
        model.addAttribute("list", list);
        model.addAttribute("paging", paging);
        model.addAttribute("URL", url);
        return url;
    }

    @RequestMapping("/userBlack")
    //유저블랙리스트 페이지
    public String userBlack( Model model,@RequestParam(defaultValue ="0") int curPage
    		,@RequestParam(required = false,name = "searchKind") String searchKind
    		,@RequestParam(required = false, name = "search") String search) {
    	String URL = "/user/userBlack";
    	UserPaging paging = new UserPaging();
    	logger.debug("@!#!@");
    	if( search != null && searchKind != null ) {
    		logger.debug("@!#!@@@@@@");
    		paging.setSearch(search);
    		paging.setSearchKind(searchKind);
    		logger.debug("paging1 : {}", paging);
    	}
    	logger.debug("@!#!@@@@@@####");
    	paging = service.getUserListPaging(paging,curPage);
    	logger.debug("paging2 : {}", paging);
    	if( search != null && searchKind != null ) {
    		paging.setSearch(search);
    		paging.setSearchKind(searchKind);
    		logger.debug("paging3 : {}", paging);
    	}
    	List<User> list = service.userPagingList(paging);
    	logger.debug("paging4 : {}", paging);
    	logger.debug("list : {}", list);
        model.addAttribute("list", list);
        model.addAttribute("paging", paging);
        model.addAttribute("URL", URL);
        return URL;
    }
    
    //수정 페이지
    @RequestMapping("/updateUser")
    public String userUpdate(int userno, Model model) {
        User dto = new User();
        dto.setUserno(userno);
        dto = service.userDetail(dto);
//        int userno = dto.getUserno();
        model.addAttribute("dto", dto);
        return "user/userUpdate";
    }
    //유져상세페이지(관리자용)
    @RequestMapping("/detailUser")
    public String userDetail(int userno, Model model, HttpSession session) {
        //선택한 고객 정보를 DB에 조회해와서
        User dto = new User();
        dto.setUserno(userno);
        dto = service.userDetail(dto);
        model.addAttribute("dto", dto);
        User login = (User) session.getAttribute("dto1");
        model.addAttribute("dto1", login);
        List<Board> list = boardService.boardList(userno);
        model.addAttribute("userno", userno);
        model.addAttribute("list", list);

//        int userno = dto.getUserno();
        //화면에 출력할 수 있도록 Model에 담는다.
        //원래는 string타입으로 담겨야하지만 스프링에서는 자동으로 형변환이 되서 int타입으로 담긴다.


        return "user/userDetail";
    }
    @RequestMapping("/searchUser")
    public String searchid(String value,Model model) {
        model.addAttribute("value", value);
        return "user/userSearch";
    }
    @RequestMapping("/updatePass")
    public String updatePass(int userno, Model model) {
        User dto = new User();
        dto.setUserno(userno);
        dto = service.userDetail(dto);
        model.addAttribute("dto", dto);
        return "user/updatePassword";
    }

    /**
     * 기능부분
     * param: UserDTO
     * */
    @RequestMapping("/userInsert")
    public String userInsert(User dto) {
        service.userInsert(dto);
        return "redirect: /";
    }

    @RequestMapping("/userUpdate")
    public String userUpdate(User dto) {
        service.userUpdate(dto);
        return "redirect: ./userDetail";
    }

	@PostMapping("/userBlack") 
    public String userBlack(@RequestParam List<String> userNos) {
		for(int i=0; i<userNos.size(); i++){
            Long no = Long.valueOf(userNos.get(i));
            service.blackUser(no);
        }
		return "redirect: /user/userBlack";
    }
    
	@PostMapping("/userWhite") 
	public String userWhite(@RequestParam List<String> userNos) {
		for(int i=0; i<userNos.size(); i++){
			Long no = Long.valueOf(userNos.get(i));
			service.whiteUser(no);
		}
		return "redirect: /user/userBlack";
	}
	
    @RequestMapping("/deleteUser")
    public String deleteUser(User dto, HttpSession session) {
        service.userDelete(dto);
        session.invalidate();
        return "redirect: /";
    }

    @ResponseBody
    @RequestMapping("/idChk")
    public int idChk(User dto) throws Exception {
        int res = service.idChk(dto);
        return res;
    }

    @RequestMapping("/idckeck")
    public String idckeck(User dto) throws Exception {
        int res = service.idChk(dto);
        try {
            if (res == 1) {
                return "redirect: /user/insertUser";
            }else if (res == 0) {
                service.userInsert(dto);
            }
        }catch (Exception e) {
            throw new Exception();
        }
        return "redirect: /";
    }
    @ResponseBody
    @PostMapping("/nickChk")
    public int nickChk(User dto) {
    	int res = service.nickChk(dto);

        return res;
    	
    }
    @PostMapping("/checkEmail")
	public @ResponseBody EmailCheck checkEmail (String email , Model model){
		int num =  service.checkEmail(email);
		return new EmailCheck(email,num);
	}
    @RequestMapping("/searchId")
    public String searchId(HttpServletRequest request, Model model, User dto,
                           @RequestParam String name,
                           @RequestParam String email) {
        try {
            dto.setName(name);
            dto.setEmail(email);
            User userid = service.findUserId(dto);
            model.addAttribute("finduserid", userid);
            String value = "id";
            model.addAttribute("value", value);

        } catch (Exception e) {
            model.addAttribute("msg", "오류가 발생되었습니다.");
            e.printStackTrace();
        }
        return "user/findResult";
    }
    @RequestMapping("/searchPw")
    public String searchPw(HttpServletRequest request, Model model,
                               @RequestParam String userid2, @RequestParam String name2,@RequestParam String email2,
                               User dto) {
        try {
            dto.setUserid(userid2);
            dto.setName(name2);
            dto.setEmail(email2);
            User search = service.findUserpw(dto);

            if(search == null) {
                model.addAttribute("msg", "기입된 정보가 잘못되었습니다. 다시 입력해주세요.");
            }

            String newPwd = UUID.randomUUID().toString().split("-")[4] + "!!";
            dto.setUserpw(newPwd);
            service.updateUserpw(dto);
            model.addAttribute("newPwd", newPwd);
            String value = "pw";
            model.addAttribute("value", value);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "오류가 발생되었습니다.");
        }
        return "user/findResult";
    }
    @RequestMapping("/userPass")
    public String userPass(HttpServletRequest request, HttpSession session) {
        User dto = new User();
        dto.setName(request.getParameter("name"));
        dto.setEmail(request.getParameter("email"));
        dto.setUserid(request.getParameter("userid"));
        dto.setUserpw(request.getParameter("userpw"));
        service.updateUserpw(dto);
        session.invalidate();
        return "redirect: /";
    }
    @RequestMapping("/userLog")
    public String userLog(Model model,@RequestParam(defaultValue ="0") int curPage){
        UserPaging paging = new UserPaging();
        String URL = "/user/userLog";

        paging = boardService.getLogPaging(curPage,paging);


        List<AccessHistory> list = loginService.history(paging);
        model.addAttribute("list",list);
        model.addAttribute("paging", paging);
        model.addAttribute("URL", URL);
        return "user/userLog";
    }
    
}
