package org.czh.commons.utils.fastjson.serializer;

/**
 * @author : czh
 * description :
 * date : 2021-07-19
 * email 916419307@qq.com
 */
public interface IObjectSerializer<S, T> {

    T serialize(S source, String format);

}
