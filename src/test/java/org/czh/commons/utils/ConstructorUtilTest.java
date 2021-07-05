package org.czh.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-05-21
 * email 916419307@qq.com
 */
@Slf4j
public class ConstructorUtilTest {

    @Test
    public void test() {
        newInstanceTest(PrivateConstructorTest.class);
        System.out.println();
        newInstanceTest(DefaultConstructorTest.class);
        System.out.println();
        newInstanceTest(ProtectedConstructorTest.class);
        System.out.println();
        newInstanceTest(PublicConstructorTest.class);
    }

    private void newInstanceTest(Class<?> clazz) {
        try {
            System.out.println(ConstructorUtil.newInstance(clazz));
        } catch (Exception e) {
            log.error("new instance fail", e);
        }

        try {
            System.out.println(ConstructorUtil.newInstance(ConstructorUtil.newConstructor(clazz)));
        } catch (Exception e) {
            log.error("new instance fail", e);
        }

        try {
            System.out.println(ConstructorUtil.newInstance(ConstructorUtil.newConstructor(clazz, String.class), "pct2"));
        } catch (Exception e) {
            log.error("new instance fail", e);
        }

        try {
            System.out.println(ConstructorUtil.newInstance(ConstructorUtil.newConstructor(clazz, String.class, int.class), "pct3", 3));
        } catch (Exception e) {
            log.error("new instance fail", e);
        }
    }
}
