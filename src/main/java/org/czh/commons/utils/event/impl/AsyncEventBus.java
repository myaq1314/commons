package org.czh.commons.utils.event.impl;

import lombok.extern.slf4j.Slf4j;
import org.czh.commons.exceptions.CommonException;
import org.czh.commons.exceptions.CommonStatusDict;
import org.czh.commons.utils.event.EventListener;
import org.czh.commons.utils.event.IEvent;
import org.czh.commons.utils.event.IEventBus;
import org.czh.commons.utils.event.IListener;
import org.czh.commons.validate.EmptyValidate;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@Slf4j
@SuppressWarnings({"rawtypes", "unchecked", "DuplicatedCode", "unused"})
public class AsyncEventBus implements IEventBus, ApplicationContextAware, InitializingBean {

    private static final int MAX_CONSUMER = 6;
    private Map<Class, List<IListener>> listenerMap;
    private ApplicationContext applicationContext;
    private final ExecutorService consumer = Executors.newFixedThreadPool(MAX_CONSUMER);

    @Override
    public void publish(IEvent event) {
        this.consumer.execute(() -> {
            List<IListener> listeners = this.listenerMap.get(event.getClass());
            if (EmptyValidate.isNotEmpty(listeners)) {
                for (IListener listener : listeners) {
                    try {
                        listener.onEvent(event);
                    } catch (Exception e) {
                        log.error("AsyncEventBus catch exception. ", e);
                        throw new CommonException(CommonStatusDict.SYSTEM_EXCEPTION);
                    }
                }
            }
        });
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
            if (eventListener != null) {
                Class eventClass = eventListener.event();
                List<IListener> listenerList = this.listenerMap.get(eventClass);
                if (listenerList == null) {
                    listenerList = new ArrayList<>();
                    listenerList.add(listener);
                    this.listenerMap.put(eventClass, listenerList);
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
