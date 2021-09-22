package org.czh.commons.utils.date;

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
public final class PointDateUtil {

    /*
      -----------------------------get before/after year point date-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddYearPointDate(date, -1, true))); // 2020-01-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddYearPointDate(date, -1, false))); // 2020-12-31 00:00:00
     * System.out.println(DateUtil.formatToText(getAddYearPointDate(date, 0, true))); // 2021-01-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddYearPointDate(date, 0, false))); // 2021-12-31 00:00:00
     * System.out.println(DateUtil.formatToText(getAddYearPointDate(date, 1, true))); // 2022-01-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddYearPointDate(date, 1, false))); // 2022-12-31 00:00:00
     * System.out.println();
     */
    public static Date getAddYearPointDate(final int addYearNum, final boolean startOrEnd) {
        return getAddYearPointDate(DateUtil.getNowDate(), addYearNum, startOrEnd);
    }

    public static Date getAddYearPointDate(final Date date,
                                           final int addYearNum,
                                           final boolean startOrEnd) {
        return getAddYearPointDate(CalendarUtil.getCalendar(date), addYearNum, startOrEnd);
    }

    public static Date getAddYearPointDate(final Calendar calendar,
                                           final int addYearNum,
                                           final boolean startOrEnd) {
        return getYearPointDate(calendar, CalendarUtil.getYear(calendar) + addYearNum, startOrEnd);
    }

    public static Date getYearPointDate(final int year, final boolean startOrEnd) {
        return getYearPointDate(CalendarUtil.getCalendar(), year, startOrEnd);
    }

    public static Date getYearPointDate(final Calendar calendar, final int year, final boolean startOrEnd) {
        EmptyAssert.isNotNull(calendar);

        calendar.clear();
        if (startOrEnd) {
            calendar.set(Calendar.YEAR, year);
            return calendar.getTime();
        } else {
            calendar.set(Calendar.YEAR, year + 1);
            return CalendarUtil.getAddDayDate(calendar, -1);
        }
    }

    /*
      -----------------------------get before/after quarter point date-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddQuarterPointDate(date, -1, true))); // 2021-04-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddQuarterPointDate(date, -1, false))); // 2021-06-30 00:00:00
     * System.out.println(DateUtil.formatToText(getAddQuarterPointDate(date, 0, true))); // 2021-04-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddQuarterPointDate(date, 0, false))); // 2021-06-30 00:00:00
     * System.out.println(DateUtil.formatToText(getAddQuarterPointDate(date, 1, true))); // 2021-07-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddQuarterPointDate(date, 1, false))); // 2021-09-30 00:00:00
     * System.out.println();
     */
    public static Date getAddQuarterPointDate(final int addMonthNum, final boolean startOrEnd) {
        return getAddQuarterPointDate(DateUtil.getNowDate(), addMonthNum, startOrEnd);
    }

    public static Date getAddQuarterPointDate(final Date date,
                                              final int addMonthNum,
                                              final boolean startOrEnd) {
        return getAddQuarterPointDate(CalendarUtil.getCalendar(date), addMonthNum, startOrEnd);
    }

    public static Date getAddQuarterPointDate(final Calendar calendar,
                                              final int addMonthNum,
                                              final boolean startOrEnd) {
        return getQuarterPointDate(
                calendar,
                CalendarUtil.getYear(calendar),
                CalendarUtil.getMonth(calendar) + addMonthNum,
                startOrEnd
        );
    }

    public static Date getQuarterPointDate(final int year, final int month, final boolean startOrEnd) {
        return getMonthPointDate(CalendarUtil.getCalendar(), year, month, startOrEnd);
    }

    public static Date getQuarterPointDate(final Calendar calendar,
                                           final int year,
                                           final int month,
                                           final boolean startOrEnd) {
        EmptyAssert.isNotNull(calendar);

        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1 - (month - 1) % 3);

