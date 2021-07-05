package org.czh.commons.utils.encrypt.asymmetric;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.annotations.tag.ValueTag;
import org.czh.commons.constant.EncryptConstant;
import org.czh.commons.utils.encrypt.symmetric.Base64Util;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.FlagAssert;

import javax.crypto.Cipher;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author : czh
 * description :
 * 公钥加密，私钥解密；或者是 私钥加密，公钥解密
 * 不能自己加密，自己解密
 * 即不能公钥加密，公钥解密
 * 也不能私钥加密，私钥解密
 * date : 2021-06-07
 * email 916419307@qq.com
 */
public final class RSAUtil {

    /*
        生成 公钥
     */
    public static Map<String, String> createKeyStringMap(@ValueTag(min = 1)  int keySize) {
        FlagAssert.isTrue(keySize >= 512 && keySize <= 65536 && keySize % 64 == 0);

        Map<String, String> keyStringMap = new HashMap<>(2);
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EncryptConstant.getRSA());
            keyPairGenerator.initialize(keySize);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知的加密方式");
        }
        keyStringMap.put(EncryptConstant.getRSAPrivateKey(), getKeyString(keyPair.getPrivate()));
        keyStringMap.put(EncryptConstant.getRSAPublicKey(), getKeyString(keyPair.getPublic()));
        return keyStringMap;
    }

    /*
        公钥 实体 与 字符串 互相转换
     */
    public static String getKeyString(@NotNullTag Key keyBean) {
        EmptyAssert.isNotNull(keyBean);
        return keyBytesToString(keyBean.getEncoded());
    }

    public static PrivateKey getPrivateKey(@NotBlankTag String privateKeyString) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(EncryptConstant.getRSA());
            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyStringToBytes(privateKeyString));
            return keyFactory.generatePrivate(pkcs8KeySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知的加密方式");
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的私钥");
        }
    }

    public static PublicKey getPublicKey(@NotBlankTag String publicKeyString) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance(EncryptConstant.getRSA());
            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyStringToBytes(publicKeyString));
            return keyFactory.generatePublic(x509KeySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("未知的加密方式");
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException("无效的公钥");
        }
    }

    /*
        加密
     */

    public static String encodeToString(@NotBlankTag String srcString, @NotNullTag Key keyBean) {
        return encodeToString(srcStringToBytes(srcString), keyBean);
    }

    public static String encodeToString(@NotEmptyTag byte[] srcBytes, @NotNullTag Key keyBean) {
        return dstBytesToString(encode(srcBytes, keyBean));
    }

    public static byte[] encode(@NotBlankTag String srcString, @NotNullTag Key keyBean) {
        return encode(srcStringToBytes(srcString), keyBean);
    }

    public static byte[] encode(@NotEmptyTag byte[] srcBytes, @NotNullTag Key keyBean) {
        EmptyAssert.isNotEmpty(srcBytes);
        EmptyAssert.isNotNull(keyBean);

        try {
            Cipher cipher = Cipher.getInstance(EncryptConstant.getRSACipher());
            cipher.init(Cipher.ENCRYPT_MODE, keyBean);
            return cipher.doFinal(srcBytes);
        } catch (Exception e) {
            throw new RuntimeException("加密失败");
        }
    }

    /*
        解密
     */

    public static String decodeToString(@NotBlankTag String dstString, @NotNullTag Key keyBean) {
        return decodeToString(dstStringToBytes(dstString), keyBean);
    }

    public static String decodeToString(@NotEmptyTag byte[] dstBytes, @NotNullTag Key keyBean) {
        return srcBytesToString(decode(dstBytes, keyBean));
    }

    public static byte[] decode(@NotBlankTag String dstString, @NotNullTag Key keyBean) {
        return decode(dstStringToBytes(dstString), keyBean);
    }

    public static byte[] decode(@NotEmptyTag byte[] dstBytes, @NotNullTag Key keyBean) {
        EmptyAssert.isNotEmpty(dstBytes);
        EmptyAssert.isNotNull(keyBean);

        try {
            Cipher cipher = Cipher.getInstance(EncryptConstant.getRSACipher());
            cipher.init(Cipher.DECRYPT_MODE, keyBean);
            return cipher.doFinal(dstBytes);
        } catch (Exception e) {
            throw new RuntimeException("解密失败");
        }
    }

    /*
        校验
     */
    public static boolean verify(@NotBlankTag String srcString,
                                 @NotBlankTag String dstString,
                                 @NotNullTag Key keyBean) {
        EmptyAssert.isNotBlank(srcString);
        return Objects.equals(srcString, decodeToString(dstString, keyBean));
    }

    public static boolean verify(@NotBlankTag String srcString,
                                 @NotEmptyTag byte[] dstBytes,
                                 @NotNullTag Key keyBean) {
        EmptyAssert.isNotBlank(srcString);
        return Objects.equals(srcString, decodeToString(dstBytes, keyBean));
    }

    public static boolean verify(@NotEmptyTag byte[] srcBytes,
                                 @NotBlankTag String dstString,
                                 @NotNullTag Key keyBean) {
        EmptyAssert.isNotEmpty(srcBytes);
        return Arrays.equals(srcBytes, decode(dstString, keyBean));
    }

    public static boolean verify(@NotEmptyTag byte[] srcBytes,
                                 @NotEmptyTag byte[] dstBytes,
                                 @NotNullTag Key keyBean) {
        EmptyAssert.isNotEmpty(srcBytes);
        return Arrays.equals(srcBytes, decode(dstBytes, keyBean));
    }

    /*
        字节数组 与 字符串 互转
     */
    private static byte[] keyStringToBytes(@NotBlankTag String keyString) {
        EmptyAssert.isNotBlank(keyString);
        return Base64Util.decode(keyString);
    }

    private static String keyBytesToString(@NotEmptyTag byte[] keyBytes) {
        EmptyAssert.isNotEmpty(keyBytes);
        return Base64Util.encodeToString(keyBytes);
    }

    private static byte[] srcStringToBytes(@NotBlankTag String srcString) {
        EmptyAssert.isNotBlank(srcString);
        return srcString.getBytes();
    }

    private static String srcBytesToString(@NotEmptyTag byte[] srcBytes) {
        EmptyAssert.isNotEmpty(srcBytes);
        return new String(srcBytes);
    }

    private static byte[] dstStringToBytes(@NotBlankTag String dstString) {
        EmptyAssert.isNotBlank(dstString);
        return Base64Util.decode(dstString);
    }

    private static String dstBytesToString(@NotEmptyTag byte[] dstBytes) {
        EmptyAssert.isNotEmpty(dstBytes);
        return Base64Util.encodeToString(dstBytes);
    }
}
