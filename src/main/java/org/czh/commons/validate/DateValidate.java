package org.czh.commons.validate;

import org.czh.commons.utils.DateUtil;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-06-28
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class DateValidate {

    /*
      -----------------------------isAfter-------------------------------
     */

    public static boolean isAfter(final String sourceString,
                                  final String targetString) {
        return isAfter(sourceString, targetString, DateUtil.getFormat());
    }

    public static boolean isAfter(final String sourceString,
                                  final String targetString,
                                  final String pattern) {
        return isAfter(
                sourceString,
                targetString,
                DateUtil.getFormat(pattern)
        );
    }

    public static boolean isAfter(final String sourceString,
                                  final String targetString,
                                  final String sourcePattern,
                                  final String targetPattern) {
        return isAfter(
                sourceString,
                targetString,
                DateUtil.getFormat(sourcePattern),
                DateUtil.getFormat(targetPattern)
        );
    }

    public static boolean isAfter(final String sourceString,
                                  final String targetString,
                                  final DateFormat format) {
        return isAfter(sourceString, targetString, format, format);
    }

    public static boolean isAfter(final String sourceString,
                                  final String targetString,
                                  final DateTimeFormatter formatter) {
        return isAfter(sourceString, targetString, formatter, formatter);
    }

    public static boolean isAfter(final String sourceString,
                                  final String targetString,
                                  final DateFormat sourceFormat,
                                  final DateFormat targetFormat) {
        Date sourceDate = DateUtil.parseToDate(sourceString, sourceFormat);
        Date targetDate = DateUtil.parseToDate(targetString, targetFormat);
        return isAfter(sourceDate, targetDate);
    }

    public static boolean isAfter(final String sourceString,
                                  final String targetString,
                                  final DateTimeFormatter sourceFormatter,
                                  final DateTimeFormatter targetFormatter) {
        LocalDateTime sourceDate = DateUtil.parseToLDTime(sourceString, sourceFormatter);
        LocalDateTime targetDate = DateUtil.parseToLDTime(targetString, targetFormatter);
        return isAfter(sourceDate, targetDate);
    }

    public static boolean isAfter(final Date sourceDate,
                                  final Date targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.compareTo(targetDate) > 0;
    }

    public static boolean isAfter(final LocalDateTime sourceDate,
                                  final LocalDateTime targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.isAfter(targetDate);
    }

    /*
      -----------------------------isBefore-------------------------------
     */

    public static boolean isBefore(final String sourceString,
                                   final String targetString) {
        return isBefore(sourceString, targetString, DateUtil.getFormat());
    }

    public static boolean isBefore(final String sourceString,
                                   final String targetString,
                                   final String pattern) {
        return isBefore(
                sourceString,
                targetString,
                DateUtil.getFormat(pattern)
        );
    }

    public static boolean isBefore(final String sourceString,
                                   final String targetString,
                                   final String sourcePattern,
                                   final String targetPattern) {
        return isBefore(
                sourceString,
                targetString,
                DateUtil.getFormat(sourcePattern),
                DateUtil.getFormat(targetPattern)
        );
    }

    public static boolean isBefore(final String sourceString,
                                   final String targetString,
                                   final DateFormat format) {
        return isBefore(sourceString, targetString, format, format);
    }

    public static boolean isBefore(final String sourceString,
                                   final String targetString,
                                   final DateTimeFormatter formatter) {
        return isBefore(sourceString, targetString, formatter, formatter);
    }

    public static boolean isBefore(final String sourceString,
                                   final String targetString,
                                   final DateFormat sourceFormat,
                                   final DateFormat targetFormat) {
        Date sourceDate = DateUtil.parseToDate(sourceString, sourceFormat);
        Date targetDate = DateUtil.parseToDate(targetString, targetFormat);
        return isBefore(sourceDate, targetDate);
    }

    public static boolean isBefore(final String sourceString,
                                   final String targetString,
                                   final DateTimeFormatter sourceFormatter,
                                   final DateTimeFormatter targetFormatter) {
        LocalDateTime sourceDate = DateUtil.parseToLDTime(sourceString, sourceFormatter);
        LocalDateTime targetDate = DateUtil.parseToLDTime(targetString, targetFormatter);
        return isBefore(sourceDate, targetDate);
    }

    public static boolean isBefore(final Date sourceDate,
                                   final Date targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.compareTo(targetDate) < 0;
    }

    public static boolean isBefore(final LocalDateTime sourceDate,
                                   final LocalDateTime targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.isBefore(targetDate);
    }

    /*
      -----------------------------isEquals-------------------------------
     */

    public static boolean isEquals(final String sourceString,
                                   final String targetString) {
        return isEquals(sourceString, targetString, DateUtil.getFormat());
    }

    public static boolean isEquals(final String sourceString,
                                   final String targetString,
                                   final String pattern) {
        return isEquals(
                sourceString,
                targetString,
                DateUtil.getFormat(pattern)
        );
    }

    public static boolean isEquals(final String sourceString,
                                   final String targetString,
                                   final String sourcePattern,
                                   final String targetPattern) {
        return isEquals(
                sourceString,
                targetString,
                DateUtil.getFormat(sourcePattern),
                DateUtil.getFormat(targetPattern)
        );
    }

    public static boolean isEquals(final String sourceString,
                                   final String targetString,
                                   final DateFormat format) {
        return isEquals(sourceString, targetString, format, format);
    }

    public static boolean isEquals(final String sourceString,
                                   final String targetString,
                                   final DateTimeFormatter formatter) {
        return isEquals(sourceString, targetString, formatter, formatter);
    }

    public static boolean isEquals(final String sourceString,
                                   final String targetString,
                                   final DateFormat sourceFormat,
                                   final DateFormat targetFormat) {
        Date sourceDate = DateUtil.parseToDate(sourceString, sourceFormat);
        Date targetDate = DateUtil.parseToDate(targetString, targetFormat);
        return isEquals(sourceDate, targetDate);
    }

    public static boolean isEquals(final String sourceString,
                                   final String targetString,
                                   final DateTimeFormatter sourceFormatter,
                                   final DateTimeFormatter targetFormatter) {
        LocalDateTime sourceDate = DateUtil.parseToLDTime(sourceString, sourceFormatter);
        LocalDateTime targetDate = DateUtil.parseToLDTime(targetString, targetFormatter);
        return isEquals(sourceDate, targetDate);
    }

    public static boolean isEquals(final Date sourceDate,
                                   final Date targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.compareTo(targetDate) == 0;
    }

    public static boolean isEquals(final LocalDateTime sourceDate,
                                   final LocalDateTime targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.isEqual(targetDate);
    }
}
