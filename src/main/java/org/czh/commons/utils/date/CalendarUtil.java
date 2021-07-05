package org.czh.commons.utils.date;

import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.annotations.tag.ValueTag;
import org.czh.commons.utils.DateUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.FlagAssert;

import java.util.Calendar;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class CalendarUtil {

    /*
      -----------------------------get calendar-------------------------------
     */

    public static Calendar getCalendar() {
        return Calendar.getInstance();
    }

    public static Calendar getCalendar(@NotNullTag final Date date) {
        EmptyAssert.isNotNull(date);

        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar;
    }

    public static void resetCalendar(Calendar calendar, Date date) {
        EmptyAssert.allNotNull(calendar, date);
        calendar.setTime(date);
    }

    /*
      -----------------------------get year-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(getYear(date)); // 2021
     */
    public static int getCurrentYear() {
        return getYear(DateUtil.getNowDate());
    }

    public static int getYear(@NotNullTag final Date date) {
        return getYear(getCalendar(date));
    }

    public static int getYear(@NotNullTag final Calendar calendar) {
        return getDateField(calendar, Calendar.YEAR);
    }

    /*
      -----------------------------get month-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(getMonth(date)); // 6
     */
    public static int getCurrentMonth() {
        return getMonth(DateUtil.getNowDate());
    }

    public static int getMonth(@NotNullTag final Date date) {
        return getMonth(getCalendar(date));
    }

    public static int getMonth(@NotNullTag final Calendar calendar) {
        return getDateField(calendar, Calendar.MONTH) + 1;
    }

    /*
      -----------------------------get day of month-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(getDayOfMonth(date)); // 29
     */
    public static int getCurrentDayOfMonth() {
        return getDayOfMonth(DateUtil.getNowDate());
    }

    public static int getDayOfMonth(@NotNullTag final Date date) {
        return getDayOfMonth(getCalendar(date));
    }

    public static int getDayOfMonth(@NotNullTag final Calendar calendar) {
        return getDateField(calendar, Calendar.DATE);
    }

    /*
      -----------------------------get day of week-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(getDayOfWeek(date)); // 3
     */
    public static int getCurrentDayOfWeek() {
        return getDayOfWeek(DateUtil.getNowDate());
    }

    public static int getDayOfWeek(@NotNullTag final Date date) {
        return getDayOfWeek(getCalendar(date));
    }

    public static int getDayOfWeek(@NotNullTag final Calendar calendar) {
        return getDateField(calendar, Calendar.DAY_OF_WEEK);
    }

    /*
      -----------------------------get hour-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(getHourOfDay(date)); // 12
     */
    public static int getCurrentHourOfDay() {
        return getHourOfDay(DateUtil.getNowDate());
    }

    public static int getHourOfDay(@NotNullTag final Date date) {
        return getHourOfDay(getCalendar(date));
    }

    public static int getHourOfDay(@NotNullTag final Calendar calendar) {
        return getDateField(calendar, Calendar.HOUR_OF_DAY);
    }

    /*
      -----------------------------get minute-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(getMinute(date)); // 29
     */
    public static int getCurrentMinute() {
        return getMinute(DateUtil.getNowDate());
    }

    public static int getMinute(@NotNullTag final Date date) {
        return getMinute(getCalendar(date));
    }

    public static int getMinute(@NotNullTag final Calendar calendar) {
        return getDateField(calendar, Calendar.MINUTE);
    }

    /*
      -----------------------------get second-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(getSecond(date)); // 49
     */
    public static int getCurrentSecond() {
        return getSecond(DateUtil.getNowDate());
    }

    public static int getSecond(@NotNullTag final Date date) {
        return getSecond(getCalendar(date));
    }

    public static int getSecond(@NotNullTag final Calendar calendar) {
        return getDateField(calendar, Calendar.SECOND);
    }

    /*
      -----------------------------get date field-------------------------------
     */

    public static int getCurrentDateField(@ValueTag(min = 1, max = 14) int field) {
        return getDateField(DateUtil.getNowDate(), field);
    }

    public static int getDateField(@NotNullTag final Date date, @ValueTag(min = 1, max = 14) int field) {
        return getDateField(getCalendar(date), field);
    }

    public static int getDateField(@NotNullTag final Calendar calendar, @ValueTag(min = 1, max = 14) int field) {
        EmptyAssert.isNotNull(calendar);
        FlagAssert.isTrue(field >= 0 && field <= 17);
        return calendar.get(field);
    }

    /*
      -----------------------------get before/after year now datetime-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddYearDate(date, -1))); // 2020-06-29 12:29:49
     * System.out.println(DateUtil.formatToText(getAddYearDate(date, 0))); // 2021-06-29 12:29:49
     * System.out.println(DateUtil.formatToText(getAddYearDate(date, 1))); // 2022-06-29 12:29:49
     * System.out.println();
     */
    public static Date getAddYearDate(final int addYearNum) {
        return getAddYearDate(DateUtil.getNowDate(), addYearNum);
    }

    public static Date getAddYearDate(@NotNullTag final Date date, int addYearNum) {
        return getAddYearDate(getCalendar(date), addYearNum);
    }

    public static Date getAddYearDate(@NotNullTag final Calendar calendar, final int addYearNum) {
        return getAddDate(calendar, Calendar.YEAR, addYearNum);
    }

    /*
      -----------------------------get before/after month now datetime-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddMonthDate(date, -1))); // 2021-05-29 12:29:49
     * System.out.println(DateUtil.formatToText(getAddMonthDate(date, 0))); // 2021-06-29 12:29:49
     * System.out.println(DateUtil.formatToText(getAddMonthDate(date, 1))); // 2021-07-29 12:29:49
     * System.out.println();
     */
    public static Date getAddMonthDate(final int addMonthNum) {
        return getAddMonthDate(DateUtil.getNowDate(), addMonthNum);
    }

    public static Date getAddMonthDate(@NotNullTag final Date date, final int addMonthNum) {
        return getAddMonthDate(getCalendar(date), addMonthNum);
    }

    public static Date getAddMonthDate(@NotNullTag final Calendar calendar, final int addMonthNum) {
        return getAddDate(calendar, Calendar.MONTH, addMonthNum);
    }

    /*
      -----------------------------get before/after week now datetime-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddWeekDate(date, -1))); // 2021-06-22 12:29:49
     * System.out.println(DateUtil.formatToText(getAddWeekDate(date, 0))); // 2021-06-29 12:29:49
     * System.out.println(DateUtil.formatToText(getAddWeekDate(date, 1))); // 2021-07-06 12:29:49
     * System.out.println();
     */
    public static Date getAddWeekDate(final int addWeekNum) {
        return getAddWeekDate(DateUtil.getNowDate(), addWeekNum);
    }

    public static Date getAddWeekDate(@NotNullTag final Date date, final int addWeekNum) {
        return getAddWeekDate(getCalendar(date), addWeekNum);
    }

    public static Date getAddWeekDate(@NotNullTag final Calendar calendar, final int addWeekNum) {
        return getAddDate(calendar, Calendar.DATE, addWeekNum * 7);
    }

    /*
      -----------------------------get before/after day now datetime-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddDayDate(date, -1))); // 2021-06-28 12:29:49
     * System.out.println(DateUtil.formatToText(getAddDayDate(date, 0))); // 2021-06-29 12:29:49
     * System.out.println(DateUtil.formatToText(getAddDayDate(date, 1))); // 2021-06-30 12:29:49
     * System.out.println();
     */
    public static Date getAddDayDate(final int addDayNum) {
        return getAddDayDate(DateUtil.getNowDate(), addDayNum);
    }

    public static Date getAddDayDate(@NotNullTag final Date date, final int addDayNum) {
        return getAddDayDate(getCalendar(date), addDayNum);
    }

    public static Date getAddDayDate(@NotNullTag final Calendar calendar, final int addDayNum) {
        return getAddDate(calendar, Calendar.DATE, addDayNum);
    }

    /*
      -----------------------------get before/after hour now datetime-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddHourDate(date, -1))); // 2021-06-29 11:29:49
     * System.out.println(DateUtil.formatToText(getAddHourDate(date, 0))); // 2021-06-29 12:29:49
     * System.out.println(DateUtil.formatToText(getAddHourDate(date, 1))); // 2021-06-29 13:29:49
     * System.out.println();
     */
    public static Date getAddHourDate(final int addHourNum) {
        return getAddHourDate(DateUtil.getNowDate(), addHourNum);
    }

    public static Date getAddHourDate(@NotNullTag final Date date, final int addHourNum) {
        return getAddHourDate(getCalendar(date), addHourNum);
    }

    public static Date getAddHourDate(@NotNullTag final Calendar calendar, final int addHourNum) {
        return getAddDate(calendar, Calendar.HOUR, addHourNum);
    }

    /*
      -----------------------------get before/after minute now datetime-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddMinuteDate(date, -1))); // 2021-06-29 12:28:49
     * System.out.println(DateUtil.formatToText(getAddMinuteDate(date, 0))); // 2021-06-29 12:29:49
     * System.out.println(DateUtil.formatToText(getAddMinuteDate(date, 1))); // 2021-06-29 12:30:49
     * System.out.println();
     */
    public static Date getAddMinuteDate(final int addMinuteNum) {
        return getAddMinuteDate(DateUtil.getNowDate(), addMinuteNum);
    }

    public static Date getAddMinuteDate(@NotNullTag final Date date, final int addMinuteNum) {
        return getAddMinuteDate(getCalendar(date), addMinuteNum);
    }

    public static Date getAddMinuteDate(@NotNullTag final Calendar calendar, final int addMinuteNum) {
        return getAddDate(calendar, Calendar.MINUTE, addMinuteNum);
    }

    /*
      -----------------------------get before/after second now datetime-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddSecondDate(date, -1))); // 2021-06-29 12:29:48
     * System.out.println(DateUtil.formatToText(getAddSecondDate(date, 0))); // 2021-06-29 12:29:49
     * System.out.println(DateUtil.formatToText(getAddSecondDate(date, 1))); // 2021-06-29 12:29:50
     * System.out.println();
     */
    public static Date getAddSecondDate(final int addSecondNum) {
        return getAddSecondDate(DateUtil.getNowDate(), addSecondNum);
    }

    public static Date getAddSecondDate(@NotNullTag final Date date, final int addSecondNum) {
        return getAddSecondDate(getCalendar(date), addSecondNum);
    }

    public static Date getAddSecondDate(@NotNullTag final Calendar calendar, final int addSecondNum) {
        return getAddDate(calendar, Calendar.SECOND, addSecondNum);
    }

    /*
      -----------------------------get before/after date now datetime-------------------------------
     */

    public static Date getAddDate(@ValueTag(min = 1, max = 14)  final int field, final int addNum) {
        return getAddDate(DateUtil.getNowDate(), field, addNum);
    }

    public static Date getAddDate(@NotNullTag final Date date,
                                  @ValueTag(min = 1, max = 14)  final int field,
                                  final int addNum) {
        return getAddDate(getCalendar(date), field, addNum);
    }

    public static Date getAddDate(@NotNullTag final Calendar calendar,
                                  @ValueTag(min = 1, max = 14)  final int field,
                                  final int addNum) {
        EmptyAssert.isNotNull(calendar);
        FlagAssert.isTrue(field >= 0 && field <= 17);

        calendar.add(field, addNum);
        return calendar.getTime();
    }

    /*
      -----------------------------main-------------------------------
     */

    public static void main(String[] args) {
        Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
        System.out.println(getYear(date)); // 2021
        System.out.println(getMonth(date)); // 6
        System.out.println(getDayOfMonth(date)); // 29
        System.out.println(getDayOfWeek(date)); // 3
        System.out.println(getHourOfDay(date)); // 12
        System.out.println(getMinute(date)); // 29
        System.out.println(getSecond(date)); // 49
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddYearDate(date, -1))); // 2020-06-29 12:29:49
        System.out.println(DateUtil.formatToText(getAddYearDate(date, 0))); // 2021-06-29 12:29:49
        System.out.println(DateUtil.formatToText(getAddYearDate(date, 1))); // 2022-06-29 12:29:49
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddMonthDate(date, -1))); // 2021-05-29 12:29:49
        System.out.println(DateUtil.formatToText(getAddMonthDate(date, 0))); // 2021-06-29 12:29:49
        System.out.println(DateUtil.formatToText(getAddMonthDate(date, 1))); // 2021-07-29 12:29:49
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddWeekDate(date, -1))); // 2021-06-22 12:29:49
        System.out.println(DateUtil.formatToText(getAddWeekDate(date, 0))); // 2021-06-29 12:29:49
        System.out.println(DateUtil.formatToText(getAddWeekDate(date, 1))); // 2021-07-06 12:29:49
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddDayDate(date, -1))); // 2021-06-28 12:29:49
        System.out.println(DateUtil.formatToText(getAddDayDate(date, 0))); // 2021-06-29 12:29:49
        System.out.println(DateUtil.formatToText(getAddDayDate(date, 1))); // 2021-06-30 12:29:49
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddHourDate(date, -1))); // 2021-06-29 11:29:49
        System.out.println(DateUtil.formatToText(getAddHourDate(date, 0))); // 2021-06-29 12:29:49
        System.out.println(DateUtil.formatToText(getAddHourDate(date, 1))); // 2021-06-29 13:29:49
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddMinuteDate(date, -1))); // 2021-06-29 12:28:49
        System.out.println(DateUtil.formatToText(getAddMinuteDate(date, 0))); // 2021-06-29 12:29:49
        System.out.println(DateUtil.formatToText(getAddMinuteDate(date, 1))); // 2021-06-29 12:30:49
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddSecondDate(date, -1))); // 2021-06-29 12:29:48
        System.out.println(DateUtil.formatToText(getAddSecondDate(date, 0))); // 2021-06-29 12:29:49
        System.out.println(DateUtil.formatToText(getAddSecondDate(date, 1))); // 2021-06-29 12:29:50
        System.out.println();
    }
}
