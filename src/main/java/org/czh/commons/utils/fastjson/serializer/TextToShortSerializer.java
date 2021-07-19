package org.czh.commons.utils.fastjson.serializer;

import org.czh.commons.utils.fastjson.ConvertUtil;

/**
 * @author : czh
 * description :
 * date : 2021-07-21
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class TextToShortSerializer extends AbstractContextObjectSerializer<String, Short> {

    @Override
    public Short serialize(String source, String format) {
        return ConvertUtil.textToShort(source, format);
    }
}