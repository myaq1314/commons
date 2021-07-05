package org.czh.commons.utils.encrypt.symmetric;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.constant.EncryptConstant;
import org.czh.commons.validate.EmptyAssert;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author : czh
 * description : 3DES 即3重DES，使用频率低，效率低
 * date : 2021-06-05
 * email 916419307@qq.com
 */
@SuppressWarnings({"unused", "DuplicatedCode"})
public final class DES3Util {

    /*
        获取公钥
     */
    public static String getKey() {
        return getKey(getSecretKey());
    }

    public static String getKey(@NotNullTag SecretKey secretKey) {
        EmptyAssert.isNotNull(secretKey);
        return keyArrayToString(secretKey.getEncoded());
    }

    public static SecretKey getSecretKey() {
        try {
            SecretKey secretKey = KeyGenerator.getInstance(EncryptConstant.getDES3()).generateKey();
            SecretKeyFactory factory = SecretKeyFactory.getInstance(EncryptConstant.getDES3());
            return factory.generateSecret(new DESedeKeySpec(secretKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知的加密方式");
        } catch (InvalidKeyException | InvalidKeySpecException e) {
            throw new RuntimeException("无效的公钥");
        }
    }

    public static SecretKey getSecretKey(@NotBlankTag String key) {
        byte[] keyBytes = keyStringToArray(key);
        return new SecretKeySpec(keyBytes, EncryptConstant.getDES3());
    }

    /*
        加密
     */

    public static String encodeToString(@NotBlankTag String src, @NotNullTag SecretKey secretKey) {
        return dstArrayToString(encode(src, secretKey));
    }

    public static String encodeToString(@NotEmptyTag byte[] srcBytes, @NotNullTag SecretKey secretKey) {
        return dstArrayToString(encode(srcBytes, secretKey));
    }

    public static byte[] encode(@NotBlankTag String src, @NotNullTag SecretKey secretKey) {
        return encode(srcStringToArray(src), secretKey);
    }

    public static byte[] encode(@NotEmptyTag byte[] srcBytes, @NotNullTag SecretKey key) {
        EmptyAssert.isNotEmpty(srcBytes);
        EmptyAssert.isNotNull(key);

        try {
            Cipher cipher = Cipher.getInstance(EncryptConstant.getDES3Cipher());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(srcBytes);
        } catch (Exception e) {
            throw new RuntimeException("加密失败");
        }
    }

    /*
        解密
     */

    public static String decodeToString(@NotBlankTag String dst, @NotNullTag SecretKey secretKey) {
        return decodeToString(dstStringToArray(dst), secretKey);
    }

    public static String decodeToString(@NotEmptyTag byte[] dstBytes, @NotNullTag SecretKey secretKey) {
        return srcArrayToString(decode(dstBytes, secretKey));
    }

    public static byte[] decode(@NotBlankTag String dst, @NotNullTag SecretKey secretKey) {
        return decode(dstStringToArray(dst), secretKey);
    }

    public static byte[] decode(@NotEmptyTag byte[] dstBytes, @NotNullTag SecretKey key) {
        EmptyAssert.isNotEmpty(dstBytes);
        EmptyAssert.isNotNull(key);

        try {
            Cipher cipher = Cipher.getInstance(EncryptConstant.getDES3Cipher());
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(dstBytes);
        } catch (Exception e) {
            throw new RuntimeException("解密失败");
        }
    }

    /*
        校验
     */
    public static boolean verify(@NotBlankTag String src,
                                 @NotBlankTag String dst,
                                 @NotNullTag SecretKey secretKey) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encodeToString(src, secretKey), dst);
    }

    public static boolean verify(@NotBlankTag String src,
                                 @NotEmptyTag byte[] dstBytes,
                                 @NotNullTag SecretKey secretKey) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Arrays.equals(encode(src, secretKey), dstBytes);
    }

    public static boolean verify(@NotEmptyTag byte[] srcBytes,
                                 @NotBlankTag String dst,
                                 @NotNullTag SecretKey secretKey) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encodeToString(srcBytes, secretKey), dst);
    }

    public static boolean verify(@NotEmptyTag byte[] srcBytes,
                                 @NotEmptyTag byte[] dstBytes,
                                 @NotNullTag SecretKey secretKey) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Arrays.equals(encode(srcBytes, secretKey), dstBytes);
    }

    /*
        字节数组 与 字符串 互转
     */
    private static byte[] keyStringToArray(@NotBlankTag String key) {
        EmptyAssert.isNotBlank(key);
        return Base64Util.decode(key);
    }

    private static String keyArrayToString(@NotEmptyTag byte[] keyBytes) {
        EmptyAssert.isNotEmpty(keyBytes);
        return Base64Util.encodeToString(keyBytes);
    }

    private static byte[] srcStringToArray(@NotBlankTag String src) {
        EmptyAssert.isNotBlank(src);
        return src.getBytes();
    }

    private static String srcArrayToString(@NotEmptyTag byte[] srcBytes) {
        EmptyAssert.isNotEmpty(srcBytes);
        return new String(srcBytes);
    }

    private static byte[] dstStringToArray(@NotBlankTag String dst) {
        EmptyAssert.isNotBlank(dst);
        return Base64Util.decode(dst);
    }

    private static String dstArrayToString(@NotEmptyTag byte[] dstBytes) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Base64Util.encodeToString(dstBytes);
    }
}
