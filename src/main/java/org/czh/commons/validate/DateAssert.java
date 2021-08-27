package org.czh.commons.validate;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;

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
public final class DateAssert {

    /*
      -----------------------------isAfter-------------------------------
     */

    public static void isAfter(@NotBlankTag final String sourceString, @NotBlankTag final String targetString) {
        if (!DateValidate.isAfter(sourceString, targetString)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be later than the target time");
        }
    }

    public static void isAfter(@NotBlankTag final String sourceString,
                               @NotBlankTag final String targetString,
                               @NotBlankTag final String pattern) {
        if (!DateValidate.isAfter(sourceString, targetString, pattern)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be later than the target time");
        }
    }

    public static void isAfter(@NotBlankTag final String sourceString,
                               @NotBlankTag final String targetString,
                               @NotBlankTag final String sourcePattern,
                               @NotBlankTag final String targetPattern) {
        if (!DateValidate.isAfter(sourceString, targetString, sourcePattern, targetPattern)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be later than the target time");
        }
    }

    public static void isAfter(@NotBlankTag final String sourceString,
                               @NotBlankTag final String targetString,
                               @NotNullTag final DateFormat format) {
        if (!DateValidate.isAfter(sourceString, targetString, format)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be later than the target time");
        }
    }

    public static void isAfter(@NotBlankTag final String sourceString,
                               @NotBlankTag final String targetString,
                               @NotNullTag final DateTimeFormatter formatter) {
        if (!DateValidate.isAfter(sourceString, targetString, formatter)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be later than the target time");
        }
    }

    public static void isAfter(@NotBlankTag final String sourceString,
                               @NotBlankTag final String targetString,
                               @NotNullTag final DateFormat sourceFormat,
                               @NotNullTag final DateFormat targetFormat) {
        if (!DateValidate.isAfter(sourceString, targetString, sourceFormat, targetFormat)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be later than the target time");
        }
    }

    public static void isAfter(@NotBlankTag final String sourceString,
                               @NotBlankTag final String targetString,
                               @NotNullTag final DateTimeFormatter sourceFormatter,
                               @NotNullTag final DateTimeFormatter targetFormatter) {
        if (!DateValidate.isAfter(sourceString, targetString, sourceFormatter, targetFormatter)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be later than the target time");
        }
    }

    public static void isAfter(@NotNullTag final Date sourceDate,
                               @NotNullTag final Date targetDate) {
        if (!DateValidate.isAfter(sourceDate, targetDate)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be later than the target time");
        }
    }

    public static void isAfter(@NotNullTag final LocalDateTime sourceDate,
                               @NotNullTag final LocalDateTime targetDate) {
        if (!DateValidate.isAfter(sourceDate, targetDate)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be later than the target time");
        }
    }

    /*
      -----------------------------isBefore-------------------------------
     */

    public static void isBefore(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString) {
        if (!DateValidate.isBefore(sourceString, targetString)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be earlier than the target time");
        }
    }

    public static void isBefore(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString,
                                @NotBlankTag final String pattern) {
        if (!DateValidate.isBefore(sourceString, targetString, pattern)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be earlier than the target time");
        }
    }

    public static void isBefore(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString,
                                @NotBlankTag final String sourcePattern,
                                @NotBlankTag final String targetPattern) {
        if (!DateValidate.isBefore(sourceString, targetString, sourcePattern, targetPattern)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be earlier than the target time");
        }
    }

    public static void isBefore(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString,
                                @NotNullTag final DateFormat format) {
        if (!DateValidate.isBefore(sourceString, targetString, format)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be earlier than the target time");
        }
    }

    public static void isBefore(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString,
                                @NotNullTag final DateTimeFormatter formatter) {
        if (!DateValidate.isBefore(sourceString, targetString, formatter)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be earlier than the target time");
        }
    }

    public static void isBefore(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString,
                                @NotNullTag final DateFormat sourceFormat,
                                @NotNullTag final DateFormat targetFormat) {
        if (!DateValidate.isBefore(sourceString, targetString, sourceFormat, targetFormat)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be earlier than the target time");
        }
    }

    public static void isBefore(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString,
                                @NotNullTag final DateTimeFormatter sourceFormatter,
                                @NotNullTag final DateTimeFormatter targetFormatter) {
        if (!DateValidate.isBefore(sourceString, targetString, sourceFormatter, targetFormatter)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be earlier than the target time");
        }
    }

    public static void isBefore(@NotNullTag final Date sourceDate,
                                @NotNullTag final Date targetDate) {
        if (!DateValidate.isBefore(sourceDate, targetDate)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be earlier than the target time");
        }
    }

    public static void isBefore(@NotNullTag final LocalDateTime sourceDate,
                                @NotNullTag final LocalDateTime targetDate) {
        if (!DateValidate.isBefore(sourceDate, targetDate)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be earlier than the target time");
        }
    }

    /*
      -----------------------------isEquals-------------------------------
     */

    public static void isEquals(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString) {
        if (!DateValidate.isEquals(sourceString, targetString)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be equals to the target time");
        }
    }

    public static void isEquals(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString,
                                @NotBlankTag final String pattern) {
        if (!DateValidate.isEquals(sourceString, targetString, pattern)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be equals to the target time");
        }
    }

    public static void isEquals(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString,
                                @NotBlankTag final String sourcePattern,
                                @NotBlankTag final String targetPattern) {
        if (!DateValidate.isEquals(sourceString, targetString, sourcePattern, targetPattern)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be equals to the target time");
        }
    }

    public static void isEquals(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString,
                                @NotNullTag final DateFormat format) {
        if (!DateValidate.isEquals(sourceString, targetString, format)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be equals to the target time");
        }
    }

    public static void isEquals(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString,
                                @NotNullTag final DateTimeFormatter formatter) {
        if (!DateValidate.isEquals(sourceString, targetString, formatter)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be equals to the target time");
        }
    }

    public static void isEquals(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString,
                                @NotNullTag final DateFormat sourceFormat,
                                @NotNullTag final DateFormat targetFormat) {
        if (!DateValidate.isEquals(sourceString, targetString, sourceFormat, targetFormat)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be equals to the target time");
        }
    }

    public static void isEquals(@NotBlankTag final String sourceString,
                                @NotBlankTag final String targetString,
                                @NotNullTag final DateTimeFormatter sourceFormatter,
                                @NotNullTag final DateTimeFormatter targetFormatter) {
        if (!DateValidate.isEquals(sourceString, targetString, sourceFormatter, targetFormatter)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be equals to the target time");
        }
    }

    public static void isEquals(@NotNullTag final Date sourceDate,
                                @NotNullTag final Date targetDate) {
        if (!DateValidate.isEquals(sourceDate, targetDate)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be equals to the target time");
        }
    }

    public static void isEquals(@NotNullTag final LocalDateTime sourceDate,
                                @NotNullTag final LocalDateTime targetDate) {
        if (!DateValidate.isEquals(sourceDate, targetDate)) {
            throw new IllegalStateException("[Assertion failed] - The source time should be equals to the target time");
        }
    }
}
