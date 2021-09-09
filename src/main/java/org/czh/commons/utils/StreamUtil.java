package org.czh.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.czh.commons.validate.EmptyValidate;

/**
 * @author : czh
 * description :
 * date : 2021-05-20
 * email 916419307@qq.com
 */
@Slf4j
public class StreamUtil {

    public static void close(AutoCloseable... closeables) {
        if (EmptyValidate.isEmpty(closeables)) {
            return;
        }

        for (AutoCloseable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (Exception e) {
                log.error("{} 关闭失败:{}", closeable, e.getCause());
            } finally {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (Exception e) {
                        log.error("{} 关闭失败:{}", closeable, e.getCause());
                    }
                }
            }
        }
    }
}
