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
public class PublicConstructorTest {

    private String name;
    private int age;

    public PublicConstructorTest() {
        this.name = "public constructor zero params";
        this.age = 0;
    }

    public PublicConstructorTest(String name) {
        this.name = "public constructor one params";
        this.age = 1;
    }

    public PublicConstructorTest(String name, int age) {
        this.name = "public constructor two params";
        this.age = 2;
    }
}