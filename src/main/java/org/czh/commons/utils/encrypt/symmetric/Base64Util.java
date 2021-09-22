package org.czh.commons.utils.encrypt.symmetric;

import org.czh.commons.validate.EmptyAssert;

import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;

/**
 * @author : czh
 * description :
 * date : 2021-06-02
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class Base64Util {

    /*
        加密
     */

    public static String encodeToString(String src) {
        return encodeToString(srcStringToArray(src));
    }

    public static String encodeToString(byte[] srcBytes) {
        return dstArrayToString(encode(srcBytes));
    }

    public static byte[] encode(String src) {
        return encode(srcStringToArray(src));
    }

    public static byte[] encode(byte[] srcBytes) {
        EmptyAssert.isNotEmpty(srcBytes);
        return Base64.getEncoder().encode(srcBytes);
    }

    /*
        解密
     */

    public static String decodeToString(String dst) {
        return decodeToString(dstStringToArray(dst));
    }

    public static String decodeToString(byte[] dstBytes) {
        return srcArrayToString(decode(dstBytes));
    }

    public static byte[] decode(String dst) {
        return decode(dstStringToArray(dst));
    }

    public static byte[] decode(byte[] dstBytes) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Base64.getDecoder().decode(dstBytes);
    }

    /*
        校验
     */

    public static boolean verify(String src, String dst) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encodeToString(src), dst);
    }

    public static boolean verify(String src, byte[] dstBytes) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Arrays.equals(encode(src), dstBytes);
    }

    public static boolean verify(byte[] srcBytes, String dst) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encodeToString(srcBytes), dst);
    }

    public static boolean verify(byte[] srcBytes, byte[] dstBytes) {
        EmptyAssert.allNotEmpty(srcBytes, dstBytes);
        return Arrays.equals(srcBytes, dstBytes);
    }

    /*
        字节数组 与 字符串 互转
     */
    private static String srcArrayToString(byte[] srcBytes) {
        EmptyAssert.isNotEmpty(srcBytes);
        return new String(srcBytes);
    }

    private static byte[] srcStringToArray(String content) {
        EmptyAssert.isNotBlank(content);
        return content.getBytes();
    }

    private static String dstArrayToString(byte[] dstBytes) {
        EmptyAssert.isNotEmpty(dstBytes);
        return new String(dstBytes);
    }

    private static byte[] dstStringToArray(String dst) {
        EmptyAssert.isNotBlank(dst);
        return dst.getBytes();
    }
}
