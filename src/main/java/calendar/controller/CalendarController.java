package calendar.controller;

import calendar.dto.Calendar;
import calendar.service.CalendarService;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import user.dto.User;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Controller
public class CalendarController {
   @Autowired
   private CalendarService calendarService;
   private Logger logger = LoggerFactory.getLogger(getClass());

   //캘린더 화면 출력
   @GetMapping("/calendar")
   public String calendarHome(){

       return "/calendar/calendar";
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
           hash.put("content", listAll.get(i).getContent());
           hash.put("start", listAll.get(i).getStart1());
           hash.put("end", listAll.get(i).getEnd1());

           jsonArr.add(hash);
       }
       logger.info("jsonArrCheck: {}", jsonArr);
       return jsonArr;
   }

    //Insert
   @PostMapping(value = "/calendar/calendarInsert")
   @ResponseBody
   public String calendarInsert(@SessionAttribute(value = "dto1", required = false) User login
           ,@RequestBody List<Map<String, Object>> param) throws IOException {
       DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);

       for (Map<String, Object> list : param) {

           String title = (String) list.get("title");
           String content = (String) list.get("content");
           String startDateString = (String) list.get("start");
           String endDateString = (String) list.get("end");

           LocalDateTime startDateUTC = LocalDateTime.parse(startDateString, dateTimeFormatter);
           LocalDateTime endDateUTC = LocalDateTime.parse(endDateString, dateTimeFormatter);

           LocalDateTime startDate = startDateUTC.plusHours(9);
           LocalDateTime endDate = endDateUTC.plusHours(9);

           int userno = login.getUserno();
            Date start = java.sql.Timestamp.valueOf(startDate);
            Date end = java.sql.Timestamp.valueOf(endDate);


           Calendar calendar = new Calendar();
            calendar.setUserno(userno);
           calendar.setCalendarTitle(title);
           calendar.setContent(content);
           calendar.setStart1(start);
           calendar.setEnd1(end);

           calendarService.insertCalendar(calendar);
       }


       return "/calendar/calendar";
   }



    //Update
    @RequestMapping("/calendar/calendarUpdate")
    @ResponseBody
    public String calendarUpdate(@RequestBody List<Map<String, Object>> param
            ,@SessionAttribute(value = "dto1", required = false) User login){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);
        for (Map<String, Object> list : param) {

            String title = (String) list.get("title");
            String content = (String) list.get("content");
            String startDateString = (String) list.get("start");
            String endDateString = (String) list.get("end");

            LocalDateTime startDateUTC = LocalDateTime.parse(startDateString, dateTimeFormatter);
            LocalDateTime endDateUTC = LocalDateTime.parse(endDateString, dateTimeFormatter);

            LocalDateTime startDate = startDateUTC.plusHours(9);
            LocalDateTime endDate = endDateUTC.plusHours(9);

            int userno = login.getUserno();
            Date start = java.sql.Timestamp.valueOf(startDate);
            Date end = java.sql.Timestamp.valueOf(endDate);


            Calendar calendar = new Calendar();
            calendar.setUserno(userno);
            calendar.setCalendarTitle(title);
            calendar.setContent(content);
            calendar.setStart1(start);
            calendar.setEnd1(end);

            calendarService.updateCalendar(calendar);
        }



        return "/calendar/calendar";
    }

    //Delete
    @RequestMapping("/calendar/calendarDelete")
    @ResponseBody
    public String calendarDelete(@RequestBody List<Map<String, Object>> param
        ,@SessionAttribute(value = "dto1", required = false) User login){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.KOREA);

//        for (Map<String, Object> list : param) {
//
//            System.out.println("list = " + list);
//
//            String title = (String) list.get("title"); // 이름 받아오기
//            String content = (String) list.get("content");
//            String startDateString = (String) list.get("start");
//            String endDateString = (String) list.get("end");
//
//            System.out.println("startDateString = " + startDateString);
//
//            LocalDateTime startDate = LocalDateTime.parse(startDateString, dateTimeFormatter);
//            LocalDateTime endDate = LocalDateTime.parse(endDateString, dateTimeFormatter);
//
//            System.out.println("startDate = " + startDate);
//
//            int userno = login.getUserno();
//
//            Schedule schedule = scheduleService.findByScheduleDateTimeStartAndScheduleDateTimeEnd(startDate, endDate).get();
//            Integer scheduleId = schedule.getId();
//            System.out.println("=================================");
//            System.out.println("scheduleId = " + scheduleId);
//
//            ManagerAssignSchedule assignSchedule = managerAssignScheduleService.findBySchedule(schedule).get();
//            Integer assignScheduleId = assignSchedule.getId();
//            System.out.println("========================");
//            System.out.println("assignSchedule = " + assignSchedule);
//            System.out.println("assignScheduleId = " + assignScheduleId);
//
//
//
//
//                calendarService.deleteByDate(schedule);

//        }
       return "/calendar/calendar";
    }
}
