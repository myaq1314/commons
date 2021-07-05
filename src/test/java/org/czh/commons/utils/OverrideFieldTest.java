package org.czh.commons.utils;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.List;

@SuppressWarnings("unused")
interface Earth extends java.io.Serializable {
    default String getType() {
        return "Earth";
    }
}

/**
 * @author : czh
 * description :
 * date : 2021-05-01
 * email 916419307@qq.com
 */
public class OverrideFieldTest {

    @Test
    public void test() {
        List<Field> fields = FieldUtil.queryFieldList(City.class);
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }

}

@SuppressWarnings({"unused", "FieldCanBeLocal"})
class Country implements Earth {
    private static final long serialVersionUID = 6632680298523103575L;
    public String publicField;
    protected String protectedField;
    String defaultField;
    private final String privateField;

    public Country(String privateField,
                   String protectedField,
                   String defaultField,
                   String publicField) {
        this.privateField = privateField;
        this.protectedField = protectedField;
        this.defaultField = defaultField;
        this.publicField = publicField;
    }
}

@SuppressWarnings({"unused", "FieldCanBeLocal"})
class Province extends Country {
    private static final long serialVersionUID = -4762977430838842849L;
    public Double publicField;
    protected Double protectedField;
    Double defaultField;
    private final Double privateField;

    public Province(Double privateField,
                    Double protectedField,
                    Double defaultField,
                    Double publicField) {
        super(privateField.toString(), protectedField.toString(), defaultField.toString(), publicField.toString());
        this.privateField = privateField;
        this.protectedField = protectedField;
        this.defaultField = defaultField;
        this.publicField = publicField;
    }
}

@SuppressWarnings({"unused", "FieldCanBeLocal"})
class City extends Province {
    private static final long serialVersionUID = 4124330573768932417L;
    public Integer publicField;
    protected Integer protectedField;
    Integer defaultField;
    private final Integer privateField;

    public City(Integer privateField,
                Integer protectedField,
                Integer defaultField,
                Integer publicField) {
        super(privateField.doubleValue(), protectedField.doubleValue(), defaultField.doubleValue(), publicField.doubleValue());
        this.privateField = privateField;
        this.protectedField = protectedField;
        this.defaultField = defaultField;
        this.publicField = publicField;
    }
}