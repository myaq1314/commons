package org.czh.commons.utils.fastjson.deserializer;

import org.czh.commons.utils.fastjson.ConvertUtil;

/**
 * @author : czh
 * description :
 * date : 2021-07-21
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class TextToShortDeserializer extends AbstractContextObjectDeserializer<String, Short> {

    @Override
    public Short deserialize(String source, String format) {
        return ConvertUtil.textToShort(source, format);
    }
}