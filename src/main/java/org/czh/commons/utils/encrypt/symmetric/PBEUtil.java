package org.czh.commons.utils.encrypt.symmetric;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.constant.EncryptConstant;
import org.czh.commons.utils.RandomUtil;
import org.czh.commons.validate.EmptyAssert;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author : czh
 * description :
 * date : 2021-06-05
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class PBEUtil {

    /*
        获取盐
     */
    public static String getSaltString() {
        return getSaltString(getSaltArray());
    }

    public static String getSaltString(@NotEmptyTag byte[] saltBytes) {
        return saltArrayToString(saltBytes);
    }

    public static byte[] getSaltArray() {
//        SecureRandom random = new SecureRandom();
//        return random.generateSeed(8);
        String salt = RandomUtil.getHexRandomUUID(null, 4, 4);
        return salt.getBytes();
    }

    public static byte[] getSaltArray(@NotBlankTag String saltString) {
        return saltStringToArray(saltString);
    }

    public static AlgorithmParameterSpec getAlgParamSpec() {
        return getAlgParamSpec(getSaltArray());
    }

    public static AlgorithmParameterSpec getAlgParamSpec(@NotBlankTag String saltString) {
        return getAlgParamSpec(getSaltArray(saltString));
    }

    public static AlgorithmParameterSpec getAlgParamSpec(@NotEmptyTag byte[] saltBytes) {
        EmptyAssert.isNotEmpty(saltBytes);
        return new PBEParameterSpec(saltBytes, 100);
    }

    /*
        使用口令，获取 密钥
     */

    public static SecretKey getSecretKey(@NotBlankTag String password) {
        EmptyAssert.isNotBlank(password);

        try {
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory factory = SecretKeyFactory.getInstance(EncryptConstant.getPBECipher());
            return factory.generateSecret(pbeKeySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知的加密方式");
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的公钥");
        }
    }

    /*
        加密
     */

    public static String encodeToString(@NotBlankTag String src,
                                        @NotNullTag SecretKey secretKey,
                                        @NotNullTag AlgorithmParameterSpec algParamSpec) {
        return dstArrayToString(encode(src, secretKey, algParamSpec));
    }

    public static String encodeToString(@NotEmptyTag byte[] srcBytes,
                                        @NotNullTag SecretKey secretKey,
                                        @NotNullTag AlgorithmParameterSpec algParamSpec) {
        return dstArrayToString(encode(srcBytes, secretKey, algParamSpec));
    }

    public static byte[] encode(@NotBlankTag String src,
                                @NotNullTag SecretKey secretKey,
                                @NotNullTag AlgorithmParameterSpec algParamSpec) {
        return encode(srcStringToArray(src), secretKey, algParamSpec);
    }

    public static byte[] encode(@NotEmptyTag byte[] srcBytes,
                                @NotNullTag SecretKey key,
                                @NotNullTag AlgorithmParameterSpec algParamSpec) {
        EmptyAssert.isNotEmpty(srcBytes);
        EmptyAssert.isNotNull(key);
        EmptyAssert.isNotNull(algParamSpec);

        try {
            Cipher cipher = Cipher.getInstance(EncryptConstant.getPBECipher());
            cipher.init(Cipher.ENCRYPT_MODE, key, algParamSpec);
            return cipher.doFinal(srcBytes);
        } catch (Exception e) {
            throw new RuntimeException("口令加密失败");
        }
    }

    /*
        解密
     */

    public static String decodeToString(@NotBlankTag String dst,
                                        @NotNullTag SecretKey secretKey,
                                        @NotNullTag AlgorithmParameterSpec algParamSpec) {
        return decodeToString(dstStringToArray(dst), secretKey, algParamSpec);
    }

    public static String decodeToString(@NotEmptyTag byte[] dstBytes,
                                        @NotNullTag SecretKey secretKey,
                                        @NotNullTag AlgorithmParameterSpec algParamSpec) {
        return srcArrayToString(decode(dstBytes, secretKey, algParamSpec));
    }

    public static byte[] decode(@NotBlankTag String dst,
                                @NotNullTag SecretKey secretKey,
                                @NotNullTag AlgorithmParameterSpec algParamSpec) {
        return decode(dstStringToArray(dst), secretKey, algParamSpec);
    }

    public static byte[] decode(@NotEmptyTag byte[] dstBytes,
                                @NotNullTag SecretKey key,
                                @NotNullTag AlgorithmParameterSpec algParamSpec) {
        EmptyAssert.isNotEmpty(dstBytes);
        EmptyAssert.isNotNull(key);
        EmptyAssert.isNotNull(algParamSpec);

        try {
            Cipher cipher = Cipher.getInstance(EncryptConstant.getPBECipher());
            cipher.init(Cipher.DECRYPT_MODE, key, algParamSpec);
            return cipher.doFinal(dstBytes);
        } catch (Exception e) {
            throw new RuntimeException("口令解密失败");
        }
    }

    /*
        校验
     */
    public static boolean verify(@NotBlankTag String src,
                                 @NotBlankTag String dst,
                                 @NotNullTag SecretKey secretKey,
                                 @NotNullTag AlgorithmParameterSpec algParamSpec) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encodeToString(src, secretKey, algParamSpec), dst);
    }

    public static boolean verify(@NotBlankTag String src,
                                 @NotEmptyTag byte[] dstBytes,
                                 @NotNullTag SecretKey secretKey,
                                 @NotNullTag AlgorithmParameterSpec algParamSpec) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Arrays.equals(encode(src, secretKey, algParamSpec), dstBytes);
    }

    public static boolean verify(@NotEmptyTag byte[] srcBytes,
                                 @NotBlankTag String dst,
                                 @NotNullTag SecretKey secretKey,
                                 @NotNullTag AlgorithmParameterSpec algParamSpec) {
        EmptyAssert.isNotBlank(dst);
        return Objects.equals(encodeToString(srcBytes, secretKey, algParamSpec), dst);
    }

    public static boolean verify(@NotEmptyTag byte[] srcBytes,
                                 @NotEmptyTag byte[] dstBytes,
                                 @NotNullTag SecretKey secretKey,
                                 @NotNullTag AlgorithmParameterSpec algParamSpec) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Arrays.equals(encode(srcBytes, secretKey, algParamSpec), dstBytes);
    }

    /*
        字节数组 与 字符串 互转
     */

    public static byte[] saltStringToArray(@NotBlankTag String salt) {
        EmptyAssert.isNotBlank(salt);
        return Base64Util.decode(salt);
    }

    public static String saltArrayToString(@NotEmptyTag byte[] saltBytes) {
        EmptyAssert.isNotEmpty(saltBytes);
        return Base64Util.encodeToString(saltBytes);
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
