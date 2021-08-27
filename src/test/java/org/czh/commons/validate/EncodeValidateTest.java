package org.czh.commons.validate;

import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021/8/27
 * email 916419307@qq.com
 */
public class EncodeValidateTest {

    @Test
    public void test() {
        String utf8FilePath = "/Users/czh/project/java/util/commons/src/test/java/org/czh/commons/validate/file/utf-8.txt";
        FlagAssert.isTrue(EncodeValidate.isUtf8(utf8FilePath));

        String gbkFilePath = "/Users/czh/project/java/util/commons/src/test/java/org/czh/commons/validate/file/gbk.txt";
        FlagAssert.isTrue(EncodeValidate.isGbk(gbkFilePath));
    }
}
