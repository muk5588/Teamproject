package user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import user.dao.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Service
public class Userservice {

    @Autowired
    UserDao dao;


    public void getuserList(HttpServletRequest request, Model model)throws Exception {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> reqMap = (HashMap<String, Object>) model.asMap().get("pageMap");

        model.addAttribute("rows", dao.getUserList(reqMap));
    }
    public int getuserListCount(HttpServletRequest request, Model model)throws Exception {
        @SuppressWarnings("unchecked")

        HashMap<String, Object> reqMap = (HashMap<String, Object>) model.asMap().get("pageMap");
        return dao.getuserListCount(reqMap);
    }
    public void saveUser(HttpServletRequest request, Model model) throws Exception {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> reqMap = (HashMap<String, Object>) model.asMap().get("pageMap");

        reqMap.put("REGUSER", common.CommonUtils.getUserId(request));


        model.addAttribute("RESULT_CODE", "0000");
        model.addAttribute("RESULT_MESG", "SUCCESS");

        if (null == reqMap.get("CMD_TYPE") || !"INSERT".equals(reqMap.get("CMD_TYPE").toString())) {
            dao.updateUser(reqMap);
            model.addAttribute("RESULT", "SUCCESS");

        } else {
            //신규에서 저장 버튼 클릭시 기존 사용자코드가 존재 할 경우
            if (null != dao.findUserId(reqMap)) {
                model.addAttribute("RESULT", "pk");
            } else {
                String userpswd = (String) reqMap.get("USERCODE");
                System.out.println("userpswd" + userpswd);
                reqMap.put("USERPSWD", userpswd);
                dao.CreateUser(reqMap);
                model.addAttribute("RESULT", "SUCCESS");
            }
        }
    }
    public void deleteUser(HttpServletRequest request, Model model) throws Exception{
        @SuppressWarnings("unchecked")
        HashMap<String, Object> reqMap = (HashMap<String, Object>) model.asMap().get("pageMap");

        dao.deleteUser(reqMap);
        //userDao.deleteUserRole(reqMap);
        //userDao.deleteMenu(reqMap); //사용자 삭제

        model.addAttribute("RESULT", "SUCCESS");
    }
}
