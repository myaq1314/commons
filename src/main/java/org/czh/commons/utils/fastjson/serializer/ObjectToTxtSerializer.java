package org.czh.commons.utils.fastjson.serializer;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public class ObjectToTxtSerializer extends AbstractContextObjectSerializer<Object, String> {

    @Override
    protected String write(Object source, String format) {
        return source.toString();
    }
}
