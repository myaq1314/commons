package org.czh.commons.utils.convertor;

import org.czh.commons.constant.DateConstant;
import org.czh.commons.utils.DateUtil;
import org.czh.commons.validate.EqualsAssert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-05-06
 * email 916419307@qq.com
 */
public class LDateConvertorTest {

    @Test
    public void getFormatterTest() {
        DateTimeFormatter formatter = DateUtil.getLDFormatter();
        DateTimeFormatter formatter1 = DateUtil.getFormatter("yyyy-MM-dd");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        EqualsAssert.allEquals(formatter.toString(), formatter1.toString(), formatter2.toString());
    }

    @Test
    public void formatToTextTest() {
        Date date = new Date();
        LocalDate localDate = DateConvertor.convertToLDate(date);

        String text1 = DateUtil.formatToText(localDate);
        String text2 = DateUtil.formatToText(localDate, "yyyy-MM-dd");
        String text3 = DateUtil.formatToText(localDate, DateUtil.getLDFormatter());

        EqualsAssert.allEquals(text1, text2, text3);
    }

    @Test
    public void convertToDateTest() {
        Date date = new Date();
        String text1 = DateUtil.formatToText(date, DateConstant.DATE_STANDARD());

        LocalDate localDate = DateConvertor.convertToLDate(date);
        String text2 = DateUtil.formatToText(localDate, DateConstant.DATE_STANDARD());

        Date date3 = DateConvertor.convertToDate(localDate);
        String text3 = DateUtil.formatToText(date3, DateConstant.DATE_STANDARD());

        EqualsAssert.allEquals(text1, text2, text3);
    }

    @Test
    public void convertToLDTimeTest() {
        Date date = new Date();
        String text1 = DateUtil.formatToText(date, DateConstant.DATE_STANDARD());
        Date date2 = DateUtil.parseToDate(text1, DateConstant.DATE_STANDARD());

        LocalDate localDate = DateConvertor.convertToLDate(date);
        LocalTime localTime = DateConvertor.convertToLTime(date);

        LocalDateTime localDateTime = DateConvertor.convertToLDTime(localDate);
        LocalDateTime localDateTime1 = DateConvertor.convertToLDTime(localDate, localTime);

        String text2 = DateUtil.formatToText(date2);
        String text3 = DateUtil.formatToText(localDateTime);
        EqualsAssert.isEquals(text2, text3);

        String text4 = DateUtil.formatToText(date);
        String text5 = DateUtil.formatToText(localDateTime1);
        EqualsAssert.isEquals(text4, text5);
    }
}
