package org.czh.commons.validate;

import org.czh.commons.constant.EncodeConstant;
import org.czh.commons.utils.EncodeUtil;

import java.io.File;

/**
 * @author : czh
 * description : 编码格式 判断
 * date : 2021/8/27
 * email 916419307@qq.com
 */
public final class EncodeValidate {

    public static boolean isUtf8(final String filePath) {
        return EncodeConstant.UTF8().equals(EncodeUtil.getFileEncode(filePath));
    }

    public static boolean isUtf8(final File file) {
        return EncodeConstant.UTF8().equals(EncodeUtil.getFileEncode(file));
    }

    public static boolean isGbk(final String filePath) {
        return EncodeConstant.GBK().equals(EncodeUtil.getFileEncode(filePath));
    }

    public static boolean isGbk(final File file) {
        return EncodeConstant.GBK().equals(EncodeUtil.getFileEncode(file));
    }
}
