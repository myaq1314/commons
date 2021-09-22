package org.czh.commons.utils;

import org.czh.commons.config.filter.RequestExecutionTimeFilter;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.slf4j.Logger;
import org.slf4j.Marker;

/**
 * @author : czh
 * description :
 * date : 2021-07-13
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class LogUtil {

    private static String formatMsg(String msg) {
        EmptyAssert.isNotNull(msg);
        String uuid = RequestExecutionTimeFilter.getLocalUuid().get();
        if (EmptyValidate.isNull(uuid)) {
            return msg;
        }
        return String.format("uuid:%s\t" + msg, RequestExecutionTimeFilter.getLocalUuid().get());
    }

    public static void info(Logger logger, String msg, Object... arguments) {
        logger.info(formatMsg(msg), arguments);
    }

    public static void info(Logger logger, String msg, Throwable throwable) {
        logger.info(formatMsg(msg), throwable);
    }

    public static void info(Logger logger, Marker marker, String msg, Object... arguments) {
        EmptyAssert.isNotNull(logger);
        logger.info(marker, formatMsg(msg), arguments);
    }

    public static void info(Logger logger, Marker marker, String msg, Throwable throwable) {
        EmptyAssert.isNotNull(logger);
        logger.info(marker, formatMsg(msg), throwable);
    }

    public static void error(Logger logger, String msg, Object... arguments) {
        logger.error(formatMsg(msg), arguments);
    }

    public static void error(Logger logger, String msg, Throwable throwable) {
        logger.error(formatMsg(msg), throwable);
    }

    public static void error(Logger logger, Marker marker, String msg, Object... arguments) {
        EmptyAssert.isNotNull(logger);
        logger.error(marker, formatMsg(msg), arguments);
    }

    public static void error(Logger logger, Marker marker, String msg, Throwable throwable) {
        EmptyAssert.isNotNull(logger);
        logger.error(marker, formatMsg(msg), throwable);
    }

    public static void debug(Logger logger, String msg, Object... arguments) {
        logger.debug(formatMsg(msg), arguments);
    }

    public static void debug(Logger logger, String msg, Throwable throwable) {
        logger.debug(formatMsg(msg), throwable);
    }

    public static void debug(Logger logger, Marker marker, String msg, Object... arguments) {
        EmptyAssert.isNotNull(logger);
        logger.debug(marker, formatMsg(msg), arguments);
    }

    public static void debug(Logger logger, Marker marker, String msg, Throwable throwable) {
        EmptyAssert.isNotNull(logger);
        logger.debug(marker, formatMsg(msg), throwable);
    }

    public static void warn(Logger logger, String msg, Object... arguments) {
        logger.warn(formatMsg(msg), arguments);
    }

    public static void warn(Logger logger, String msg, Throwable throwable) {
        logger.warn(formatMsg(msg), throwable);
    }

    public static void warn(Logger logger, Marker marker, String msg, Object... arguments) {
        EmptyAssert.isNotNull(logger);
        logger.warn(marker, formatMsg(msg), arguments);
    }

    public static void warn(Logger logger, Marker marker, String msg, Throwable throwable) {
        EmptyAssert.isNotNull(logger);
        logger.warn(marker, formatMsg(msg), throwable);
    }
}
