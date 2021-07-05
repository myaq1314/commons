package org.czh.commons.utils.event;

import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
public interface IJmsEvent<T> extends IEvent<T> {

    /**
     * 获取队列名称
     */
    String getQueueName();

    /**
     * 获取消息文本
     */
    String getMessage();

    /**
     * 获取配置参数
     */
    Map<String, Object> getProperties();

}
