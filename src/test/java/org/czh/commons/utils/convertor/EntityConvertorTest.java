package org.czh.commons.utils.convertor;

import com.alibaba.fastjson.JSONObject;
import org.czh.commons.entity.example.EntityTest;
import org.czh.commons.entity.example.SonTest;
import org.czh.commons.validate.EqualsAssert;
import org.junit.Test;

import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-05-02
 * email 916419307@qq.com
 */
public class EntityConvertorTest {

    @Test
    public void convertToMapTest() {
        SonTest son = new SonTest();
        Map<String, Object> map = EntityConvertor.convertToMap(son);
        System.out.println(map);

        EqualsAssert.isEquals(son.getPPri(), map.get("pPri"));
        EqualsAssert.isEquals(son.getPPro(), map.get("pPro"));
        EqualsAssert.isEquals(son.getPDef(), map.get("pDef"));
        EqualsAssert.isEquals(son.getPPub(), map.get("pPub"));
        EqualsAssert.isEquals(SonTest.getPPriSta(), map.get("pPriSta"));
        EqualsAssert.isEquals(SonTest.getPProSta(), map.get("pProSta"));
        EqualsAssert.isEquals(SonTest.getPDefSta(), map.get("pDefSta"));
        EqualsAssert.isEquals(SonTest.getPPubSta(), map.get("pPubSta"));
        EqualsAssert.isEquals(son.getSPri(), map.get("sPri"));
        EqualsAssert.isEquals(son.getSPro(), map.get("sPro"));
        EqualsAssert.isEquals(son.getSDef(), map.get("sDef"));
        EqualsAssert.isEquals(son.getSPub(), map.get("sPub"));
        EqualsAssert.isEquals(SonTest.getSPriSta(), map.get("sPriSta"));
        EqualsAssert.isEquals(SonTest.getSProSta(), map.get("sProSta"));
        EqualsAssert.isEquals(SonTest.getSDefSta(), map.get("sDefSta"));
        EqualsAssert.isEquals(SonTest.getSPubSta(), map.get("sPubSta"));
    }

    @Test
    public void convertToJsonObjectTest() {
        EntityTest entityTest = new EntityTest();
        entityTest.setId(2L);
        entityTest.setName("TOM");
        entityTest.setAge(18);
        entityTest.setPrice(6.42D);

        JSONObject jsonObject = JSONConvertor.convertToJsonObject(entityTest);
        System.out.println(jsonObject);

        String jsonString = JSONConvertor.convertToJsonString(entityTest);
        System.out.println(jsonString);
    }
}
