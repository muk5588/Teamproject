package calendar.service;

import calendar.dto.Calendar;

import java.util.List;

public interface CalendarService {
    public List<Calendar> getCalData(int userno);

    public void insertCalendar(Calendar calendar);

    public void updateDateCalendar(Calendar calendar);

    public void deleteCalendar(Calendar calendar);
}
