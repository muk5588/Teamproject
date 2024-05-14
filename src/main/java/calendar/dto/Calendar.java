package calendar.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Calendar {
    private int calendarNo;
    private int userno;
    private String calendarTitle;
    private String content;


    private Date start1;
    private Date end1;
    private int allDay;

    private Date oldStart;


    private Date oldEnd;





    public Calendar() {}



    public Calendar(int calendarNo, int userno, String calendarTitle, String content, Date start1, Date end1, int allDay, Date oldStart, Date oldEnd) {
        this.calendarNo = calendarNo;
        this.userno = userno;
        this.calendarTitle = calendarTitle;
        this.content = content;
        this.start1 = start1;
        this.end1 = end1;
        this.allDay = allDay;
        this.oldStart = oldStart;
        this.oldEnd = oldEnd;
    }


    @Override
    public String toString() {
        return "Calendar{" +
                "calendarNo=" + calendarNo +
                ", userno=" + userno +
                ", calendarTitle='" + calendarTitle + '\'' +
                ", content='" + content + '\'' +
                ", start1=" + start1 +
                ", end1=" + end1 +
                ", allDay=" + allDay +
                ", oldStart=" + oldStart +
                ", oldEnd=" + oldEnd +
                '}';
    }

    public int getCalendarNo() {
        return calendarNo;
    }

    public void setCalendarNo(int calendarNo) {
        this.calendarNo = calendarNo;
    }

    public int getUserno() {
        return userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public String getCalendarTitle() {
        return calendarTitle;
    }

    public void setCalendarTitle(String calendarTitle) {
        this.calendarTitle = calendarTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStart1() {
        return start1;
    }

    public void setStart1(Date start1) {
        this.start1 = start1;
    }

    public Date getEnd1() {
        return end1;
    }

    public void setEnd1(Date end1) {
        this.end1 = end1;
    }

    public int getAllDay() {
        return allDay;
    }

    public void setAllDay(int allDay) {
        this.allDay = allDay;
    }

    public Date getOldStart() {
        return oldStart;
    }

    public void setOldStart(Date oldStart) {
        this.oldStart = oldStart;
    }

    public Date getOldEnd() {
        return oldEnd;
    }

    public void setOldEnd(Date oldEnd) {
        this.oldEnd = oldEnd;
    }
}