package org.czh.commons.utils.encrypt.irreversible;

import org.czh.commons.validate.EqualsAssert;
import org.czh.commons.validate.FlagAssert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-06-08
 * email 916419307@qq.com
 */
public class MD5UtilTest {

    @Test
    public void test() {
        String salt = MD5Util.getSalt(16);
        System.out.println(salt); // f2f3b657310a4e30 16位

        String src = "123456";
        System.out.println(src); // 123456

        String dst32 = MD5Util.encode32(src, salt);
        String dst322 = MD5Util.encode32(src, salt);
        System.out.println(dst32); // 2a54aaaf9d6a56f14bcb9a08e84ee7be
        FlagAssert.isTrue(MD5Util.verify32(src, dst32, salt));
        EqualsAssert.isEquals(dst32, dst322);

        String dst16 = MD5Util.encode16(src, salt);
        String dst162 = MD5Util.encode16(src, salt);
        System.out.println(dst16); // 9d6a56f14bcb9a08
        FlagAssert.isTrue(MD5Util.verify16(src, dst16, salt));
        EqualsAssert.isEquals(dst16, dst162);
    }
}
