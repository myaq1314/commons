package org.czh.commons.exceptions;

import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
public class CommonExceptionTest {

    @Test
    public void test() {
        try {
            throwNewCommonException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void throwNewCommonException() {
        throw new CommonException("通用异常");
    }

}
