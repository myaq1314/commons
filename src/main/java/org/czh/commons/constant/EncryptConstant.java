package org.czh.commons.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IKeyEnum;

/**
 * @author : czh
 * description : 加密 常量 表达式
 * date : 2021-06-04
 * email 916419307@qq.com
 */
public final class EncryptConstant {

    public static String getMD5() {
        return "MD5";
    }

    public static String getHmacMD5() {
        return "HmacMD5";
    }

    public static String getDES() {
        return "DES";
    }

    public static String getDESCipher() {
        return "DES/ECB/PKCS5Padding";
    }

    public static String getDES3() {
        return "DESede";
    }

    public static String getDES3Cipher() {
        return "DESede/ECB/PKCS5Padding";
    }

    public static String getAES() {
        return "AES";
    }

    public static String getAESCipher() {
        return "AES/ECB/PKCS5Padding";
    }

    public static String getPBE() {
        return "PBE";
    }

    public static String getPBECipher() {
        return "PBEWITHMD5andDES";
    }

    public static String getRSAPublicKey() {
        return "RSAPublicKey";
    }

    public static String getRSAPrivateKey() {
        return "RSAPrivateKey";
    }

    public static String getRSA() {
        return "RSA";
    }

    public static String getRSACipher() {
        return "RSA";
    }


    @AllArgsConstructor

    public enum SHAType implements IKeyEnum<String> {
        SHA_1("SHA-1"), // 40位 适用范围 不超过 2^64 二进制位
        SHA_224("SHA-224"), // 56位 适用范围 不超过 2^64 二进制位
        SHA_256("SHA-256"), // 64位 适用范围 不超过 2^64 二进制位
        SHA_384("SHA-384"), // 96位 适用范围 不超过 2^128 二进制位
        SHA_512("SHA-512") // 128位 适用范围 不超过 2^128 二进制位

        // 占位符
        ;

        @Getter
        public final String key;

    }
}
