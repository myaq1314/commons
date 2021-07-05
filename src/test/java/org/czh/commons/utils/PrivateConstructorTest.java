package org.czh.commons.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.czh.commons.entity.IBaseEntity;

/**
 * @author : czh
 * description :
 * date : 2021-05-21
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
public class PrivateConstructorTest implements IBaseEntity {

    private static final long serialVersionUID = 8268697209084914943L;

    private String name;
    private int age;

    private PrivateConstructorTest() {
        this.name = "private constructor zero params";
        this.age = 0;
    }

    private PrivateConstructorTest(String name) {
        this.name = "private constructor one params";
        this.age = 1;
    }

    private PrivateConstructorTest(String name, int age) {
        this.name = "private constructor two params";
        this.age = 2;
    }
}