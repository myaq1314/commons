package org.czh.commons.utils.fastjson.serializer;

import org.czh.commons.validate.EmptyValidate;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class LongToDecimalSerializer extends AbstractContextObjectSerializer<Long, BigDecimal> {

    @Override
    protected BigDecimal write(Long source, String format) {
        BigDecimal rate;
        if (EmptyValidate.isNotEmpty(format)) {
            rate = new BigDecimal(format.trim());
        } else {
            rate = new BigDecimal(100);
        }
        return new BigDecimal(source).divide(rate, format.trim().length() - 1, RoundingMode.DOWN).stripTrailingZeros();
    }
}
