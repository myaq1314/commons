package org.czh.commons.utils.fastjson.deserializer;

/**
 * @author : czh
 * description :
 * date : 2021-07-19
 * email 916419307@qq.com
 */
public interface IObjectDeserializer<S, T> {

    T deserialize(S source, String format);

}
