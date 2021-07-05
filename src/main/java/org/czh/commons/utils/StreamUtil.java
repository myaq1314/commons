package org.czh.commons.utils;

import org.czh.commons.validate.EmptyValidate;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author : czh
 * description :
 * date : 2021-05-20
 * email 916419307@qq.com
 */
public class StreamUtil {

    @SuppressWarnings("DuplicatedCode")
    public static void close(Closeable... closeables) {
        if (EmptyValidate.isEmpty(closeables)) {
            return;
        }

        for (Closeable closeable : closeables) {
            try {
                if (closeable != null) {
                    closeable.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (closeable != null) {
                    try {
                        closeable.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
