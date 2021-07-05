package org.czh.commons.utils.encrypt.symmetric;

import org.czh.commons.validate.EqualsAssert;
import org.czh.commons.validate.FlagAssert;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.security.spec.AlgorithmParameterSpec;

/**
 * @author : czh
 * description :
 * date : 2021-06-08
 * email 916419307@qq.com
 */
public class PBEUtilTest {

    @Test
    public void test() {
        String salt = PBEUtil.getSaltString();
        System.out.println(salt); // QTrAtWy+FV4=
        AlgorithmParameterSpec algParamSpec = PBEUtil.getAlgParamSpec(salt);

        String password = "password";
        SecretKey secretKey = PBEUtil.getSecretKey(password);

        String src = "123456";
        System.out.println(src); // 123456

        String dst = PBEUtil.encodeToString(src, secretKey, algParamSpec);
        String dst2 = PBEUtil.encodeToString(src, secretKey, algParamSpec);
        System.out.println(dst); // DRBP6VK8iY8=
        FlagAssert.isTrue(PBEUtil.verify(src, dst, secretKey, algParamSpec));
        EqualsAssert.isEquals(dst, dst2);

        String src2 = PBEUtil.decodeToString(dst, secretKey, algParamSpec);
        String src3 = PBEUtil.decodeToString(dst, secretKey, algParamSpec);
        System.out.println(src); // 123456
        EqualsAssert.isEquals(src, src2);
        EqualsAssert.isEquals(src2, src3);
    }

}
