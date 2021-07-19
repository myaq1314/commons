package org.czh.commons.utils.fastjson.deserializer;

import org.czh.commons.utils.fastjson.ConvertUtil;

import java.math.BigDecimal;

/**
 * @author : czh
 * description :
 * date : 2021-07-19
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class LongToDecimalDeserializer extends AbstractContextObjectDeserializer<Long, BigDecimal> {

    @Override
    public BigDecimal deserialize(Long source, String format) {
        return ConvertUtil.longToDecimal(source, format);
    }
}