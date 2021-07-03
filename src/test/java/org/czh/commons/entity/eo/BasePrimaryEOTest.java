package org.czh.commons.entity.eo;

import org.czh.commons.entity.eo.example.BasePrimaryTestEO;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
public class BasePrimaryEOTest {

    @Test
    public void test() {
        BasePrimaryTestEO condition = new BasePrimaryTestEO();
        condition.setId(10L);

        String builder = " select * from student as a where id = " + condition.getId() + " ; ";
        System.out.println(builder);

        // select * from student as a where id = 10 ;
    }
}
