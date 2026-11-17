package utils;

import com.ibm.icu.text.SimpleDateFormat;
import com.ibm.icu.util.ULocale;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final ULocale PERSIAN_LOCALE = new ULocale("fa_IR@calendar=persian");
    public static final ULocale PERSIAN_EN_LOCALE = new ULocale("en@calendar=persian");

    /**
     *
     * @param date
     * @param pattern --> "yyyy/MM/dd HH:mm:ss" or "yyyy/MM/dd hh:mm:ss" or "yy/MM/dd" ...
     * @return
     */
    public static String fromDateToPersianString(Date date, String pattern) {
        return new SimpleDateFormat(pattern, PERSIAN_EN_LOCALE).format(date);
    }

    public static Date addToDate(Date date, int calendarTimeTypeToAdd, int amountToAdd) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
//        calendar.add(Calendar.YEAR, 1);
        calendar.add(calendarTimeTypeToAdd, amountToAdd);
        return calendar.getTime();
    }


}
