package org.czh.commons.utils;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.validate.EmptyAssert;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class DateUtil {

    /*
      -----------------------------get formats-------------------------------
     */

    public static DateFormat getFormat() {
        return getFormat("yyyy-MM-dd HH:mm:ss");
    }

    public static DateFormat getFormat(@NotBlankTag final String pattern) {
        EmptyAssert.isNotBlank(pattern, "日期格式文本无效");

        try {
            return new SimpleDateFormat(pattern);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("日期格式文本无效");
        }
    }

    public static DateTimeFormatter getLDTFormatter() {
        return getFormatter("yyyy-MM-dd HH:mm:ss");
    }

    public static DateTimeFormatter getLDFormatter() {
        return getFormatter("yyyy-MM-dd");
    }

    public static DateTimeFormatter getLTFormatter() {
        return getFormatter("HH:mm:ss");
    }

    public static DateTimeFormatter getFormatter(@NotBlankTag final String pattern) {
        EmptyAssert.isNotBlank(pattern, "日期格式文本无效");

        try {
            return DateTimeFormatter.ofPattern(pattern);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("日期格式文本无效");
        }
    }

    /*
      -----------------------------dates format to text-------------------------------
     */

    public static String formatToText(@NotNullTag final Date date) {
        return formatToText(date, DateUtil.getFormat());
    }

    public static String formatToText(@NotNullTag final Date date, @NotBlankTag final String pattern) {
        return formatToText(date, DateUtil.getFormat(pattern));
    }

    public static String formatToText(@NotNullTag final Date date, @NotNullTag final DateFormat format) {
        EmptyAssert.allNotNull(date, format);
        return format.format(date);
    }

    public static String formatToText(@NotNullTag final LocalDate localDate) {
        return formatToText(localDate, DateUtil.getLDFormatter());
    }

    public static String formatToText(@NotNullTag final LocalDateTime localDateTime) {
        return formatToText(localDateTime, DateUtil.getLDTFormatter());
    }

    public static String formatToText(@NotNullTag final LocalTime localTime) {
        return formatToText(localTime, DateUtil.getLTFormatter());
    }

    public static String formatToText(@NotNullTag final TemporalAccessor date, @NotBlankTag final String pattern) {
        return formatToText(date, DateUtil.getFormatter(pattern));
    }

    public static String formatToText(@NotNullTag final TemporalAccessor date,
                                      @NotNullTag final DateTimeFormatter formatter) {
        EmptyAssert.allNotNull(date, formatter);
        return formatter.format(date);
    }

    /*
      -----------------------------text parse to dates-------------------------------
     */
    public static Date parseToDate(@NotBlankTag final String dateText) {
        return parseToDate(dateText, DateUtil.getFormat());
    }

    public static Date parseToDate(@NotBlankTag final String dateText, @NotBlankTag final String pattern) {
        return parseToDate(dateText, DateUtil.getFormat(pattern));
    }

    public static Date parseToDate(@NotBlankTag final String dateText, @NotNullTag final DateFormat formatter) {
        EmptyAssert.isNotBlank(dateText);
        EmptyAssert.isNotNull(formatter);

        try {
            return formatter.parse(dateText);
        } catch (ParseException e) {
            throw new RuntimeException("不匹配的日期时间文本 和 日期时间格式");
        }
    }

    public static LocalDateTime parseToLDTime(String dateText) {
        return parseToLDTime(dateText, DateUtil.getLDTFormatter());
    }

    public static LocalDateTime parseToLDTime(String dateText, String pattern) {
        return parseToLDTime(dateText, DateUtil.getFormatter(pattern));
    }

    public static LocalDateTime parseToLDTime(String dateText, DateTimeFormatter formatter) {
        EmptyAssert.isNotBlank(dateText);
        EmptyAssert.isNotNull(formatter);

        try {
            return LocalDateTime.parse(dateText, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("不匹配的日期时间文本 和 日期时间格式");
        }
    }

    public static LocalDate parseToLDate(String dateText) {
        return parseToLDate(dateText, DateUtil.getLDFormatter());
    }

    public static LocalDate parseToLDate(String dateText, String pattern) {
        return parseToLDate(dateText, DateUtil.getFormatter(pattern));
    }

    public static LocalDate parseToLDate(String dateText, DateTimeFormatter formatter) {
        EmptyAssert.isNotBlank(dateText);
        EmptyAssert.isNotNull(formatter);

        try {
            return LocalDate.parse(dateText, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("不匹配的日期文本 和 日期格式");
        }
    }

    public static LocalTime parseToLTime(String dateText) {
        return parseToLTime(dateText, DateUtil.getLTFormatter());
    }

    public static LocalTime parseToLTime(String dateText, String pattern) {
        return parseToLTime(dateText, DateUtil.getFormatter(pattern));
    }

    public static LocalTime parseToLTime(String dateText, DateTimeFormatter formatter) {
        EmptyAssert.isNotBlank(dateText);
        EmptyAssert.isNotNull(formatter);

        try {
            return LocalTime.parse(dateText, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("不匹配的时间文本 和 时间格式");
        }
    }

    /*
      -----------------------------dates compare-------------------------------
     */

    public static int compare(@NotBlankTag final String sourceString,
                              @NotBlankTag final String targetString) {
        return compare(sourceString, targetString, DateUtil.getFormat());
    }

    public static int compare(@NotBlankTag final String sourceString,
                              @NotBlankTag final String targetString,
                              @NotBlankTag final String pattern) {
        return compare(
                sourceString,
                targetString,
                DateUtil.getFormat(pattern)
        );
    }

    public static int compare(@NotBlankTag final String sourceString,
                              @NotBlankTag final String targetString,
                              @NotBlankTag final String sourcePattern,
                              @NotBlankTag final String targetPattern) {
        return compare(
                sourceString,
                targetString,
                DateUtil.getFormat(sourcePattern),
                DateUtil.getFormat(targetPattern)
        );
    }

    public static int compare(@NotBlankTag final String sourceString,
                              @NotBlankTag final String targetString,
                              @NotNullTag final DateFormat format) {
        return compare(sourceString, targetString, format, format);
    }

    public static int compare(@NotBlankTag final String sourceString,
                              @NotBlankTag final String targetString,
                              @NotNullTag final DateTimeFormatter formatter) {
        return compare(sourceString, targetString, formatter, formatter);
    }

    public static int compare(@NotBlankTag final String sourceString,
                              @NotBlankTag final String targetString,
                              @NotNullTag final DateFormat sourceFormat,
                              @NotNullTag final DateFormat targetFormat) {
        Date sourceDate = parseToDate(sourceString, sourceFormat);
        Date targetDate = parseToDate(targetString, targetFormat);
        return compare(sourceDate, targetDate);
    }

    public static int compare(@NotBlankTag final String sourceString,
                              @NotBlankTag final String targetString,
                              @NotNullTag final DateTimeFormatter sourceFormatter,
                              @NotNullTag final DateTimeFormatter targetFormatter) {
        LocalDateTime sourceDate = parseToLDTime(sourceString, sourceFormatter);
        LocalDateTime targetDate = parseToLDTime(targetString, targetFormatter);
        return compare(sourceDate, targetDate);
    }

    public static int compare(@NotNullTag final Date sourceDate,
                              @NotNullTag final Date targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.compareTo(targetDate);
    }

    public static int compare(@NotNullTag final LocalDateTime sourceDate,
                              @NotNullTag final LocalDateTime targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.compareTo(targetDate);
    }

    /*
      -----------------------------get now date-------------------------------
     */

    public static Date getNowDate() {
        return new Date();
    }

    public static LocalDateTime getNowLDTime() {
        return LocalDateTime.now();
    }

    public static LocalDate getNowLDate() {
        return LocalDate.now();
    }

    public static LocalTime getNowLTime() {
        return LocalTime.now();
    }

    public static String getNowText() {
        return getNowText(getFormat());
    }

    public static String getNowText(@NotBlankTag final String pattern) {
        return getNowText(getFormat(pattern));
    }

    public static String getNowText(@NotNullTag final DateFormat format) {
        return formatToText(getNowDate(), format);
    }
}
