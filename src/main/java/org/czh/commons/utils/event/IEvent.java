package org.czh.commons.utils.event;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
public interface IEvent<T> {

    T getPayLoad();

    void setPayLoad(T payLoad);

}
