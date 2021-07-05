package org.czh.commons.utils.convertor;

import org.czh.commons.constant.DateConstant;
import org.czh.commons.utils.DateUtil;
import org.czh.commons.validate.EqualsAssert;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author : czh
 * description :
 * date : 2021-05-06
 * email 916419307@qq.com
 */
public class LTimeConvertorTest {

    @Test
    public void getFormatterTest() {
        DateTimeFormatter formatter = DateUtil.getLTFormatter();
        DateTimeFormatter formatter1 = DateUtil.getFormatter("HH:mm:ss");
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm:ss");

        EqualsAssert.allEquals(formatter.toString(), formatter1.toString(), formatter2.toString());
    }

    @Test
    public void formatToTextTest() {
        Date date = new Date();
        LocalTime localTime = DateConvertor.convertToLTime(date);

        String text1 = DateUtil.formatToText(localTime);
        String text2 = DateUtil.formatToText(localTime, DateConstant.TIME_STANDARD());
        String text3 = DateUtil.formatToText(localTime, DateUtil.getLTFormatter());

        EqualsAssert.allEquals(text1, text2, text3);
    }

    @Test
    public void convertToDateTest() {
        Date date = new Date();
        String text1 = DateUtil.formatToText(date, DateConstant.TIME_STANDARD());

        LocalTime localTime = DateConvertor.convertToLTime(date);
        String text2 = DateUtil.formatToText(localTime, DateConstant.TIME_STANDARD());

        EqualsAssert.allEquals(text1, text2);
    }
}
