package org.czh.commons.utils.encrypt.symmetric;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
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

    public static String encodeToString(@NotBlankTag String src) {
        return encodeToString(srcStringToArray(src));
    }

    public static String encodeToString(@NotEmptyTag byte[] srcBytes) {
        return dstArrayToString(encode(srcBytes));
    }

    public static byte[] encode(@NotBlankTag String src) {
        return encode(srcStringToArray(src));
    }

    public static byte[] encode(@NotEmptyTag byte[] srcBytes) {
        EmptyAssert.isNotEmpty(srcBytes);
        return Base64.getEncoder().encode(srcBytes);
    }

    /*
        解密
     */

    public static String decodeToString(@NotBlankTag String dst) {
        return decodeToString(dstStringToArray(dst));
    }

    public static String decodeToString(@NotEmptyTag byte[] dstBytes) {
        return srcArrayToString(decode(dstBytes));
    }

    public static byte[] decode(@NotBlankTag String dst) {
        return decode(dstStringToArray(dst));
    }

    public static byte[] decode(@NotEmptyTag byte[] dstBytes) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Base64.getDecoder().decode(dstBytes);
    }

    /*
        校验
     */

    public static boolean verify(@NotBlankTag String src, @NotBlankTag String dst) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encodeToString(src), dst);
    }

    public static boolean verify(@NotBlankTag String src, @NotEmptyTag byte[] dstBytes) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Arrays.equals(encode(src), dstBytes);
    }

    public static boolean verify(@NotEmptyTag byte[] srcBytes, @NotBlankTag String dst) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encodeToString(srcBytes), dst);
    }

    public static boolean verify(@NotEmptyTag byte[] srcBytes, @NotEmptyTag byte[] dstBytes) {
        EmptyAssert.allNotEmpty(srcBytes, dstBytes);
        return Arrays.equals(srcBytes, dstBytes);
    }

    /*
        字节数组 与 字符串 互转
     */
    private static String srcArrayToString(@NotEmptyTag byte[] srcBytes) {
        EmptyAssert.isNotEmpty(srcBytes);
        return new String(srcBytes);
    }

    private static byte[] srcStringToArray(@NotBlankTag String content) {
        EmptyAssert.isNotBlank(content);
        return content.getBytes();
    }

    private static String dstArrayToString(@NotEmptyTag byte[] dstBytes) {
        EmptyAssert.isNotEmpty(dstBytes);
        return new String(dstBytes);
    }

    private static byte[] dstStringToArray(@NotBlankTag String dst) {
        EmptyAssert.isNotBlank(dst);
        return dst.getBytes();
    }
}
