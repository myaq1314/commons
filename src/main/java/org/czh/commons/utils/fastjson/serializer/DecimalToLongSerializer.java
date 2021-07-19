package org.czh.commons.utils.fastjson.serializer;

import org.czh.commons.utils.fastjson.ConvertUtil;

import java.math.BigDecimal;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class DecimalToLongSerializer extends AbstractContextObjectSerializer<BigDecimal, Long> {

    @Override
    public Long serialize(BigDecimal source, String format) {
        return ConvertUtil.decimalToLong(source, format);
    }
}
