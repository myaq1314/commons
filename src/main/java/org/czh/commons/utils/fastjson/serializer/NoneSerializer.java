package org.czh.commons.utils.fastjson.serializer;

/**
 * @author : czh
 * description :
 * date : 2021-07-21
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class NoneSerializer implements IObjectSerializer<Object, Object> {

    @Override
    public Object serialize(Object source, String format) {
        return source;
    }
}