package org.czh.commons.utils.fastjson.deserializer;

import org.czh.commons.utils.fastjson.ConvertUtil;

/**
 * @author : czh
 * description :
 * date : 2021-07-19
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class TextToLongDeserializer extends AbstractContextObjectDeserializer<String, Long> {

    @Override
    public Long deserialize(String source, String format) {
        return ConvertUtil.textToLong(source, format);
    }
}
