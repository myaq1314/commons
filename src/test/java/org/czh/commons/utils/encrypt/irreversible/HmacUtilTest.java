package org.czh.commons.utils.encrypt.irreversible;

import org.czh.commons.validate.EqualsAssert;
import org.czh.commons.validate.FlagAssert;
import org.junit.Test;

import java.security.Key;

/**
 * @author : czh
 * description :
 * date : 2021-06-08
 * email 916419307@qq.com
 */
public class HmacUtilTest {

    private String srcString;
    private byte[] srcBytes;
    private String keyString;
    private Key keyBean;
    private String dstString;
    private byte[] dstBytes;

    @Test
    public void test() {
        String key = HmacUtil.getKeyString(HmacUtil.createKeyBean());
        System.out.println(key); // qRSubWiifx9/q/HQffdOGE1jljRe/1BbkEdxEZYOztucPApL1dswCDArlaLo2K5hQg5L3P++iq9W37ekimsYCQ==
        Key keyBean = HmacUtil.getKeyBean(key);

        String src = "123456";
        System.out.println(src); // 123456

        String dst = HmacUtil.encodeToString(src, keyBean);
        String dst2 = HmacUtil.encodeToString(src, keyBean);
        System.out.println(dst); // 1b589c3a31995afd8aa74d21cfa6287e
        FlagAssert.isTrue(HmacUtil.verify(src, dst, keyBean));
        EqualsAssert.isEquals(dst, dst2);
    }

}
