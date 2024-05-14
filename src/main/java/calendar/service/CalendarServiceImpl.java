package calendar.service;

import calendar.dao.CalendarDao;
import calendar.dto.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService{
    @Autowired private CalendarDao calendarDao;
    @Override
    public List<Calendar> getCalData(int userno) {
        return calendarDao.getCalData(userno);
    }

    @Override
    public void insertCalendar(Calendar calendar) {

        calendarDao.insertCalendar(calendar);
    }

    @Override
    public void updateCalendar(Calendar calendar) {
        calendarDao.updateCalendar(calendar);
    }

    @Override
    public void deleteCalendar(Calendar calendar) {
        calendarDao.deleteCalendar(calendar);
    }


}
