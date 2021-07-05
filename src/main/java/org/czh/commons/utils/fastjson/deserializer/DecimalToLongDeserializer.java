package org.czh.commons.utils.fastjson.deserializer;

import org.czh.commons.validate.EmptyValidate;

import java.math.BigDecimal;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class DecimalToLongDeserializer extends AbstractContextObjectDeserializer<BigDecimal, Long> {

    @Override
    protected Long deserialize(BigDecimal source, String format) {
        BigDecimal rate;
        if (EmptyValidate.isNotEmpty(format)) {
            rate = new BigDecimal(format.trim());
        } else {
            rate = new BigDecimal(100);
        }
        return source.multiply(rate).longValue();
    }
}
