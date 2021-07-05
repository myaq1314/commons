package org.czh.commons.utils.event;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
public interface IEventBus<E extends IEvent<?>> {

    /**
     * 推送事件
     */
    void publish(E event);

}
