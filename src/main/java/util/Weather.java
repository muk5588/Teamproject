package util;

import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import user.dto.User;
import util.service.GeoCoding;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
public class Weather {
    @Autowired
    private GeoCoding geoCoding;
    private Logger logger = LoggerFactory.getLogger(getClass());


    @GetMapping("/weather")
    public String getWeather(Model model, HttpSession session) throws IOException, ParseException {
        String x = null;
        String y = null;
        User user = (User) session.getAttribute("dto1");
        if (user == null) {
            x = "126.98322015";
            y = "37.57023746";
            model.addAttribute("x",x);
            model.addAttribute("y",y);
        }else {
            String address = user.getAddress();
            HashMap<String, String> XYMap = geoCoding.getXYMapfromJson(geoCoding.getKakaoApiFromAddress(address));
            x = XYMap.get("x");   //위도
            y = XYMap.get("y");   //경도
            model.addAttribute("x",x);
            model.addAttribute("y",y);
        }

        return "layout/weather";
    }
}
