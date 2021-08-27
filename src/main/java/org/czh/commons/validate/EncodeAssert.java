package org.czh.commons.validate;

import java.io.File;

/**
 * @author : czh
 * description : 编码格式 断言
 * date : 2021/8/27
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class EncodeAssert {

    public static void isUtf8(final String filePath) {
        isUtf8(filePath, "[Assertion failed] - this file must be utf8 encode");
    }

    public static void isUtf8(final String filePath, final String message) {
        if (!EncodeValidate.isUtf8(filePath)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isUtf8(final File file) {
        isUtf8(file, "[Assertion failed] - this file must be utf8 encode");
    }

    public static void isUtf8(final File file, final String message) {
        if (!EncodeValidate.isUtf8(file)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isGbk(final String filePath) {
        isGbk(filePath, "[Assertion failed] - this file must be gbk encode");
    }

    public static void isGbk(final String filePath, final String message) {
        if (!EncodeValidate.isGbk(filePath)) {
            throw new IllegalStateException(message);
        }
    }

    public static void isGbk(final File file) {
        isGbk(file, "[Assertion failed] - this file must be gbk encode");
    }

    public static void isGbk(final File file, final String message) {
        if (!EncodeValidate.isGbk(file)) {
            throw new IllegalStateException(message);
        }
    }
}
