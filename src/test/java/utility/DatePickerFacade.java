package utility;

import pages.CalendarPageComponent;

public class DatePickerFacade {
    private final CalendarPageComponent calendar;
    public DatePickerFacade(CalendarPageComponent calendar)  {
        this.calendar = calendar;
    }

    public void setDesiredDate()    {
        calendar.setYear();
        calendar.setMonth();
        calendar.setDay();
    }
}
