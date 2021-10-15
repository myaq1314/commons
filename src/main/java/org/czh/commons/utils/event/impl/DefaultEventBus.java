package org.czh.commons.utils.event.impl;

import lombok.extern.slf4j.Slf4j;
import org.czh.commons.exceptions.CommonStatusDict;
import org.czh.commons.utils.event.EventListener;
import org.czh.commons.utils.event.IEvent;
import org.czh.commons.utils.event.IEventBus;
import org.czh.commons.utils.event.IListener;
import org.czh.commons_core.exceptions.CommonException;
import org.czh.commons_core.validate.EmptyValidate;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@Slf4j
@SuppressWarnings({"rawtypes", "unchecked", "DuplicatedCode", "unused"})
public class DefaultEventBus implements IEventBus, ApplicationContextAware, InitializingBean {

    private Map<Class<? extends IEvent>, List<IListener>> listenerMap;
    private ApplicationContext applicationContext;

    @Override
    public void publish(IEvent event) {
        List<IListener> listeners = this.listenerMap.get(event.getClass());
        if (EmptyValidate.isEmpty(listeners)) {
            return;
        }

        for (IListener listener : listeners) {
            try {
                listener.onEvent(event);
            } catch (Exception e) {
                log.error("DefaultEventBus catch exception.", e);
                throw new CommonException(CommonStatusDict.SYSTEM_EXCEPTION);
            }
        }
    }

    @Override
    public void afterPropertiesSet() {
        this.listenerMap = new HashMap<>();
        Map<String, IListener> listeners = this.applicationContext.getBeansOfType(IListener.class);
        if (EmptyValidate.isEmpty(listeners)) {
            return;
        }

        listeners.forEach((key, value) -> {
            IListener listener = (IListener) this.applicationContext.getBean(key);
            EventListener eventListener = listener.getClass().getAnnotation(EventListener.class);
            if (EmptyValidate.isNotNull(eventListener)) {
                Class eventClazz = eventListener.event();
                List<IListener> listenerList = this.listenerMap.get(eventClazz);
                if (listenerList == null) {
                    listenerList = new ArrayList<>();
                    listenerList.add(listener);
                    this.listenerMap.put(eventClazz, listenerList);
                } else {
                    listenerList.add(listener);
                }
            }
        });
    }

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
