package org.czh.commons.utils.fastjson.deserializer;

import org.czh.commons.utils.fastjson.ConvertUtil;

/**
 * @author : czh
 * description :
 * date : 2021-07-19
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class TextToIntegerDeserializer extends AbstractContextObjectDeserializer<String, Integer> {

    @Override
    public Integer deserialize(String source, String format) {
        return ConvertUtil.textToInteger(source, format);
    }
}
