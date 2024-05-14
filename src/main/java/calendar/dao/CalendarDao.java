package calendar.dao;

import calendar.dto.Calendar;

import java.util.Date;
import java.util.List;

public interface CalendarDao {

    public List<Calendar> getCalData(int userno);

    public void insertCalendar(Calendar calendar);

    public void updateCalendar(Calendar calendar);

    public void deleteCalendar(Calendar calendar);

}
