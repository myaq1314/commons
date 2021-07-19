package org.czh.commons.utils.fastjson.serializer;

import org.czh.commons.utils.fastjson.ConvertUtil;

/**
 * @author : czh
 * description :
 * date : 2021-07-19
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class TextToIntegerSerializer extends AbstractContextObjectSerializer<String, Integer> {

    @Override
    public Integer serialize(String source, String format) {
        return ConvertUtil.textToInteger(source, format);
    }
}
