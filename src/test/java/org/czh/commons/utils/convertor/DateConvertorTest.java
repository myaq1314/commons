package org.czh.commons.utils.convertor;

import org.czh.commons.constant.DateConstant;
import org.czh.commons.utils.DateUtil;
import org.czh.commons.validate.EqualsAssert;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
public class DateConvertorTest {

    @Test
    public void getFormatTest() {
        DateFormat formatter = DateUtil.getFormat();
        DateFormat formatter1 = DateUtil.getFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        EqualsAssert.allEquals(formatter, formatter1, formatter2);
    }

    @Test
    public void formatToTextTest() {
        Date date = new Date();

        String text = DateUtil.formatToText(date);
        String text1 = DateUtil.formatToText(date, "yyyy-MM-dd HH:mm:ss");

        DateFormat formatter2 = DateUtil.getFormat("yyyy-MM-dd HH:mm:ss");
        String text2 = DateUtil.formatToText(date, formatter2);

        EqualsAssert.allEquals(text, text1, text2);
    }

    @Test
    public void convertToLDateTest() {
        Date date = new Date();

        LocalDate localDate = DateConvertor.convertToLDate(date);
        String text = DateUtil.formatToText(localDate);

        String text2 = DateUtil.formatToText(date, DateConstant.DATE_STANDARD());
        EqualsAssert.isEquals(text, text2);
    }

    @Test
    public void convertToLDTime() {
        Date date = new Date();

        LocalDateTime localDateTime = DateConvertor.convertToLDTime(date);
        String text = DateUtil.formatToText(localDateTime);

        String text2 = DateUtil.formatToText(date);
        EqualsAssert.isEquals(text, text2);
    }

    @Test
    public void convertToLTimeTest() {
        Date date = new Date();

        LocalTime localTime = DateConvertor.convertToLTime(date);
        String text = DateUtil.formatToText(localTime);

        String text2 = DateUtil.formatToText(date, DateConstant.TIME_STANDARD());
        EqualsAssert.isEquals(text, text2);
    }
}
