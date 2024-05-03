package util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
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
        String serviceKey = "T3X%2FxwFRUNtn%2FzTw6jl8fy2bMMq%2FZIeQh4Ao2AoJ0NQYj5eYWao63d%2FQNd3KflFbjUvg%2Ba5luRueWOJ0m9X39g%3D%3D";
        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst";
        User user = (User) session.getAttribute("dto1");
        String address = user.getAddress();
        HashMap<String, String> XYMap = geoCoding.getXYMapfromJson(geoCoding.getKakaoApiFromAddress(address));
        String x = XYMap.get("x");   //위도
        String y = XYMap.get("y");   //경도
        String nx = x.substring(0, x.lastIndexOf("."));
        String ny = y.substring(0, y.lastIndexOf("."));
        String type = "json";    //조회하고 싶은 type(json, xml 중 고름)
        Date d = new Date();
        SimpleDateFormat f1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat f2 = new SimpleDateFormat("HH");
        String baseDate = f1.format(d);
        String baseTime = Integer.parseInt(f2.format(d)) - 1 + "00";

        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /* 조회하고싶은 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /* 조회하고싶은 시간 */
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
        String stream = sb.toString();
        System.out.println(stream);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(stream);

        JSONObject response = (JSONObject) jsonObject.get("response");
        JSONObject body = (JSONObject) response.get("body");
        JSONObject items = (JSONObject) body.get("items");

        JSONArray jsonArray = (JSONArray) items.get("item");

        System.out.println("--------------------------");

        JSONObject object;
        // item 내부의 category 를 보고 사용하기 위해서 사용
        String category;
        Double value;

        // jsonArray를 반복자로 반복
        for (int i = 0; i < jsonArray.size(); i++) {

            object = (JSONObject) jsonArray.get(i);
            category = (String) object.get("category");

            value = Double.parseDouble((String) object.get("fcstValue"));

            WeatherValue weatherValue = WeatherValue.valueOf(category);

            switch (weatherValue) {
                case PTY:
                    weather.setPTY(value);
                    break;
                case REH:
                    weather.setREH(value);
                    break;
                case RN1:
                    weather.setRN1(value);
                    break;
                case T1H:
                    weather.setT1H(value);
                    break;
                case UUU:
                    weather.setUUU(value);
                    break;
                case VEC:
                    weather.setVEC(value);
                    break;
                case VVV:
                    weather.setVVV(value);
                    break;
                case WSD:
                    weather.setWSD(value);
                    break;
                default:
                    break;
            }
        }
        weather.setDate(baseDate);
        weather.setTime(baseTime);

        System.out.println(baseTime);
        System.out.println(baseTime);
        System.out.println(weather);
        model.addAttribute("weather", weather);
        return "layout/weather";
    }
}
