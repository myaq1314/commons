package org.czh.commons.utils;

import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.validate.EmptyAssert;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
public final class MoneyUtil {

    public static long yuanToFen(@NotNullTag final BigDecimal yuan) {
        EmptyAssert.isNotNull(yuan);
        return yuan.multiply(new BigDecimal(100)).longValue();
    }

    public static BigDecimal fenToYuan(@NotNullTag final Long fen) {
        EmptyAssert.isNotNull(fen);
        return new BigDecimal(fen).divide(new BigDecimal(100), 2, RoundingMode.DOWN);
    }
}
