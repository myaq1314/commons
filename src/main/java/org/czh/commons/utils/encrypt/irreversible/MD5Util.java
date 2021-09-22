package org.czh.commons.utils.encrypt.irreversible;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.ValueTag;
import org.czh.commons.constant.EncryptConstant;
import org.czh.commons.utils.RandomUtil;
import org.czh.commons.validate.EmptyAssert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author : czh
 * description :
 * date : 2021-05-29
 * email 916419307@qq.com
 */
@SuppressWarnings({"unused"})
public final class MD5Util {

    /**
     * 获取 盐
     *
     * @param length 盐长度
     */
    public static String getSalt(@ValueTag(min = 1) int length) {
        return RandomUtil.getHexRandom(length);
    }

    /**
     * 加密
     * 没有加盐
     *
     * @param src 需要加密的内容
     * @return 内容密文
     */
    public static String encode16(@NotBlankTag String src) {
        return encode16(src, null);
    }

    /**
     * 加盐加密
     *
     * @param src  需要加密的内容
     * @param salt 盐字符串
     * @return 内容密文
     */
    public static String encode16(@NotBlankTag String src, String salt) {
        return encode32(src, salt).substring(8, 24);
    }

    /**
     * 加密
     * 没有加盐
     *
     * @param src 需要加密的内容
     * @return 内容密文
     */
    public static String encode32(@NotBlankTag String src) {
        return encode32(src, null);
    }

    /**
     * 加盐加密
     *
     * @param src  需要加密的内容
     * @param salt 盐字符串
     * @return 内容密文
     */
    public static String encode32(@NotBlankTag String src, String salt) {
        try {
            MessageDigest md5 = MessageDigest.getInstance(EncryptConstant.getMD5());
            md5.update(srcStringToArray(src, salt));
            return dstArrayToString(md5.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知的加密算法");
        }
    }

    /*
        校验
     */

    public static boolean verify16(@NotBlankTag String src, @NotBlankTag String dst) {
        return verify16(src, dst, null);
    }

    public static boolean verify16(@NotBlankTag String src, @NotBlankTag String dst, String salt) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encode16(src, salt), dst);
    }

    public static boolean verify32(@NotBlankTag String src, @NotBlankTag String dst) {
        return verify32(src, dst, null);
    }

    public static boolean verify32(@NotBlankTag String src, @NotBlankTag String dst, String salt) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encode32(src, salt), dst);
    }

    /*
        字节数组 与 字符串 互转
     */
    public static byte[] srcStringToArray(@NotBlankTag String src, String salt) {
        EmptyAssert.isNotBlank(src);
        return salt == null ? src.getBytes() : (src + salt).getBytes();
    }

    public static String dstArrayToString(@NotEmptyTag byte[] dstBytes) {
        EmptyAssert.isNotEmpty(dstBytes);

        StringBuilder builder = new StringBuilder();
        for (byte dstByte : dstBytes) {
            int i = ((int) dstByte) & 0xff;
            if (i < 16) {
                builder.append("0");
            }
            builder.append(Integer.toHexString(i));
        }
        return builder.toString();
    }
}