        if (startOrEnd) {
            return calendar.getTime();
        } else {
            calendar.add(Calendar.MONTH, 3);
            return CalendarUtil.getAddDayDate(calendar, -1);
        }
    }

    /*
      -----------------------------get before/after month point date-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddMonthPointDate(date, -1, true))); // 2021-05-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddMonthPointDate(date, -1, false))); // 2021-05-31 00:00:00
     * System.out.println(DateUtil.formatToText(getAddMonthPointDate(date, 0, true))); // 2021-06-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddMonthPointDate(date, 0, false))); // 2021-06-30 00:00:00
     * System.out.println(DateUtil.formatToText(getAddMonthPointDate(date, 1, true))); // 2021-07-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddMonthPointDate(date, 1, false))); // 2021-07-31 00:00:00
     * System.out.println();
     */
    public static Date getAddMonthPointDate(final int addMonthNum, final boolean startOrEnd) {
        return getAddMonthPointDate(DateUtil.getNowDate(), addMonthNum, startOrEnd);
    }

    public static Date getAddMonthPointDate(final Date date,
                                            final int addMonthNum,
                                            final boolean startOrEnd) {
        return getAddMonthPointDate(CalendarUtil.getCalendar(date), addMonthNum, startOrEnd);
    }

    public static Date getAddMonthPointDate(final Calendar calendar,
                                            final int addMonthNum,
                                            final boolean startOrEnd) {
        return getMonthPointDate(
                calendar,
                CalendarUtil.getYear(calendar),
                CalendarUtil.getMonth(calendar) + addMonthNum,
                startOrEnd
        );
    }

    public static Date getMonthPointDate(final int year, final int month, final boolean startOrEnd) {
        return getMonthPointDate(CalendarUtil.getCalendar(), year, month, startOrEnd);
    }

    public static Date getMonthPointDate(final Calendar calendar,
                                         final int year,
                                         final int month,
                                         final boolean startOrEnd) {
        EmptyAssert.isNotNull(calendar);

        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        if (startOrEnd) {
            calendar.set(Calendar.MONTH, month - 1);
            return calendar.getTime();
        } else {
            // month - 1 + 1
            calendar.set(Calendar.MONTH, month);
            return CalendarUtil.getAddDayDate(calendar, -1);
        }
    }

    /*
      -----------------------------get before/after week point date-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * // 周日做星期第一天（当前日期为周二）
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.SUNDAY, -1, true))); // 2021-06-27 00:00:00 本周日
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.SUNDAY, -1, false))); // 2021-07-03 00:00:00 本周六
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.SUNDAY, 0, true))); // 2021-06-27 00:00:00 本周日
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.SUNDAY, 0, false))); // 2021-07-03 00:00:00 本周六
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.SUNDAY, 1, true))); // 2021-06-27 00:00:00 本周日
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.SUNDAY, 1, false))); // 2021-07-03 00:00:00 本周六
     * System.out.println();
     * // 周一做星期第一天（当前日期为周二）
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.MONDAY, -1, true))); // 2021-06-28 00:00:00 本周一
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.MONDAY, -1, false))); // 2021-07-04 00:00:00 本周日
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.MONDAY, 0, true))); // 2021-06-28 00:00:00 本周一
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.MONDAY, 0, false))); // 2021-07-04 00:00:00 本周日
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.MONDAY, 1, true))); // 2021-06-28 00:00:00 本周一
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.MONDAY, 1, false))); // 2021-07-04 00:00:00 本周日
     * System.out.println();
     * // 周二做星期第一天（当前日期为周二）
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.TUESDAY, -1, true))); // 2021-06-22 00:00:00 上周二
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.TUESDAY, -1, false))); // 2021-06-28 00:00:00 上周一
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.TUESDAY, 0, true))); // 2021-06-29 00:00:00 本周二
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.TUESDAY, 0, false))); // 2021-07-05 00:00:00 本周一
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.TUESDAY, 1, true))); // 2021-06-29 00:00:00 本周二
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.TUESDAY, 1, false))); // 2021-07-05 00:00:00 本周一
     * System.out.println();
     * // 周三做星期第一天（当前日期为周二）
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.WEDNESDAY, -1, true))); // 2021-06-23 00:00:00 本周三
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.WEDNESDAY, -1, false))); // 2021-06-29 00:00:00 本周二
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.WEDNESDAY, 0, true))); // 2021-06-23 00:00:00 本周三
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.WEDNESDAY, 0, false))); // 2021-06-29 00:00:00 本周二
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.WEDNESDAY, 1, true))); // 2021-06-30 00:00:00 下周三
     * System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.WEDNESDAY, 1, false))); // 2021-07-06 00:00:00 下周二
     * System.out.println();
     */
    public static Date getAddWeekPointDate(final int firstOfWeek,
                                           final int addDayNum,
                                           final boolean startOrEnd) {
        return getAddWeekPointDate(DateUtil.getNowDate(), firstOfWeek, addDayNum, startOrEnd);
    }

    public static Date getAddWeekPointDate(final Date date,
                                           final int firstOfWeek,
                                           final int addDayNum,
                                           final boolean startOrEnd) {
        return getAddWeekPointDate(CalendarUtil.getCalendar(date), firstOfWeek, addDayNum, startOrEnd);
    }

    public static Date getAddWeekPointDate(final Calendar calendar,
                                           final int firstOfWeek,
                                           final int addDayNum,
                                           final boolean startOrEnd) {
        int year = CalendarUtil.getYear(calendar);
        int month = CalendarUtil.getMonth(calendar);
        int dayOfMonth = CalendarUtil.getDayOfMonth(calendar) + addDayNum;
        return getWeekPointDate(calendar, year, month, dayOfMonth, firstOfWeek, startOrEnd);
    }

    public static Date getWeekPointDate(final int year,
                                        final int month,
                                        final int dayOfMonth,
                                        final int firstOfWeek,
                                        final boolean startOrEnd) {
        return getWeekPointDate(CalendarUtil.getCalendar(), year, month, dayOfMonth, firstOfWeek, startOrEnd);
    }

    public static Date getWeekPointDate(final Calendar calendar,
                                        final int year,
                                        final int month,
                                        final int dayOfMonth,
                                        final int firstOfWeek,
                                        final boolean startOrEnd) {
        EmptyAssert.isNotNull(calendar);

        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek < firstOfWeek) {
            calendar.add(Calendar.DAY_OF_MONTH, -dayOfWeek + firstOfWeek - 7);
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, -dayOfWeek + firstOfWeek);
        }
        if (!startOrEnd) {
            calendar.add(Calendar.DAY_OF_MONTH, 6);
        }
        return calendar.getTime();
    }
    
    /*
      -----------------------------main-------------------------------
     */

    public static void main(String[] args) {
        Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
        System.out.println(DateUtil.formatToText(getAddYearPointDate(date, -1, true))); // 2020-01-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddYearPointDate(date, -1, false))); // 2020-12-31 00:00:00
        System.out.println(DateUtil.formatToText(getAddYearPointDate(date, 0, true))); // 2021-01-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddYearPointDate(date, 0, false))); // 2021-12-31 00:00:00
        System.out.println(DateUtil.formatToText(getAddYearPointDate(date, 1, true))); // 2022-01-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddYearPointDate(date, 1, false))); // 2022-12-31 00:00:00
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddQuarterPointDate(date, -1, true))); // 2021-04-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddQuarterPointDate(date, -1, false))); // 2021-06-30 00:00:00
        System.out.println(DateUtil.formatToText(getAddQuarterPointDate(date, 0, true))); // 2021-04-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddQuarterPointDate(date, 0, false))); // 2021-06-30 00:00:00
        System.out.println(DateUtil.formatToText(getAddQuarterPointDate(date, 1, true))); // 2021-07-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddQuarterPointDate(date, 1, false))); // 2021-09-30 00:00:00
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddMonthPointDate(date, -1, true))); // 2021-05-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddMonthPointDate(date, -1, false))); // 2021-05-31 00:00:00
        System.out.println(DateUtil.formatToText(getAddMonthPointDate(date, 0, true))); // 2021-06-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddMonthPointDate(date, 0, false))); // 2021-06-30 00:00:00
        System.out.println(DateUtil.formatToText(getAddMonthPointDate(date, 1, true))); // 2021-07-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddMonthPointDate(date, 1, false))); // 2021-07-31 00:00:00
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.SUNDAY, -1, true))); // 2021-06-27 00:00:00 本周日
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.SUNDAY, -1, false))); // 2021-07-03 00:00:00 本周六
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.SUNDAY, 0, true))); // 2021-06-27 00:00:00 本周日
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.SUNDAY, 0, false))); // 2021-07-03 00:00:00 本周六
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.SUNDAY, 1, true))); // 2021-06-27 00:00:00 本周日
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.SUNDAY, 1, false))); // 2021-07-03 00:00:00 本周六
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.MONDAY, -1, true))); // 2021-06-28 00:00:00 本周一
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.MONDAY, -1, false))); // 2021-07-04 00:00:00 本周日
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.MONDAY, 0, true))); // 2021-06-28 00:00:00 本周一
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.MONDAY, 0, false))); // 2021-07-04 00:00:00 本周日
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.MONDAY, 1, true))); // 2021-06-28 00:00:00 本周一
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.MONDAY, 1, false))); // 2021-07-04 00:00:00 本周日
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.TUESDAY, -1, true))); // 2021-06-22 00:00:00 上周二
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.TUESDAY, -1, false))); // 2021-06-28 00:00:00 上周一
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.TUESDAY, 0, true))); // 2021-06-29 00:00:00 本周二
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.TUESDAY, 0, false))); // 2021-07-05 00:00:00 本周一
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.TUESDAY, 1, true))); // 2021-06-29 00:00:00 本周二
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.TUESDAY, 1, false))); // 2021-07-05 00:00:00 本周一
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.WEDNESDAY, -1, true))); // 2021-06-23 00:00:00 本周三
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.WEDNESDAY, -1, false))); // 2021-06-29 00:00:00 本周二
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.WEDNESDAY, 0, true))); // 2021-06-23 00:00:00 本周三
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.WEDNESDAY, 0, false))); // 2021-06-29 00:00:00 本周二
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.WEDNESDAY, 1, true))); // 2021-06-30 00:00:00 下周三
        System.out.println(DateUtil.formatToText(getAddWeekPointDate(date, Calendar.WEDNESDAY, 1, false))); // 2021-07-06 00:00:00 下周二
        System.out.println();
    }
}
