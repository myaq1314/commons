package org.czh.commons.utils.encrypt.symmetric;

import org.czh.commons.validate.EqualsAssert;
import org.czh.commons.validate.FlagAssert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-06-08
 * email 916419307@qq.com
 */
public class Base64UtilTest {

    @Test
    public void test() {
        String src = "123456";
        System.out.println(src); // 123456

        String dst = Base64Util.encodeToString(src);
        String dst2 = Base64Util.encodeToString(src);
        System.out.println(dst); // MTIzNDU2
        EqualsAssert.isEquals(dst, dst2);

        String src2 = Base64Util.decodeToString(dst);
        EqualsAssert.isEquals(src, src2);
        FlagAssert.isTrue(Base64Util.verify(src, dst));
    }

}
