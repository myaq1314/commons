package org.czh.commons.utils.encrypt.symmetric;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.czh.commons.constant.EncryptConstant;
import org.czh.commons.validate.EmptyAssert;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author : czh
 * description : AES 是 DES 的高级替代
 * date : 2021-06-05
 * email 916419307@qq.com
 */
@SuppressWarnings({"unused", "DuplicatedCode"})
public final class AESUtil {

    /*
        获取公钥
     */
    public static String getKey() {
        return getKey(getSecretKey());
    }

    public static String getKey(SecretKey secretKey) {
        EmptyAssert.isNotNull(secretKey);
        return keyArrayToString(secretKey.getEncoded());
    }

    public static SecretKey getSecretKey() {
        try {
            SecretKey secretKey = KeyGenerator.getInstance(EncryptConstant.getAES()).generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), EncryptConstant.getAES());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知的加密方式");
        }
    }

    public static SecretKey getSecretKey(String key) {
        byte[] keyBytes = keyStringToArray(key);
        return new SecretKeySpec(keyBytes, EncryptConstant.getAES());
    }

    /*
        加密
     */

    public static String encodeToString(String src, SecretKey secretKey) {
        return dstArrayToString(encode(src, secretKey));
    }

    public static String encodeToString(byte[] srcBytes, SecretKey secretKey) {
        return dstArrayToString(encode(srcBytes, secretKey));
    }

    public static byte[] encode(String src, SecretKey secretKey) {
        return encode(srcStringToArray(src), secretKey);
    }

    public static byte[] encode(byte[] srcBytes, SecretKey secretKey) {
        EmptyAssert.isNotEmpty(srcBytes);
        EmptyAssert.isNotNull(secretKey);

        try {
            Cipher cipher = Cipher.getInstance(EncryptConstant.getAESCipher());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(srcBytes);
        } catch (Exception e) {
            throw new RuntimeException("加密失败");
        }
    }

    /*
        解密
     */

    public static String decodeToString(String dst, SecretKey secretKey) {
        return decodeToString(dstStringToArray(dst), secretKey);
    }

    public static String decodeToString(byte[] dstBytes, SecretKey secretKey) {
        return srcArrayToString(decode(dstBytes, secretKey));
    }

    public static byte[] decode(String dst, SecretKey secretKey) {
        return decode(dstStringToArray(dst), secretKey);
    }

    public static byte[] decode(byte[] dstBytes, SecretKey secretKey) {
        EmptyAssert.isNotEmpty(dstBytes);
        EmptyAssert.isNotNull(secretKey);

        try {
            Cipher cipher = Cipher.getInstance(EncryptConstant.getAESCipher());
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(dstBytes);
        } catch (Exception e) {
            throw new RuntimeException("解密失败");
        }
    }

    /*
        校验
     */
    public static boolean verify(String src,
                                 String dst,
                                 SecretKey secretKey) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encodeToString(src, secretKey), dst);
    }

    public static boolean verify(String src,
                                 byte[] dstBytes,
                                 SecretKey secretKey) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Arrays.equals(encode(src, secretKey), dstBytes);
    }

    public static boolean verify(byte[] srcBytes,
                                 String dst,
                                 SecretKey secretKey) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encodeToString(srcBytes, secretKey), dst);
    }

    public static boolean verify(byte[] srcBytes,
                                 byte[] dstBytes,
                                 SecretKey secretKey) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Arrays.equals(encode(srcBytes, secretKey), dstBytes);
    }

    /*
        字节数组 与 字符串 互转
     */
    private static byte[] keyStringToArray(String key) {
        EmptyAssert.isNotBlank(key);
        return Base64Util.decode(key);
    }

    private static String keyArrayToString(byte[] keyBytes) {
        EmptyAssert.isNotEmpty(keyBytes);
        return Base64Util.encodeToString(keyBytes);
    }

    private static byte[] srcStringToArray(String src) {
        EmptyAssert.isNotBlank(src);
        return src.getBytes();
    }

    private static String srcArrayToString(byte[] srcBytes) {
        EmptyAssert.isNotEmpty(srcBytes);
        return new String(srcBytes);
    }

    private static byte[] dstStringToArray(String dst) {
        EmptyAssert.isNotBlank(dst);
        try {
            return Hex.decodeHex(dst);
        } catch (DecoderException e) {
            throw new RuntimeException("无效的密文字符串");
        }
    }

    private static String dstArrayToString(byte[] dstBytes) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Hex.encodeHexString(dstBytes);
    }
}
