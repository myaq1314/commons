package org.czh.commons.utils;

import lombok.Data;
import lombok.ToString;

/**
 * @author : czh
 * description :
 * date : 2021-05-21
 * email 916419307@qq.com
 */
@Data
@ToString
public class ProtectedConstructorTest {

    private String name;
    private int age;

    protected ProtectedConstructorTest() {
        this.name = "protected constructor zero params";
        this.age = 0;
    }

    protected ProtectedConstructorTest(String name) {
        this.name = "protected constructor one params";
        this.age = 1;
    }

    protected ProtectedConstructorTest(String name, int age) {
        this.name = "protected constructor two params";
        this.age = 2;
    }
}