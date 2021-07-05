package org.czh.commons.utils.date;

import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.annotations.tag.ValueTag;
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
public final class PointTimeUtil {

    /*
      -----------------------------get before/after year point date-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddYearPointTime(date, -1, true))); // 2020-01-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddYearPointTime(date, -1, false))); // 2020-12-31 23:59:59
     * System.out.println(DateUtil.formatToText(getAddYearPointTime(date, 0, true))); // 2021-01-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddYearPointTime(date, 0, false))); // 2021-12-31 23:59:59
     * System.out.println(DateUtil.formatToText(getAddYearPointTime(date, 1, true))); // 2022-01-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddYearPointTime(date, 1, false))); // 2022-12-31 23:59:59
     * System.out.println();
     */
    public static Date getAddYearPointTime(final int addYearNum, final boolean startOrEnd) {
        return getAddYearPointTime(DateUtil.getNowDate(), addYearNum, startOrEnd);
    }

    public static Date getAddYearPointTime(@NotNullTag final Date date,
                                           final int addYearNum,
                                           final boolean startOrEnd) {
        return getAddYearPointTime(CalendarUtil.getCalendar(date), addYearNum, startOrEnd);
    }

    public static Date getAddYearPointTime(@NotNullTag final Calendar calendar,
                                           final int addYearNum,
                                           final boolean startOrEnd) {
        return getYearPointTime(calendar, CalendarUtil.getYear(calendar) + addYearNum, startOrEnd);
    }

    public static Date getYearPointTime(final int year, final boolean startOrEnd) {
        return getYearPointTime(CalendarUtil.getCalendar(), year, startOrEnd);
    }

    public static Date getYearPointTime(@NotNullTag final Calendar calendar, final int year, final boolean startOrEnd) {
        EmptyAssert.isNotNull(calendar);

        calendar.clear();
        if (startOrEnd) {
            calendar.set(Calendar.YEAR, year);
            return calendar.getTime();
        } else {
            calendar.set(Calendar.YEAR, year + 1);
            return CalendarUtil.getAddSecondDate(calendar, -1);
        }
    }

    /*
      -----------------------------get before/after quarter point date-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddQuarterPointTime(date, -1, true))); // 2021-04-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddQuarterPointTime(date, -1, false))); // 2021-06-30 23:59:59
     * System.out.println(DateUtil.formatToText(getAddQuarterPointTime(date, 0, true))); // 2021-04-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddQuarterPointTime(date, 0, false))); // 2021-06-30 23:59:59
     * System.out.println(DateUtil.formatToText(getAddQuarterPointTime(date, 1, true))); // 2021-07-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddQuarterPointTime(date, 1, false))); // 2021-09-30 23:59:59
     * System.out.println();
     */
    public static Date getAddQuarterPointTime(final int addMonthNum, final boolean startOrEnd) {
        return getAddQuarterPointTime(DateUtil.getNowDate(), addMonthNum, startOrEnd);
    }

    public static Date getAddQuarterPointTime(@NotNullTag final Date date,
                                              final int addMonthNum,
                                              final boolean startOrEnd) {
        return getAddQuarterPointTime(CalendarUtil.getCalendar(date), addMonthNum, startOrEnd);
    }

    public static Date getAddQuarterPointTime(@NotNullTag final Calendar calendar,
                                              final int addMonthNum,
                                              final boolean startOrEnd) {
        return getQuarterPointTime(
                calendar,
                CalendarUtil.getYear(calendar),
                CalendarUtil.getMonth(calendar) + addMonthNum,
                startOrEnd
        );
    }

    public static Date getQuarterPointTime(final int year, final int month, final boolean startOrEnd) {
        return getMonthPointTime(CalendarUtil.getCalendar(), year, month, startOrEnd);
    }

