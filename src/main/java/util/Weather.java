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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Controller
public class Weather {
    @Autowired
    private GeoCoding geoCoding;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private String sky, temperature,  rain, snow, humidity;
    private String current_weather_code;
    // 현재 날짜/시간
    LocalDateTime t = LocalDateTime.now().minusMinutes(30);
    enum WeatherValue {
        T1H, RN1, SKY, UUU, VVV, REH, PTY, LGT, VEC, WSD
    }

    @GetMapping("/weather")
    public String getWeather(Model model, HttpSession session) throws IOException, ParseException {
        User user = (User) session.getAttribute("dto1");
        String address = user.getAddress();
        System.out.println(address);
        HashMap<String, String> XYMap = geoCoding.getXYMapfromJson(geoCoding.getKakaoApiFromAddress(address));
        System.out.println(XYMap);
        String x = XYMap.get("x");   //위도
        String y = XYMap.get("y");   //경도
        String nx = x.substring(0, x.lastIndexOf("."));
        String ny = y.substring(0, y.lastIndexOf("."));
        System.out.println(nx);
        System.out.println(ny);
        String type = "json";    //조회하고 싶은 type(json, xml 중 고름)
        String serviceKey = "T3X%2FxwFRUNtn%2FzTw6jl8fy2bMMq%2FZIeQh4Ao2AoJ0NQYj5eYWao63d%2FQNd3KflFbjUvg%2Ba5luRueWOJ0m9X39g%3D%3D";
        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtFcst";
        StringBuilder urlBuilder = new StringBuilder(apiUrl);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("nx", "UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); //경도
        urlBuilder.append("&" + URLEncoder.encode("ny", "UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); //위도
        urlBuilder.append("&" + URLEncoder.encode("base_date", "UTF-8") + "=" + URLEncoder.encode(t.format(DateTimeFormatter.ofPattern("yyyyMMdd")), "UTF-8")); /* 조회하고싶은 날짜*/
        urlBuilder.append("&" + URLEncoder.encode("base_time", "UTF-8") + "=" + URLEncoder.encode(t.format(DateTimeFormatter.ofPattern("HHmm")) , "UTF-8")); /* 조회하고싶은 시간 */
        urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8"));    /* 타입 */

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        boolean ok = false;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            ok = true;
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        if (ok) {
            String fd = null, ft = null; // 가장 빠른 예보 시각
            String pty = null; // 강수형태
            String sky = null; // 하늘상태
            String cat; // category
            String val; // fcstValue
        }
        rd.close();
        conn.disconnect();
        String result = sb.toString();
        JSONParser jsonParser = new JSONParser();
        logger.info("jsonParser : {}", jsonParser.toString());
        JSONObject jsonObject = (JSONObject) jsonParser.parse(result);
        JSONObject parse_response = (JSONObject) jsonObject.get("response");
        JSONObject parse_body = (JSONObject) parse_response.get("body"); // response 로 부터 body 찾아오기
        JSONObject parse_items = (JSONObject) parse_body.get("items"); // body 로 부터 items 받아오기
        // items 로 부터 itemList : 뒤에 [ 로 시작하므로 jsonArray 이다.
        JSONArray parse_item = (JSONArray) parse_items.get("item");
        System.out.println(parse_item);
        System.out.println("--------------------------");


        // jsonArray를 반복자로 반복
        for (int i = 0; i < parse_item.size(); i++) {
            JSONObject object = (JSONObject) parse_item.get(i);
            String category = (String) object.get("category"); // item 에서 카테고리를 검색
            String value = (String) object.get("fcstValue");
//            System.out.println(value);
//            System.out.println("--------------------------");
            WeatherValue weatherValue = WeatherValue.valueOf(category);
//            System.out.println(weatherValue);
//            System.out.println("--------------------------");
            if (category.equals("SKY")) {
                if (weatherValue.equals("1")) {
                    sky = "맑음 ";
                    current_weather_code = "1";
                } else if (weatherValue.equals("2")) {
                    sky = "비 ";
                    current_weather_code = "2";
                } else if (weatherValue.equals("3")) {
                    sky = "구름많음 ";
                    current_weather_code = "3";
                } else if (weatherValue.equals("4")) {
                    sky = "흐림 ";
                    current_weather_code = "4";
                }
            }

            if (category.equals("T1H")) {
                temperature = value + "℃ ";
            }

            if (category.equals("RN1")) {
                rain = value + "mm ";
                snow = value + "mm ";
            }
            if (category.equals("REH")) {
                humidity = value + "%";
            }
        }
        String weather = sky + rain + temperature  + snow + humidity;
        System.out.println(weather);
        model.addAttribute("weather", weather);
        return "layout/weather";
    }
}
