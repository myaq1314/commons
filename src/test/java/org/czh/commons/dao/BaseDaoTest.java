package org.czh.commons.dao;

import org.czh.commons.dao.example.BasePrimaryTestEO;
import org.czh.commons.dao.example.BasePrimaryTestProxyEO;
import org.czh.commons.dao.example.IBasePrimaryTestDao;
import org.junit.Test;

import java.lang.reflect.Proxy;
import java.math.BigDecimal;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
public class BaseDaoTest {

    @Test
    public void test() {
        BasePrimaryTestEO eo = new BasePrimaryTestEO();
        eo.setId(1L);
        eo.setName("TOM");
        eo.setAge(2);
        eo.setScore(new BigDecimal(100));

        IBasePrimaryTestDao dao = (IBasePrimaryTestDao) Proxy.newProxyInstance(
                IBasePrimaryTestDao.class.getClassLoader(),
                new Class[]{IBasePrimaryTestDao.class},
                new BasePrimaryTestProxyEO<>(IBasePrimaryTestDao.class)
        );
        System.out.println(dao.insert(eo));
        System.out.println(dao.update(eo, eo));
        System.out.println(dao.queryMapList(eo));
        System.out.println(dao.getCount(eo));
    }

}
