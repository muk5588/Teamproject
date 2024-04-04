package user.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import user.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Service
public class UserService {

    private UserDao userDao = new UserDao();

    /**
     * 사용자 ID 중복체크 카운트
     *
     * @param request
     * @param model
     * @throws Exception
     */
    public int getUserListCount(HttpServletRequest request, Model model) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> reqMap = (HashMap<String, Object>) model.asMap().get("pageMap");

        return userDao.getUsrListCount(reqMap);
    }

    /**
     * 사용자 목록 조회
     *
     * @param request
     * @param model
     * @throws Exception
     */
    public void userList(HttpServletRequest request, Model model) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> reqMap = (HashMap<String, Object>) model.asMap().get("pageMap");
        model.addAttribute("rows", userDao.userList(reqMap));

    }

    /**
     * 사용자 가입
     *
     * @param request
     * @param model
     * @throws Exception
     */
    public void insertUser(HttpServletRequest request, Model model) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> reqMap = (HashMap<String, Object>) model.asMap().get("pageMap");

        userDao.insertUser(reqMap);
        model.addAttribute("RESULT", "SUCCESS");
    }

    /**
     * 사용자 업데이트
     *
     * @param request
     * @param model
     * @throws Exception
     */
    public void updateUser(HttpServletRequest request, Model model) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> reqMap = (HashMap<String, Object>) model.asMap().get("pageMap");
        userDao.selectUserId(reqMap);
        userDao.updateUser(reqMap);
        model.addAttribute("RESULT", "SUCCESS");

    }


    /**
     * 사용자 삭제
     *
     * @param request
     * @param model
     */
    public void deleteUser(HttpServletRequest request, Model model) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> reqMap = (HashMap<String, Object>) model.asMap().get("pageMap");
        userDao.deleteUser(reqMap);
        model.addAttribute("RESULT", "SUCCESS");
    }

    public void getBoardList(HttpServletRequest request, Model model) {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> reqMap = (HashMap<String, Object>) model.asMap().get("pageMap");
        model.addAttribute("rows", userDao.userBoardList(reqMap));

    }
}



