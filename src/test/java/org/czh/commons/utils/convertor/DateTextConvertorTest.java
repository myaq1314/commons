package org.czh.commons.utils.convertor;

import org.czh.commons.constant.DateConstant;
import org.czh.commons.utils.DateUtil;
import org.czh.commons.validate.EqualsAssert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-05-06
 * email 916419307@qq.com
 */
public class DateTextConvertorTest {

    @Test
    public void convertToDateTest() {
        Date date = new Date();

        // yyyy-MM-dd HH:mm:ss
        String text1 = DateUtil.formatToText(date);
        // yyyy-MM-dd HH:mm:ss SSS
        String text2 = DateUtil.formatToText(date, DateConstant.DATETIME_STANDARD_MILLIS());

        Date date1 = DateUtil.parseToDate(text1);
        Date date2 = DateUtil.parseToDate(text1, DateUtil.getFormat());

        Date date3 = DateUtil.parseToDate(text2, DateConstant.DATETIME_STANDARD_MILLIS());

        EqualsAssert.allEquals(date1, date2);
        EqualsAssert.allEquals(date, date3);
    }

    @Test
    public void convertToLDTimeTest() {
        Date date = new Date();

        // yyyy-MM-dd HH:mm:ss
        String text = DateUtil.formatToText(date);

        LocalDateTime localDateTime1 = DateUtil.parseToLDTime(text);
        LocalDateTime localDateTime2 = DateUtil.parseToLDTime(text, DateConstant.DATETIME_STANDARD());
        LocalDateTime localDateTime3 = DateUtil.parseToLDTime(text, DateUtil.getLDTFormatter());

        String text1 = DateUtil.formatToText(localDateTime1);
        String text2 = DateUtil.formatToText(localDateTime2, DateConstant.DATETIME_STANDARD());
        String text3 = DateUtil.formatToText(localDateTime3, DateUtil.getLDTFormatter());

        EqualsAssert.allEquals(text, text1, text2, text3);
    }

    @Test
    public void convertToLDateTest() {
        Date date = new Date();

        // yyyy-MM-dd
        String text = DateUtil.formatToText(date, DateConstant.DATE_STANDARD());

        LocalDate localDate1 = DateUtil.parseToLDate(text);
        LocalDate localDate2 = DateUtil.parseToLDate(text, DateConstant.DATE_STANDARD());
        LocalDate localDate3 = DateUtil.parseToLDate(text, DateUtil.getLDFormatter());

        String text1 = DateUtil.formatToText(localDate1);
        String text2 = DateUtil.formatToText(localDate2, DateConstant.DATE_STANDARD());
        String text3 = DateUtil.formatToText(localDate3, DateUtil.getLDFormatter());

        EqualsAssert.allEquals(text, text1, text2, text3);
    }

    @Test
    public void convertToLTimeTest() {
        Date date = new Date();

        // HH:mm:ss
        String text = DateUtil.formatToText(date, DateConstant.TIME_STANDARD());

        LocalTime localTime1 = DateUtil.parseToLTime(text);
        LocalTime localTime2 = DateUtil.parseToLTime(text, DateConstant.TIME_STANDARD());
        LocalTime localTime3 = DateUtil.parseToLTime(text, DateUtil.getLTFormatter());

        String text1 = DateUtil.formatToText(localTime1);
        String text2 = DateUtil.formatToText(localTime2, DateConstant.TIME_STANDARD());
        String text3 = DateUtil.formatToText(localTime3, DateUtil.getLTFormatter());

        EqualsAssert.allEquals(text, text1, text2, text3);
    }
}
