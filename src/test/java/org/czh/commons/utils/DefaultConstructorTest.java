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
public class DefaultConstructorTest {

    private String name;
    private int age;

    DefaultConstructorTest() {
        this.name = "default constructor zero params";
        this.age = 0;
    }

    DefaultConstructorTest(String name) {
        this.name = "default constructor one params";
        this.age = 1;
    }

    DefaultConstructorTest(String name, int age) {
        this.name = "default constructor two params";
        this.age = 2;
    }
}