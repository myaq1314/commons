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
public class DES3UtilTest {

    @Test
    public void test() {
        String key = DES3Util.getKey(DES3Util.getSecretKey());
        System.out.println(key); // GRMgdcETHBWYjwvxyP5kq1fqAvFF+9nZ
        SecretKey secretKey = DES3Util.getSecretKey(key);

        String src = "123456";
        System.out.println(src); // 123456

        String dst = DES3Util.encodeToString(src, secretKey);
        String dst2 = DES3Util.encodeToString(src, secretKey);
        System.out.println(dst); // YvgtC3p2krw=
        FlagAssert.isTrue(DES3Util.verify(src, dst, secretKey));
        EqualsAssert.isEquals(dst, dst2);

        String src2 = DES3Util.decodeToString(dst, secretKey);
        String src3 = DES3Util.decodeToString(dst, secretKey);
        System.out.println(src2); // 123456
        EqualsAssert.isEquals(src, src2);
        EqualsAssert.isEquals(src2, src3);
    }

}
