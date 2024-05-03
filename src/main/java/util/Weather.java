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
    private String sky, temperature, rain, snow, humidity;
    private String current_weather_code;
    // 현재 날짜/시간
    LocalDateTime t = LocalDateTime.now().minusMinutes(30);

    enum WeatherValue {
        T1H, RN1, SKY, UUU, VVV, REH, PTY, LGT, VEC, WSD
    }


    @GetMapping("/weather")
    public String getWeather(Model model, HttpSession session) throws IOException, ParseException {
        WeatherDto weather = new WeatherDto();
        User user = (User) session.getAttribute("dto1");
        String address = user.getAddress();
        HashMap<String, String> XYMap = geoCoding.getXYMapfromJson(geoCoding.getKakaoApiFromAddress(address));
        String x = XYMap.get("x");   //위도
        String y = XYMap.get("y");   //경도
        model.addAttribute("weather", weather);
        return "layout/weather";
    }
}
