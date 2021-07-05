package org.czh.commons.validate;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
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
public final class DateValidate {

    /*
      -----------------------------isAfter-------------------------------
     */

    public static boolean isAfter(@NotBlankTag final String sourceString,
                                  @NotBlankTag final String targetString) {
        return isAfter(sourceString, targetString, DateUtil.getFormat());
    }

    public static boolean isAfter(@NotBlankTag final String sourceString,
                                  @NotBlankTag final String targetString,
                                  @NotBlankTag final String pattern) {
        return isAfter(
                sourceString,
                targetString,
                DateUtil.getFormat(pattern)
        );
    }

    public static boolean isAfter(@NotBlankTag final String sourceString,
                                  @NotBlankTag final String targetString,
                                  @NotBlankTag final String sourcePattern,
                                  @NotBlankTag final String targetPattern) {
        return isAfter(
                sourceString,
                targetString,
                DateUtil.getFormat(sourcePattern),
                DateUtil.getFormat(targetPattern)
        );
    }

    public static boolean isAfter(@NotBlankTag final String sourceString,
                                  @NotBlankTag final String targetString,
                                  @NotNullTag final DateFormat format) {
        return isAfter(sourceString, targetString, format, format);
    }

    public static boolean isAfter(@NotBlankTag final String sourceString,
                                  @NotBlankTag final String targetString,
                                  @NotNullTag final DateTimeFormatter formatter) {
        return isAfter(sourceString, targetString, formatter, formatter);
    }

    public static boolean isAfter(@NotBlankTag final String sourceString,
                                  @NotBlankTag final String targetString,
                                  @NotNullTag final DateFormat sourceFormat,
                                  @NotNullTag final DateFormat targetFormat) {
        Date sourceDate = DateUtil.parseToDate(sourceString, sourceFormat);
        Date targetDate = DateUtil.parseToDate(targetString, targetFormat);
        return isAfter(sourceDate, targetDate);
    }

    public static boolean isAfter(@NotBlankTag final String sourceString,
                                  @NotBlankTag final String targetString,
                                  @NotNullTag final DateTimeFormatter sourceFormatter,
                                  @NotNullTag final DateTimeFormatter targetFormatter) {
        LocalDateTime sourceDate = DateUtil.parseToLDTime(sourceString, sourceFormatter);
        LocalDateTime targetDate = DateUtil.parseToLDTime(targetString, targetFormatter);
        return isAfter(sourceDate, targetDate);
    }

    public static boolean isAfter(@NotNullTag final Date sourceDate,
                                  @NotNullTag final Date targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.compareTo(targetDate) > 0;
    }

    public static boolean isAfter(@NotNullTag final LocalDateTime sourceDate,
                                  @NotNullTag final LocalDateTime targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.isAfter(targetDate);
    }

    /*
      -----------------------------isBefore-------------------------------
     */

    public static boolean isBefore(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString) {
        return isBefore(sourceString, targetString, DateUtil.getFormat());
    }

    public static boolean isBefore(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString,
                                   @NotBlankTag final String pattern) {
        return isBefore(
                sourceString,
                targetString,
                DateUtil.getFormat(pattern)
        );
    }

    public static boolean isBefore(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString,
                                   @NotBlankTag final String sourcePattern,
                                   @NotBlankTag final String targetPattern) {
        return isBefore(
                sourceString,
                targetString,
                DateUtil.getFormat(sourcePattern),
                DateUtil.getFormat(targetPattern)
        );
    }

    public static boolean isBefore(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString,
                                   @NotNullTag final DateFormat format) {
        return isBefore(sourceString, targetString, format, format);
    }

    public static boolean isBefore(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString,
                                   @NotNullTag final DateTimeFormatter formatter) {
        return isBefore(sourceString, targetString, formatter, formatter);
    }

    public static boolean isBefore(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString,
                                   @NotNullTag final DateFormat sourceFormat,
                                   @NotNullTag final DateFormat targetFormat) {
        Date sourceDate = DateUtil.parseToDate(sourceString, sourceFormat);
        Date targetDate = DateUtil.parseToDate(targetString, targetFormat);
        return isBefore(sourceDate, targetDate);
    }

    public static boolean isBefore(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString,
                                   @NotNullTag final DateTimeFormatter sourceFormatter,
                                   @NotNullTag final DateTimeFormatter targetFormatter) {
        LocalDateTime sourceDate = DateUtil.parseToLDTime(sourceString, sourceFormatter);
        LocalDateTime targetDate = DateUtil.parseToLDTime(targetString, targetFormatter);
        return isBefore(sourceDate, targetDate);
    }

    public static boolean isBefore(@NotNullTag final Date sourceDate,
                                   @NotNullTag final Date targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.compareTo(targetDate) < 0;
    }

    public static boolean isBefore(@NotNullTag final LocalDateTime sourceDate,
                                   @NotNullTag final LocalDateTime targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.isBefore(targetDate);
    }

    /*
      -----------------------------isEquals-------------------------------
     */

    public static boolean isEquals(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString) {
        return isEquals(sourceString, targetString, DateUtil.getFormat());
    }

    public static boolean isEquals(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString,
                                   @NotBlankTag final String pattern) {
        return isEquals(
                sourceString,
                targetString,
                DateUtil.getFormat(pattern)
        );
    }

    public static boolean isEquals(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString,
                                   @NotBlankTag final String sourcePattern,
                                   @NotBlankTag final String targetPattern) {
        return isEquals(
                sourceString,
                targetString,
                DateUtil.getFormat(sourcePattern),
                DateUtil.getFormat(targetPattern)
        );
    }

    public static boolean isEquals(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString,
                                   @NotNullTag final DateFormat format) {
        return isEquals(sourceString, targetString, format, format);
    }

    public static boolean isEquals(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString,
                                   @NotNullTag final DateTimeFormatter formatter) {
        return isEquals(sourceString, targetString, formatter, formatter);
    }

    public static boolean isEquals(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString,
                                   @NotNullTag final DateFormat sourceFormat,
                                   @NotNullTag final DateFormat targetFormat) {
        Date sourceDate = DateUtil.parseToDate(sourceString, sourceFormat);
        Date targetDate = DateUtil.parseToDate(targetString, targetFormat);
        return isEquals(sourceDate, targetDate);
    }

    public static boolean isEquals(@NotBlankTag final String sourceString,
                                   @NotBlankTag final String targetString,
                                   @NotNullTag final DateTimeFormatter sourceFormatter,
                                   @NotNullTag final DateTimeFormatter targetFormatter) {
        LocalDateTime sourceDate = DateUtil.parseToLDTime(sourceString, sourceFormatter);
        LocalDateTime targetDate = DateUtil.parseToLDTime(targetString, targetFormatter);
        return isEquals(sourceDate, targetDate);
    }

    public static boolean isEquals(@NotNullTag final Date sourceDate,
                                   @NotNullTag final Date targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.compareTo(targetDate) == 0;
    }

    public static boolean isEquals(@NotNullTag final LocalDateTime sourceDate,
                                   @NotNullTag final LocalDateTime targetDate) {
        EmptyAssert.allNotNull(sourceDate, targetDate);
        return sourceDate.isEqual(targetDate);
    }
}
