package org.czh.commons.service;

import org.czh.commons.entity.eo.example.ExampleCommonEO;
import org.czh.commons.entity.eo.example.ExamplePrimaryEO;
import org.czh.commons.entity.eo.example.ExampleQueryEO;
import org.czh.commons.enums.example.ExampleCommonEnum;
import org.czh.commons.enums.example.ExamplePrimaryEnum;
import org.czh.commons.enums.example.ExampleQueryEnum;
import org.czh.commons.service.example.IExampleCommonService;
import org.czh.commons.service.example.IExamplePrimaryService;
import org.czh.commons.service.example.IExampleQueryService;
import org.czh.commons.service.example.impl.ExampleCommonServiceImpl;
import org.czh.commons.service.example.impl.ExamplePrimaryServiceImpl;
import org.czh.commons.service.example.impl.ExampleQueryServiceImpl;
import org.junit.Test;

/**
 * @author : czh
 * description :
 * date : 2021-07-05
 * email 916419307@qq.com
 */
public class ExampleServiceTest {

    @Test
    public void test() {
        IExamplePrimaryService primaryService = new ExamplePrimaryServiceImpl();
        ExamplePrimaryEO examplePrimaryEO = primaryService.createEntity(ExamplePrimaryEnum.ID, 1L);
        System.out.println(examplePrimaryEO);

        IExampleCommonService commonService = new ExampleCommonServiceImpl();
        ExampleCommonEO exampleCommonEO = commonService.createEntity(ExampleCommonEnum.EXAMPLE_NAME, "exampleName");
        System.out.println(exampleCommonEO);

        IExampleQueryService queryService = new ExampleQueryServiceImpl();
        ExampleQueryEO exampleQueryEO = queryService.createEntity(ExampleQueryEnum.PRICE, 34D);
        System.out.println(exampleQueryEO);
    }
}
