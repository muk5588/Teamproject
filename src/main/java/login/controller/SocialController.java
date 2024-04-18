package login.controller;

import login.service.SocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SocialController {
    @Autowired
    private SocialService socialService;

    @RequestMapping("/login/kakaoLogin")
    public String kakaoLogin(@RequestParam("code") String code) {
        return null;
    }


}
