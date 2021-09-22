package org.czh.commons.utils.encrypt.irreversible;

import org.czh.commons.constant.EncryptConstant;
import org.czh.commons.utils.encrypt.symmetric.Base64Util;
import org.czh.commons.validate.EmptyAssert;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author : czh
 * description :
 * date : 2021-06-02
 * email 916419307@qq.com
 */
public final class HmacUtil {

    /*
        生成 公钥
     */

    public static String createKeyString() {
        return getKeyString(createKeyBean());
    }

    public static Key createKeyBean() {
        try {
            return KeyGenerator.getInstance(EncryptConstant.getHmacMD5()).generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知的加密算法");
        }
    }

    /*
        公钥 实体 与 字符串 互相转换
     */
    public static String getKeyString(Key keyBean) {
        EmptyAssert.isNotNull(keyBean);
        return keyBytesToString(keyBean.getEncoded());
    }

    public static Key getKeyBean(String key) {
        return new SecretKeySpec(keyStringToBytes(key), EncryptConstant.getHmacMD5());
    }

    /*
        加密
     */

    public static String encodeToString(String srcString, Key keyBean) {
        return encodeToString(srcStringToBytes(srcString), keyBean);
    }

    public static String encodeToString(byte[] srcBytes, Key keyBean) {
        return dstBytesToString(encode(srcBytes, keyBean));
    }

    public static byte[] encode(String srcString, Key keyBean) {
        EmptyAssert.isNotBlank(srcString);
        return encode(srcStringToBytes(srcString), keyBean);
    }

    public static byte[] encode(byte[] srcBytes, Key keyBean) {
        EmptyAssert.isNotEmpty(srcBytes);
        EmptyAssert.isNotNull(keyBean);

        try {
            Mac mac = Mac.getInstance(EncryptConstant.getHmacMD5());
            mac.init(keyBean);
            return mac.doFinal(srcBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知的加密算法");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("无效的公钥");
        }
    }

    /*
        校验
     */

    public static boolean verify(String srcString,
                                 String dstString,
                                 Key keyBean) {
        EmptyAssert.isNotBlank(dstString);
        return Objects.equals(encodeToString(srcString, keyBean), dstString);
    }

    public static boolean verify(String srcString,
                                 byte[] dstBytes,
                                 Key keyBean) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Arrays.equals(encode(srcString, keyBean), dstBytes);
    }

    public static boolean verify(byte[] srcBytes,
                                 String dstString,
                                 Key keyBean) {
        EmptyAssert.isNotBlank(dstString);
        return Objects.equals(encodeToString(srcBytes, keyBean), dstString);
    }

    public static boolean verify(byte[] srcBytes,
                                 byte[] dstBytes,
                                 Key keyBean) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Arrays.equals(encode(srcBytes, keyBean), dstBytes);
    }

    /*
        字节数组 与 字符串 互转
     */
    private static byte[] keyStringToBytes(String keyString) {
        EmptyAssert.isNotBlank(keyString);
        return Base64Util.decode(keyString);
    }

    private static String keyBytesToString(byte[] keyBytes) {
        EmptyAssert.isNotEmpty(keyBytes);
        return Base64Util.encodeToString(keyBytes);
    }

    private static String dstBytesToString(byte[] dstBytes) {
        return new BigInteger(dstBytes).toString(16);
    }

    private static byte[] srcStringToBytes(String srcString) {
        return srcString.getBytes();
    }
}
