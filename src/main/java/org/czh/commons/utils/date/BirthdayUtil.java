package org.czh.commons.utils.date;

import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.annotations.tag.ValueTag;
import org.czh.commons.utils.DateUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.FlagAssert;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
public final class BirthdayUtil {

    /*
      -----------------------------get max birthday-------------------------------
     */

    public static String getMaxBirthdayText(@ValueTag(min = 1) final int age) {
        return getMaxBirthdayText(DateUtil.getNowDate(), age);
    }

    public static String getMaxBirthdayText(@NotNullTag final Date endDate, @ValueTag(min = 1) final int age) {
        return getMaxBirthdayText(CalendarUtil.getCalendar(endDate), age);
    }

    public static String getMaxBirthdayText(@NotNullTag final Calendar calendar, @ValueTag(min = 1) final int age) {
        return DateUtil.formatToText(getMaxBirthdayDate(calendar, age));
    }

    public static Date getMaxBirthdayDate(@ValueTag(min = 1) final int age) {
        return getMaxBirthdayDate(DateUtil.getNowDate(), age);
    }

    public static Date getMaxBirthdayDate(@NotNullTag final Date endDate, @ValueTag(min = 1) final int age) {
        return getMaxBirthdayDate(CalendarUtil.getCalendar(endDate), age);
    }

    public static Date getMaxBirthdayDate(@NotNullTag final Calendar calendar, @ValueTag(min = 1) final int age) {
        EmptyAssert.isNotNull(calendar);
        FlagAssert.isTrue(age >= 1);

        calendar.add(Calendar.YEAR, -age - 1);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    /*
      -----------------------------get min birthday-------------------------------
     */

    public static String getMinBirthdayText(@ValueTag(min = 1) final int age) {
        return getMinBirthdayText(DateUtil.getNowDate(), age);
    }

    public static String getMinBirthdayText(@NotNullTag final Date endDate, @ValueTag(min = 1) final int age) {
        return getMinBirthdayText(CalendarUtil.getCalendar(endDate), age);
    }

    public static String getMinBirthdayText(@NotNullTag final Calendar calendar, @ValueTag(min = 1) final int age) {
        return DateUtil.formatToText(getMinBirthdayDate(calendar, age));
    }

    public static Date getMinBirthdayDate(@ValueTag(min = 1) final int age) {
        return getMinBirthdayDate(DateUtil.getNowDate(), age);
    }

    public static Date getMinBirthdayDate(@NotNullTag final Date endDate, @ValueTag(min = 1) final int age) {
        return getMinBirthdayDate(CalendarUtil.getCalendar(endDate), age);
    }

    public static Date getMinBirthdayDate(@NotNullTag final Calendar calendar, @ValueTag(min = 1) final int age) {
        EmptyAssert.isNotNull(calendar);
        FlagAssert.isTrue(age >= 1);

        calendar.add(Calendar.YEAR, -age);
        return calendar.getTime();
    }

    /*
      -----------------------------get min and max birthday-------------------------------
     */

    public static String getMinBirthdayKey() {
        return "minBirthdayKey";
    }

    public static String getMaxBirthdayKey() {
        return "maxBirthdayKey";
    }

    public static Map<String, String> getPointBirthdayTextMap(@ValueTag(min = 1) final int age) {
        return getPointBirthdayTextMap(DateUtil.getNowDate(), age);
    }

    public static Map<String, String> getPointBirthdayTextMap(@NotNullTag final Date endDate,
                                                             @ValueTag(min = 1) final int age) {
        Map<String, Date> pointBirthdayDateMap = getPointBirthdayDateMap(endDate, age);
        EmptyAssert.isNotEmpty(pointBirthdayDateMap);

        Map<String, String> pointBirthdayTextMap = new HashMap<>(pointBirthdayDateMap.size());
        pointBirthdayDateMap.forEach((key, value) -> pointBirthdayTextMap.put(key, DateUtil.formatToText(value)));
        return pointBirthdayTextMap;
    }

    public static Map<String, Date> getPointBirthdayDateMap(@ValueTag(min = 1) final int age) {
        return getPointBirthdayDateMap(DateUtil.getNowDate(), age);
    }

    public static Map<String, Date> getPointBirthdayDateMap(@NotNullTag final Date endDate,
                                                            @ValueTag(min = 1) final int age) {
        Map<String, Date> pointBirthdayDateMap = new HashMap<>(2);
        pointBirthdayDateMap.put(getMinBirthdayKey(), getMinBirthdayDate(endDate, age));
        pointBirthdayDateMap.put(getMaxBirthdayKey(), getMaxBirthdayDate(endDate, age));
        return pointBirthdayDateMap;
    }
}
