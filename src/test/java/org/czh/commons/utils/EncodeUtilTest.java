package org.czh.commons.utils;

import org.czh.commons.constant.EncodeConstant;
import org.czh.commons.validate.EqualsAssert;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021/8/28
 * email 916419307@qq.com
 */
public class EncodeUtilTest {

    @Test
    public void test() {
        String utf8FilePath = "/Users/czh/project/java/util/commons/src/test/java/org/czh/commons/validate/file/utf-8.txt";
        EqualsAssert.isEquals(EncodeConstant.UTF8(), EncodeUtil.getFileEncode(utf8FilePath));

        String gbkFilePath = "/Users/czh/project/java/util/commons/src/test/java/org/czh/commons/validate/file/gbk.txt";
        EqualsAssert.isEquals(EncodeConstant.GBK(), EncodeUtil.getFileEncode(gbkFilePath));
    }

}
