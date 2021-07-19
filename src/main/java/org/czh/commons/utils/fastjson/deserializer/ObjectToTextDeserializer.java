package org.czh.commons.utils.fastjson.deserializer;

import org.czh.commons.utils.fastjson.ConvertUtil;

/**
 * @author : czh
 * description :
 * date : 2021-07-19
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class ObjectToTextDeserializer extends AbstractContextObjectDeserializer<Object, String> {

    @Override
    public String deserialize(Object source, String format) {
        return ConvertUtil.objectToText(source, format);
    }
}
