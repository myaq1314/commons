package org.czh.commons.utils.encrypt.irreversible;

import org.apache.commons.codec.binary.Hex;
import org.czh.commons.constant.EncryptConstant;
import org.czh.commons.utils.RandomUtil;
import org.czh.commons.validate.EmptyAssert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author : czh
 * description :
 * date : 2021-06-01
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class SHAUtil {

    /*
        盐
     */
    public static String getSalt(int length) {
        return RandomUtil.getHexRandom(length);
    }

    /*
        加密
     */

    public static String encode(String src) {
        return encode(EncryptConstant.SHAType.SHA_1, src);
    }

    public static String encode(EncryptConstant.SHAType shaType, String src) {
        return encode(shaType, src, null);
    }

    public static String encode(EncryptConstant.SHAType shaType, String src, String salt) {
        try {
            MessageDigest shaMd = MessageDigest.getInstance(shaType.key);
            return dstArrayToString(shaMd.digest(srcStringToArray(src, salt)));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知的加密算法");
        }
    }

    /*
        校验
     */

    public static boolean verify(String src, String dst) {
        return verify(EncryptConstant.SHAType.SHA_1, src, dst);
    }

    public static boolean verify(EncryptConstant.SHAType shaType,
                                 String src,
                                 String dst) {
        return verify(shaType, src, dst, null);
    }

    public static boolean verify(EncryptConstant.SHAType shaType,
                                 String src,
                                 String dst,
                                 String salt) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encode(shaType, src, salt), dst);
    }

    /*
        字节数组 与 字符串 互转
     */
    public static byte[] srcStringToArray(String src, String salt) {
        EmptyAssert.isNotBlank(src);
        return salt == null ? src.getBytes() : (src + salt).getBytes();
    }

    public static String dstArrayToString(byte[] dstBytes) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Hex.encodeHexString(dstBytes);
    }
}
