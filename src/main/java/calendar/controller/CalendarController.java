package calendar.controller;

import calendar.dto.Calendar;
import calendar.service.CalendarService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import user.dto.User;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class CalendarController {
   @Autowired
   private CalendarService calendarService;

   //캘린더 화면 출력
   @GetMapping("/calendar")
   public String calendarHome(){

       return "calendar/calendar";
   }

   //달력 데이터 조회
   @GetMapping("/calendar/calendarList")
   @ResponseBody
   public List<Map<String, Object>> calendarList(@SessionAttribute(value = "dto1", required = false) User login) throws IOException {
       int userno = login.getUserno();

       List<Calendar> listAll = calendarService.getCalData(userno);

       JSONArray jsonArr = new JSONArray();

       for (int i = 0; i < listAll.size(); i++) {
           HashMap<String, Object> hash = new HashMap<>();
           hash.put("title", listAll.get(i).getCalendarTitle());
           hash.put("xcontent", listAll.get(i).getContent());
           hash.put("start", listAll.get(i).getStart1());
           hash.put("end", listAll.get(i).getEnd1());

           jsonArr.add(hash);
       }
       return jsonArr;
   }

    //Insert
   @RequestMapping( "/calendar/calendarInsert")
   @ResponseBody
   public void calendarInsert(@SessionAttribute(value = "dto1", required = false) User login
           ,@RequestBody HashMap<String, Object> param) {
       DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);



       String title = (String) param.get("title");
       String content = (String) param.get("xcontent");
       String startDateString = (String) param.get("start");
       String endDateString = (String) param.get("end");

       LocalDateTime startDateUTC = LocalDateTime.parse(startDateString, dateTimeFormatter);
       LocalDateTime endDateUTC = LocalDateTime.parse(endDateString, dateTimeFormatter);

       LocalDateTime startDate = startDateUTC.plusHours(9);
       LocalDateTime endDate = endDateUTC.plusHours(9);


       Date start = java.sql.Timestamp.valueOf(startDate);
       Date end = java.sql.Timestamp.valueOf(endDate);

       Calendar calendar = new Calendar();
       calendar.setUserno(login.getUserno());
       calendar.setCalendarTitle(title);
       calendar.setContent(content);
       calendar.setStart1(start);
       calendar.setEnd1(end);

       calendarService.insertCalendar(calendar);





   }



    //Update
    @RequestMapping("/calendar/calendarUpdate")
    @ResponseBody
    public void calendarUpdate(@RequestBody HashMap<String, Object> param
        ,@SessionAttribute(value = "dto1", required = false) User login)  {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);
        String title = (String) param.get("title");
        String content = (String) param.get("xcontent");
        String startDateString = (String) param.get("start");
        String endDateString = (String) param.get("end");

        LocalDateTime startDateUTC = LocalDateTime.parse(startDateString, dateTimeFormatter);
        LocalDateTime endDateUTC = LocalDateTime.parse(endDateString, dateTimeFormatter);

        LocalDateTime startDate = startDateUTC.plusHours(9);
        LocalDateTime endDate = endDateUTC.plusHours(9);


        Date start = java.sql.Timestamp.valueOf(startDate);
        Date end = java.sql.Timestamp.valueOf(endDate);

        Calendar calendar = new Calendar();
        calendar.setUserno(login.getUserno());
        calendar.setCalendarTitle(title);
        calendar.setContent(content);
        calendar.setStart1(start);
        calendar.setEnd1(end);


        calendarService.updateCalendar(calendar);




    }

    //Delete
    @RequestMapping("/calendar/calendarDelete")
    @ResponseBody
    public void calendarDelete(@RequestBody HashMap<String, Object> param
        ,@SessionAttribute(value = "dto1", required = false) User login) throws ParseException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);


            String title = (String) param.get("title");
            String content = (String) param.get("xcontent");
            String startDateString = (String) param.get("start");
            String endDateString = (String) param.get("end");

            LocalDateTime startDateUTC = LocalDateTime.parse(startDateString, dateTimeFormatter);
            LocalDateTime endDateUTC = LocalDateTime.parse(endDateString, dateTimeFormatter);

            LocalDateTime startDate = startDateUTC.plusHours(9);
             LocalDateTime endDate = endDateUTC.plusHours(9);



            Date start = java.sql.Timestamp.valueOf(startDate);
            Date end = java.sql.Timestamp.valueOf(endDate);
            System.out.println("start : " + start + "end : " + end);
            Calendar calendar = new Calendar();
            calendar.setUserno(login.getUserno());
            calendar.setCalendarTitle(title);
            calendar.setContent(content);
            calendar.setStart1(start);
            calendar.setEnd1(end);


            calendarService.deleteCalendar(calendar);

    }
}
