package org.czh.commons.utils.encrypt.symmetric;

import org.czh.commons.validate.EqualsAssert;
import org.czh.commons.validate.FlagAssert;
import org.junit.Test;

import javax.crypto.SecretKey;

/**
 * @author : czh
 * description :
 * date : 2021-06-08
 * email 916419307@qq.com
 */
public class AESUtilTest {

    @Test
    public void test() {
        String key = AESUtil.getKey(AESUtil.getSecretKey());
        System.out.println(key); // YHZcwVrucOTSQ+W2wXZhLA==
        SecretKey secretKey = AESUtil.getSecretKey(key);

        String src = "123456";
        System.out.println(src); // 123456

        String dst = AESUtil.encodeToString(src, secretKey);
        String dst2 = AESUtil.encodeToString(src, secretKey);
        System.out.println(dst); // dd538038f77f4aadb41f0e1b588d49dc
        FlagAssert.isTrue(AESUtil.verify(src, dst, secretKey));
        EqualsAssert.isEquals(dst, dst2);

        String src2 = AESUtil.decodeToString(dst, secretKey);
        String src3 = AESUtil.decodeToString(dst, secretKey);
        System.out.println(src2); // 123456
        EqualsAssert.isEquals(src, src2);
        EqualsAssert.isEquals(src2, src3);
    }

}
