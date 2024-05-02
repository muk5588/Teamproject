package util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import user.dto.User;
import util.service.GeoCoding;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

@Controller
public class Weather {
    @Autowired
    private GeoCoding geoCoding;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    Calendar c1 = Calendar.getInstance();
    String strToday = sdf.format(c1.getTime());

    @GetMapping("/weather")
    public String getWeather(Model model, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("dto1");
        String address = user.getAddress();
        System.out.println(address);
        HashMap<String, String> XYMap = geoCoding.getXYMapfromJson(geoCoding.getKakaoApiFromAddress(address));
        System.out.println(XYMap);
        String nx = XYMap.get("x");   //위도
        String ny = XYMap.get("y");   //경도
        String baseDate = strToday;    //조회하고싶은 날짜
        String baseTime = "0000";    //조회하고싶은 시간
        String type = "json";    //조회하고 싶은 type(json, xml 중 고름)
        String serviceKey = "T3X%2FxwFRUNtn%2FzTw6jl8fy2bMMq%2FZIeQh4Ao2AoJ0NQYj5eYWao63d%2FQNd3KflFbjUvg%2Ba5luRueWOJ0m9X39g%3D%3D";
        String apiUrl = "http://apis.data.go.kr/1360000/TourStnInfoService/getTourStnVilageFcst";
        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 AM 02시부터 3시간 단위 */
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));    /* 타입 */

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
        String result = sb.toString();
        model.addAttribute("weather", result);
        return "layout/weather";
    }
}