    public static Date getQuarterPointTime(@NotNullTag final Calendar calendar,
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
            return CalendarUtil.getAddSecondDate(calendar, -1);
        }
    }

    /*
      -----------------------------get before/after month point date-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddMonthPointTime(date, -1, true))); // 2021-05-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddMonthPointTime(date, -1, false))); // 2021-05-31 23:59:59
     * System.out.println(DateUtil.formatToText(getAddMonthPointTime(date, 0, true))); // 2021-06-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddMonthPointTime(date, 0, false))); // 2021-06-30 23:59:59
     * System.out.println(DateUtil.formatToText(getAddMonthPointTime(date, 1, true))); // 2021-07-01 00:00:00
     * System.out.println(DateUtil.formatToText(getAddMonthPointTime(date, 1, false))); // 2021-07-31 23:59:59
     * System.out.println();
     */
    public static Date getAddMonthPointTime(final int addMonthNum, final boolean startOrEnd) {
        return getAddMonthPointTime(DateUtil.getNowDate(), addMonthNum, startOrEnd);
    }

    public static Date getAddMonthPointTime(@NotNullTag final Date date,
                                            final int addMonthNum,
                                            final boolean startOrEnd) {
        return getAddMonthPointTime(CalendarUtil.getCalendar(date), addMonthNum, startOrEnd);
    }

    public static Date getAddMonthPointTime(@NotNullTag final Calendar calendar,
                                            final int addMonthNum,
                                            final boolean startOrEnd) {
        return getMonthPointTime(
                calendar,
                CalendarUtil.getYear(calendar),
                CalendarUtil.getMonth(calendar) + addMonthNum,
                startOrEnd
        );
    }

    public static Date getMonthPointTime(final int year, final int month, final boolean startOrEnd) {
        return getMonthPointTime(CalendarUtil.getCalendar(), year, month, startOrEnd);
    }

    public static Date getMonthPointTime(@NotNullTag final Calendar calendar,
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
            return CalendarUtil.getAddSecondDate(calendar, -1);
        }
    }

    /*
      -----------------------------get before/after week point date-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * // 周日做星期第一天（当前日期为周二）
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.SUNDAY, -1, true))); // 2021-06-27 00:00:00 本周日
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.SUNDAY, -1, false))); // 2021-07-03 23:59:59 本周六
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.SUNDAY, 0, true))); // 2021-06-27 00:00:00 本周日
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.SUNDAY, 0, false))); // 2021-07-03 23:59:59 本周六
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.SUNDAY, 1, true))); // 2021-06-27 00:00:00 本周日
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.SUNDAY, 1, false))); // 2021-07-03 23:59:59 本周六
     * System.out.println();
     * // 周一做星期第一天（当前日期为周二）
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.MONDAY, -1, true))); // 2021-06-28 00:00:00 本周一
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.MONDAY, -1, false))); // 2021-07-04 23:59:59 本周日
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.MONDAY, 0, true))); // 2021-06-28 00:00:00 本周一
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.MONDAY, 0, false))); // 2021-07-04 23:59:59 本周日
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.MONDAY, 1, true))); // 2021-06-28 00:00:00 本周一
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.MONDAY, 1, false))); // 2021-07-04 23:59:59 本周日
     * System.out.println();
     * // 周二做星期第一天（当前日期为周二）
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.TUESDAY, -1, true))); // 2021-06-22 00:00:00 上周二
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.TUESDAY, -1, false))); // 2021-06-28 23:59:59 上周一
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.TUESDAY, 0, true))); // 2021-06-29 00:00:00 本周二
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.TUESDAY, 0, false))); // 2021-07-05 23:59:59 本周一
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.TUESDAY, 1, true))); // 2021-06-29 00:00:00 本周二
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.TUESDAY, 1, false))); // 2021-07-05 23:59:59 本周一
     * System.out.println();
     * // 周三做星期第一天（当前日期为周二）
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.WEDNESDAY, -1, true))); // 2021-06-23 00:00:00 本周三
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.WEDNESDAY, -1, false))); // 2021-06-29 23:59:59 本周二
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.WEDNESDAY, 0, true))); // 2021-06-23 00:00:00 本周三
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.WEDNESDAY, 0, false))); // 2021-06-29 23:59:59 本周二
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.WEDNESDAY, 1, true))); // 2021-06-30 00:00:00 下周三
     * System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.WEDNESDAY, 1, false))); // 2021-07-06 23:59:59 下周二
     * System.out.println();
     */
    public static Date getAddWeekPointTime(@ValueTag(min = 1, max = 7) final int firstOfWeek,
                                           final int addDayNum,
                                           final boolean startOrEnd) {
        return getAddWeekPointTime(DateUtil.getNowDate(), firstOfWeek, addDayNum, startOrEnd);
    }

    public static Date getAddWeekPointTime(@NotNullTag final Date date,
                                           @ValueTag(min = 1, max = 7) final int firstOfWeek,
                                           final int addDayNum,
                                           final boolean startOrEnd) {
        return getAddWeekPointTime(CalendarUtil.getCalendar(date), firstOfWeek, addDayNum, startOrEnd);
    }

    public static Date getAddWeekPointTime(@NotNullTag final Calendar calendar,
                                           @ValueTag(min = 1, max = 7) final int firstOfWeek,
                                           final int addDayNum,
                                           final boolean startOrEnd) {
        int year = CalendarUtil.getYear(calendar);
        int month = CalendarUtil.getMonth(calendar);
        int dayOfMonth = CalendarUtil.getDayOfMonth(calendar) + addDayNum;
        return getWeekPointTime(calendar, year, month, dayOfMonth, firstOfWeek, startOrEnd);
    }

    public static Date getWeekPointTime(final int year,
                                        final int month,
                                        final int dayOfMonth,
                                        @ValueTag(min = 1, max = 7) final int firstOfWeek,
                                        final boolean startOrEnd) {
        return getWeekPointTime(CalendarUtil.getCalendar(), year, month, dayOfMonth, firstOfWeek, startOrEnd);
    }

    public static Date getWeekPointTime(@NotNullTag final Calendar calendar,
                                        final int year,
                                        final int month,
                                        final int dayOfMonth,
                                        @ValueTag(min = 1, max = 7) final int firstOfWeek,
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
        if (startOrEnd) {
            return calendar.getTime();
        } else {
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            return CalendarUtil.getAddSecondDate(calendar, -1);
        }
    }

    /*
      -----------------------------get before/after day point date-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddDayPointTime(date, -1, true))); // 2021-06-28 00:00:00
     * System.out.println(DateUtil.formatToText(getAddDayPointTime(date, -1, false))); // 2021-06-28 23:59:59
     * System.out.println(DateUtil.formatToText(getAddDayPointTime(date, 0, true))); // 2021-06-29 00:00:00
     * System.out.println(DateUtil.formatToText(getAddDayPointTime(date, 0, false))); // 2021-06-29 23:59:59
     * System.out.println(DateUtil.formatToText(getAddDayPointTime(date, 1, true))); // 2021-06-30 00:00:00
     * System.out.println(DateUtil.formatToText(getAddDayPointTime(date, 1, false))); // 2021-06-30 23:59:59
     * System.out.println();
     */
    public static Date getAddDayPointTime(final int addDayNum, final boolean startOrEnd) {
        return getAddDayPointTime(DateUtil.getNowDate(), addDayNum, startOrEnd);
    }

    public static Date getAddDayPointTime(@NotNullTag final Date date, final int addDayNum, final boolean startOrEnd) {
        return getAddDayPointTime(CalendarUtil.getCalendar(date), addDayNum, startOrEnd);
    }

    public static Date getAddDayPointTime(@NotNullTag final Calendar calendar,
                                          final int addDayNum,
                                          final boolean startOrEnd) {
        int year = CalendarUtil.getYear(calendar);
        int month = CalendarUtil.getMonth(calendar);
        int dayOfMonth = CalendarUtil.getDayOfMonth(calendar) + addDayNum;
        return getDayPointTime(calendar, year, month, dayOfMonth, startOrEnd);
    }

    public static Date getDayPointTime(final int year,
                                       final int month,
                                       final int dayOfMonth,
                                       final boolean startOrEnd) {
        return getDayPointTime(CalendarUtil.getCalendar(), year, month, dayOfMonth, startOrEnd);
    }

    public static Date getDayPointTime(@NotNullTag Calendar calendar,
                                       final int year,
                                       final int month,
                                       final int dayOfMonth,
                                       final boolean startOrEnd) {
        EmptyAssert.isNotNull(calendar);

        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        if (startOrEnd) {
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            return calendar.getTime();
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth + 1);
            return CalendarUtil.getAddSecondDate(calendar, -1);
        }
    }

    /*
      -----------------------------get before/after hour point date-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddHourPointTime(date, -1, true))); // 2021-06-29 11:00:00
     * System.out.println(DateUtil.formatToText(getAddHourPointTime(date, -1, false))); // 2021-06-29 11:59:59
     * System.out.println(DateUtil.formatToText(getAddHourPointTime(date, 0, true))); // 2021-06-29 12:00:00
     * System.out.println(DateUtil.formatToText(getAddHourPointTime(date, 0, false))); // 2021-06-29 12:59:59
     * System.out.println(DateUtil.formatToText(getAddHourPointTime(date, 1, true))); // 2021-06-29 13:00:00
     * System.out.println(DateUtil.formatToText(getAddHourPointTime(date, 1, false))); // 2021-06-29 13:59:59
     * System.out.println();
     */
    public static Date getAddHourPointTime(final int addHourNum, final boolean startOrEnd) {
        return getAddHourPointTime(DateUtil.getNowDate(), addHourNum, startOrEnd);
    }

    public static Date getAddHourPointTime(@NotNullTag final Date date,
                                           final int addHourNum,
                                           final boolean startOrEnd) {
        return getAddHourPointTime(CalendarUtil.getCalendar(date), addHourNum, startOrEnd);
    }

    public static Date getAddHourPointTime(@NotNullTag final Calendar calendar,
                                           final int addHourNum,
                                           final boolean startOrEnd) {
        calendar.clear(Calendar.MINUTE);
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        if (startOrEnd) {
            calendar.add(Calendar.HOUR_OF_DAY, addHourNum);
            return calendar.getTime();
        } else {
            calendar.add(Calendar.HOUR_OF_DAY, addHourNum + 1);
            return CalendarUtil.getAddSecondDate(calendar, -1);
        }
    }

    public static Date getHourPointTime(final int year,
                                        final int month,
                                        final int dayOfMonth,
                                        final int hourOfDay,
                                        final boolean startOrEnd) {
        Calendar calendar = CalendarUtil.getCalendar();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (startOrEnd) {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            return calendar.getTime();
        } else {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay + 1);
            return CalendarUtil.getAddSecondDate(calendar, -1);
        }
    }

    /*
      -----------------------------get before/after minute point date-------------------------------
     */

    /**
     * Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
     * System.out.println(DateUtil.formatToText(getAddMinutePointTime(date, -1, true))); // 2021-06-29 12:28:00
     * System.out.println(DateUtil.formatToText(getAddMinutePointTime(date, -1, false))); // 2021-06-29 12:28:59
     * System.out.println(DateUtil.formatToText(getAddMinutePointTime(date, 0, true))); // 2021-06-29 12:29:00
     * System.out.println(DateUtil.formatToText(getAddMinutePointTime(date, 0, false))); // 2021-06-29 12:29:59
     * System.out.println(DateUtil.formatToText(getAddMinutePointTime(date, 1, true))); // 2021-06-29 12:30:00
     * System.out.println(DateUtil.formatToText(getAddMinutePointTime(date, 1, false))); // 2021-06-29 12:30:59
     * System.out.println();
     */
    public static Date getAddMinutePointTime(final int addMinuteNum, final boolean startOrEnd) {
        return getAddMinutePointTime(DateUtil.getNowDate(), addMinuteNum, startOrEnd);
    }

    public static Date getAddMinutePointTime(@NotNullTag final Date date,
                                             final int addMinuteNum,
                                             final boolean startOrEnd) {
        return getAddMinutePointTime(CalendarUtil.getCalendar(date), addMinuteNum, startOrEnd);
    }

    public static Date getAddMinutePointTime(@NotNullTag final Calendar calendar,
                                             final int addMinuteNum,
                                             final boolean startOrEnd) {
        calendar.clear(Calendar.SECOND);
        calendar.clear(Calendar.MILLISECOND);
        if (startOrEnd) {
            calendar.add(Calendar.MINUTE, addMinuteNum);
            return calendar.getTime();
        } else {
            calendar.add(Calendar.MINUTE, addMinuteNum + 1);
            return CalendarUtil.getAddSecondDate(calendar, -1);
        }
    }

    public static Date getMinutePointTime(final int year,
                                          final int month,
                                          final int dayOfMonth,
                                          final int hourOfDay,
                                          final int minute,
                                          final boolean startOrEnd) {
        Calendar calendar = CalendarUtil.getCalendar();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        if (startOrEnd) {
            calendar.set(Calendar.MINUTE, minute);
            return calendar.getTime();
        } else {
            calendar.set(Calendar.MINUTE, minute + 1);
            return CalendarUtil.getAddSecondDate(calendar, -1);
        }
    }

    /*
      -----------------------------main-------------------------------
     */

    public static void main(String[] args) {
        Date date = DateUtil.parseToDate("2021-06-29 12:29:49");
        System.out.println(DateUtil.formatToText(getAddYearPointTime(date, -1, true))); // 2020-01-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddYearPointTime(date, -1, false))); // 2020-12-31 23:59:59
        System.out.println(DateUtil.formatToText(getAddYearPointTime(date, 0, true))); // 2021-01-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddYearPointTime(date, 0, false))); // 2021-12-31 23:59:59
        System.out.println(DateUtil.formatToText(getAddYearPointTime(date, 1, true))); // 2022-01-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddYearPointTime(date, 1, false))); // 2022-12-31 23:59:59
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddQuarterPointTime(date, -1, true))); // 2021-04-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddQuarterPointTime(date, -1, false))); // 2021-06-30 23:59:59
        System.out.println(DateUtil.formatToText(getAddQuarterPointTime(date, 0, true))); // 2021-04-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddQuarterPointTime(date, 0, false))); // 2021-06-30 23:59:59
        System.out.println(DateUtil.formatToText(getAddQuarterPointTime(date, 1, true))); // 2021-07-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddQuarterPointTime(date, 1, false))); // 2021-09-30 23:59:59
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddMonthPointTime(date, -1, true))); // 2021-05-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddMonthPointTime(date, -1, false))); // 2021-05-31 23:59:59
        System.out.println(DateUtil.formatToText(getAddMonthPointTime(date, 0, true))); // 2021-06-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddMonthPointTime(date, 0, false))); // 2021-06-30 23:59:59
        System.out.println(DateUtil.formatToText(getAddMonthPointTime(date, 1, true))); // 2021-07-01 00:00:00
        System.out.println(DateUtil.formatToText(getAddMonthPointTime(date, 1, false))); // 2021-07-31 23:59:59
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.SUNDAY, -1, true))); // 2021-06-27 00:00:00 本周日
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.SUNDAY, -1, false))); // 2021-07-03 23:59:59 本周六
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.SUNDAY, 0, true))); // 2021-06-27 00:00:00 本周日
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.SUNDAY, 0, false))); // 2021-07-03 23:59:59 本周六
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.SUNDAY, 1, true))); // 2021-06-27 00:00:00 本周日
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.SUNDAY, 1, false))); // 2021-07-03 23:59:59 本周六
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.MONDAY, -1, true))); // 2021-06-28 00:00:00 本周一
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.MONDAY, -1, false))); // 2021-07-04 23:59:59 本周日
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.MONDAY, 0, true))); // 2021-06-28 00:00:00 本周一
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.MONDAY, 0, false))); // 2021-07-04 23:59:59 本周日
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.MONDAY, 1, true))); // 2021-06-28 00:00:00 本周一
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.MONDAY, 1, false))); // 2021-07-04 23:59:59 本周日
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.TUESDAY, -1, true))); // 2021-06-22 00:00:00 上周二
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.TUESDAY, -1, false))); // 2021-06-28 23:59:59 上周一
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.TUESDAY, 0, true))); // 2021-06-29 00:00:00 本周二
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.TUESDAY, 0, false))); // 2021-07-05 23:59:59 本周一
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.TUESDAY, 1, true))); // 2021-06-29 00:00:00 本周二
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.TUESDAY, 1, false))); // 2021-07-05 23:59:59 本周一
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.WEDNESDAY, -1, true))); // 2021-06-23 00:00:00 本周三
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.WEDNESDAY, -1, false))); // 2021-06-29 23:59:59 本周二
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.WEDNESDAY, 0, true))); // 2021-06-23 00:00:00 本周三
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.WEDNESDAY, 0, false))); // 2021-06-29 23:59:59 本周二
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.WEDNESDAY, 1, true))); // 2021-06-30 00:00:00 下周三
        System.out.println(DateUtil.formatToText(getAddWeekPointTime(date, Calendar.WEDNESDAY, 1, false))); // 2021-07-06 23:59:59 下周二
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddDayPointTime(date, -1, true))); // 2021-06-28 00:00:00
        System.out.println(DateUtil.formatToText(getAddDayPointTime(date, -1, false))); // 2021-06-28 23:59:59
        System.out.println(DateUtil.formatToText(getAddDayPointTime(date, 0, true))); // 2021-06-29 00:00:00
        System.out.println(DateUtil.formatToText(getAddDayPointTime(date, 0, false))); // 2021-06-29 23:59:59
        System.out.println(DateUtil.formatToText(getAddDayPointTime(date, 1, true))); // 2021-06-30 00:00:00
        System.out.println(DateUtil.formatToText(getAddDayPointTime(date, 1, false))); // 2021-06-30 23:59:59
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddHourPointTime(date, -1, true))); // 2021-06-29 11:00:00
        System.out.println(DateUtil.formatToText(getAddHourPointTime(date, -1, false))); // 2021-06-29 11:59:59
        System.out.println(DateUtil.formatToText(getAddHourPointTime(date, 0, true))); // 2021-06-29 12:00:00
        System.out.println(DateUtil.formatToText(getAddHourPointTime(date, 0, false))); // 2021-06-29 12:59:59
        System.out.println(DateUtil.formatToText(getAddHourPointTime(date, 1, true))); // 2021-06-29 13:00:00
        System.out.println(DateUtil.formatToText(getAddHourPointTime(date, 1, false))); // 2021-06-29 13:59:59
        System.out.println();

        System.out.println(DateUtil.formatToText(getAddMinutePointTime(date, -1, true))); // 2021-06-29 12:28:00
        System.out.println(DateUtil.formatToText(getAddMinutePointTime(date, -1, false))); // 2021-06-29 12:28:59
        System.out.println(DateUtil.formatToText(getAddMinutePointTime(date, 0, true))); // 2021-06-29 12:29:00
        System.out.println(DateUtil.formatToText(getAddMinutePointTime(date, 0, false))); // 2021-06-29 12:29:59
        System.out.println(DateUtil.formatToText(getAddMinutePointTime(date, 1, true))); // 2021-06-29 12:30:00
        System.out.println(DateUtil.formatToText(getAddMinutePointTime(date, 1, false))); // 2021-06-29 12:30:59
        System.out.println();
    }
}
