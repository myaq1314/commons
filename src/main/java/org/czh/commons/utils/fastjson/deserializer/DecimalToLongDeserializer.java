package org.czh.commons.utils.fastjson.deserializer;

import org.czh.commons.utils.fastjson.ConvertUtil;

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
    public Long deserialize(BigDecimal source, String format) {
        return ConvertUtil.decimalToLong(source, format);
    }
}
