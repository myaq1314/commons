package org.czh.commons.utils.event.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.czh.commons.utils.event.IEventBus;
import org.czh.commons.utils.event.IJmsEvent;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Message;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author : czh
 * description :
 * date : 2021-06-29
 * email 916419307@qq.com
 */
@Slf4j
public class JmsEventBus<T> implements IEventBus<IJmsEvent<T>> {

    // 返回 Java 虚拟机可用的处理器数。
    private static final int PARALLELISM = Runtime.getRuntime().availableProcessors();

    @Getter
    @Setter
    private JmsTemplate jmsTemplate;
    private final LinkedBlockingQueue<IJmsEvent<T>> queue = new LinkedBlockingQueue<>(5000);
    private volatile boolean active = false;
    private ExecutorService executorService;

    public void start() {
        EmptyAssert.isNotNull(this.jmsTemplate);
        if (this.active) {
            log.info("JmsEventBus has started.");
        } else {
            this.active = true;
        }
        log.info("JmsEventBus is starting...");

        for (int i = 0; i < PARALLELISM; i++) {
            getExecutorService().execute(new Runnable() {
                IJmsEvent<T> event = null;

                @Override
                public void run() {
                    while (active) {
                        try {
                            event = queue.take();
                            jmsTemplate.send(this.event.getQueueName(), session -> {
                                Message message = session.createTextMessage(event.getMessage());
                                Map<String, Object> properties = event.getProperties();
                                if (EmptyValidate.isNotEmpty(properties)) {
                                    for (Map.Entry<String, Object> entry : properties.entrySet()) {
                                        message.setObjectProperty(entry.getKey(), entry.getValue());
                                    }
                                }
                                return message;
                            });
                        } catch (Exception e) {
                            log.warn(e.getMessage(), e);
                        }
                    }
                }
            });
        }
    }

    public void stop() {
        log.info("JmsEventBus is stopping...");
        active = false;
    }

    @Override
    public void publish(final IJmsEvent<T> event) {
        try {
            queue.add(event);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public ExecutorService getExecutorService() {
        if (this.executorService == null) {
            this.executorService = Executors.newWorkStealingPool();
        }
        return this.executorService;
    }
}
