package org.czh.commons.utils.fastjson.serializer;

import org.czh.commons.utils.fastjson.ConvertUtil;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class ObjectToTextSerializer extends AbstractContextObjectSerializer<Object, String> {

    @Override
    public String serialize(Object source, String format) {
        return ConvertUtil.objectToText(source, format);
    }
}
