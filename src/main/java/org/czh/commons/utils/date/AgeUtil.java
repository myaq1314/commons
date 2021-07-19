package org.czh.commons.utils.date;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.constant.DateConstant;
import org.czh.commons.utils.DateUtil;
import org.czh.commons.validate.EmptyAssert;

import java.util.Calendar;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
public final class AgeUtil {

    public static int getCurrentAge(@NotBlankTag final String birthdayText) {
        return getCurrentAge(birthdayText, DateConstant.DATE_STANDARD());
    }

    public static int getCurrentAge(@NotBlankTag final String birthdayText, @NotBlankTag final String pattern) {
        return getCurrentAge(DateUtil.parseToDate(birthdayText, pattern));
    }

    public static int getCurrentAge(@NotNullTag final Date birthdayDate) {
        return getAge(birthdayDate, DateUtil.getNowDate());
    }

    public static int getAge(@NotNullTag final Date startDate, @NotNullTag final Date endDate) {
        EmptyAssert.allNotNull(startDate, endDate);

        Calendar calendar = CalendarUtil.getCalendar(startDate);
        int startYear = CalendarUtil.getYear(calendar);
        int startMonth = CalendarUtil.getMonth(calendar);
        int startDay = CalendarUtil.getDayOfMonth(calendar);

        CalendarUtil.resetCalendar(calendar, endDate);
        int endYear = CalendarUtil.getYear(calendar);
        int endMonth = CalendarUtil.getMonth(calendar);
        int endDay = CalendarUtil.getDayOfMonth(calendar);

        int age = endYear - startYear;
        if (endMonth < startMonth) {
            age--;
        } else if (endMonth == startMonth) {
            if (endDay < startDay) {
                age--;
            }
        }
        return age;
    }
}
