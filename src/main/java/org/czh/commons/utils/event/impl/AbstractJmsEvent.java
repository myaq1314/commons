package org.czh.commons.utils.event.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.czh.commons.utils.event.IJmsEvent;

import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AbstractJmsEvent<T> extends AbstractEvent<T> implements IJmsEvent<T> {

    @Getter
    @Setter
    private String queueName;
    @Getter
    @Setter
    private Map<String, Object> properties;

    public AbstractJmsEvent(T payLoad) {
        super(payLoad);
    }

    public AbstractJmsEvent(T payLoad, String queueName) {
        super(payLoad);
        this.queueName = queueName;
    }

    @Override
    public String getMessage() {
        if (this.payLoad == null) {
            return null;
        }

        if (this.payLoad instanceof String) {
            return (String) payLoad;
        } else {
            return this.payLoad.toString();
        }
    }
}
