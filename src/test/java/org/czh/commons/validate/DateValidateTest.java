package org.czh.commons.validate;

import org.czh.commons.constant.DateConstant;
import org.czh.commons.utils.DateUtil;
import org.czh.commons.utils.date.CalendarUtil;
import org.junit.Test;

import java.time.temporal.ChronoUnit;

/**
 * @author : czh
 * description :
 * date : 2021-07-05
 * email 916419307@qq.com
 */
public class DateValidateTest {

    @Test
    public void testValidate() {
        FlagAssert.isTrue(DateValidate.isAfter("2021-07-05 18:26:05", "2021-07-05 18:25:17"));
        FlagAssert.isTrue(DateValidate.isBefore("2021-07-05 18:23:05", "2021-07-05 18:25:17"));
        FlagAssert.isTrue(DateValidate.isEquals("2021-07-05 18:25:17", "2021-07-05 18:25:17"));
    }

    @Test
    public void testAssert() {
        DateAssert.isAfter("2021-07-05 18:28:08", "2021-07-05 18:27:10");
        DateAssert.isBefore("2021-07-05 18:28:08", "2021-07-05 18:29:10");
        DateAssert.isEquals("2021-07-05 18:28:08", "2021-07-05 18:28:08");

        DateAssert.isAfter("2021-07-05 18:29:27", "2021-07-05 18:29:10", DateConstant.DATETIME_STANDARD());
        DateAssert.isAfter("2021-07-05 18:29:27", "2021-07-05 18:29:10", DateConstant.DATETIME_STANDARD(), DateConstant.DATETIME_STANDARD());
        DateAssert.isAfter("2021-07-05 18:29:27", "2021-07-05 18:29:10", DateUtil.getFormat());
        DateAssert.isAfter("2021-07-05 18:29:27", "2021-07-05 18:29:10", DateUtil.getLDTFormatter());
        DateAssert.isAfter("2021-07-05 18:29:27", "2021-07-05 18:29:10", DateUtil.getFormat(), DateUtil.getFormat());
        DateAssert.isAfter("2021-07-05 18:29:27", "2021-07-05 18:29:10", DateUtil.getLDTFormatter(), DateUtil.getLDTFormatter());
        DateAssert.isAfter(DateUtil.getNowDate(), CalendarUtil.getAddMinuteDate(-1));
        DateAssert.isAfter(DateUtil.getNowLDTime(), DateUtil.getNowLDTime().minus(1, ChronoUnit.MINUTES));

        DateAssert.isBefore("2021-07-05 18:29:10", "2021-07-05 18:29:27", DateConstant.DATETIME_STANDARD());
        DateAssert.isBefore("2021-07-05 18:29:10", "2021-07-05 18:29:27", DateConstant.DATETIME_STANDARD(), DateConstant.DATETIME_STANDARD());
        DateAssert.isBefore("2021-07-05 18:29:10", "2021-07-05 18:29:27", DateUtil.getFormat());
        DateAssert.isBefore("2021-07-05 18:29:10", "2021-07-05 18:29:27", DateUtil.getLDTFormatter());
        DateAssert.isBefore("2021-07-05 18:29:10", "2021-07-05 18:29:27", DateUtil.getFormat(), DateUtil.getFormat());
        DateAssert.isBefore("2021-07-05 18:29:10", "2021-07-05 18:29:27", DateUtil.getLDTFormatter(), DateUtil.getLDTFormatter());
        DateAssert.isBefore(CalendarUtil.getAddMinuteDate(-1), DateUtil.getNowDate());
        DateAssert.isBefore(DateUtil.getNowLDTime().minus(1, ChronoUnit.MINUTES), DateUtil.getNowLDTime());

        DateAssert.isEquals("2021-07-05 18:29:10", "2021-07-05 18:29:10", DateConstant.DATETIME_STANDARD());
        DateAssert.isEquals("2021-07-05 18:29:10", "2021-07-05 18:29:10", DateConstant.DATETIME_STANDARD(), DateConstant.DATETIME_STANDARD());
        DateAssert.isEquals("2021-07-05 18:29:10", "2021-07-05 18:29:10", DateUtil.getFormat());
        DateAssert.isEquals("2021-07-05 18:29:10", "2021-07-05 18:29:10", DateUtil.getLDTFormatter());
        DateAssert.isEquals("2021-07-05 18:29:10", "2021-07-05 18:29:10", DateUtil.getFormat(), DateUtil.getFormat());
        DateAssert.isEquals("2021-07-05 18:29:10", "2021-07-05 18:29:10", DateUtil.getLDTFormatter(), DateUtil.getLDTFormatter());
        DateAssert.isEquals(DateUtil.getNowDate(), DateUtil.getNowDate());
        DateAssert.isEquals(DateUtil.getNowLDTime().minus(0, ChronoUnit.MINUTES), DateUtil.getNowLDTime());
    }
}
