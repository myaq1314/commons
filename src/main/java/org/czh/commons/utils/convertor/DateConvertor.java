package org.czh.commons.utils.convertor;

import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-06-17
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class DateConvertor {

    /*
      -----------------------------dates convert to local date-------------------------------
     */
    public static LocalDate convertToLDate(Date date) {
        return convertToLDTime(date).toLocalDate();
    }

    public static LocalDate convertToLDate(LocalDateTime localDateTime) {
        EmptyAssert.isNotNull(localDateTime);

        return localDateTime.toLocalDate();
    }

    /*
      -----------------------------dates convert to local date time-------------------------------
     */
    public static LocalDateTime convertToLDTime(Date date) {
        EmptyAssert.isNotNull(date);

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    public static LocalDateTime convertToLDTime(LocalDate localDate) {
        return convertToLDTime(localDate, null);
    }

    public static LocalDateTime convertToLDTime(LocalDate localDate, LocalTime localTime) {
        EmptyAssert.isNotNull(localDate);

        if (EmptyValidate.isNull(localTime)) {
            return localDate.atStartOfDay();
        }

        return localDate.atTime(localTime);
    }

    /*
      -----------------------------dates convert to local time-------------------------------
     */
    public static LocalTime convertToLTime(Date date) {
        return convertToLDTime(date).toLocalTime();
    }

    public static LocalTime convertToLTime(LocalDateTime localDateTime) {
        EmptyAssert.isNotNull(localDateTime);

        return localDateTime.toLocalTime();
    }

    /*
      -----------------------------dates convert to date-------------------------------
     */

    public static Date convertToDate(LocalDate localDate) {
        return convertToDate(convertToLDTime(localDate));
    }

    public static Date convertToDate(LocalDateTime localDateTime) {
        EmptyAssert.isNotNull(localDateTime);

        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
