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
public class DESUtilTest {

    @Test
    public void test() {
        String key = DESUtil.getKey(DESUtil.getSecretKey());
        System.out.println(key); // StnTAeOM/ps=
        SecretKey secretKey = DESUtil.getSecretKey(key);

        String src = "123456";
        System.out.println(src); // 123456

        String dst = DESUtil.encodeToString(src, secretKey);
        String dst2 = DESUtil.encodeToString(src, secretKey);
        System.out.println(dst); // 2M2V0zT++kU=
        FlagAssert.isTrue(DESUtil.verify(src, dst, secretKey));
        EqualsAssert.isEquals(dst, dst2);

        String src2 = DESUtil.decodeToString(dst, secretKey);
        String src3 = DESUtil.decodeToString(dst, secretKey);
        System.out.println(src); // 123456
        EqualsAssert.isEquals(src, src2);
        EqualsAssert.isEquals(src2, src3);
    }

}
