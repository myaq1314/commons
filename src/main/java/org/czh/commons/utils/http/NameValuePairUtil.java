package org.czh.commons.utils.http;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyValidate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-07-07
 * email 916419307@qq.com
 */
public final class NameValuePairUtil {

    public static void main(String[] args) {
        IBaseEntity baseEntity = new IBaseEntity() {
            private static final long serialVersionUID = 1603127512655654205L;
            @Getter
            @Setter
            private String name = "IBaseEntity";
            @Getter
            @Setter
            private int age = 0;
        };
        List<NameValuePair> pairListByEntityParam = getListByParam(baseEntity);
        System.out.println(pairListByEntityParam);
        System.out.println();

        List<NameValuePair> pairListByEntity = getListByEntity(baseEntity);
        System.out.println(pairListByEntity);
        System.out.println();

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Map");
        map.put("age", 1);
        List<NameValuePair> pairListByMapParam = getListByParam(map);
        System.out.println(pairListByMapParam);
        System.out.println();

        List<NameValuePair> pairListByMap = getListByMap(map);
        System.out.println(pairListByMap);
        System.out.println();
    }

    public static <Params> List<NameValuePair> getListByParam(final Params params) {
        if (EmptyValidate.isNull(params)) {
            return null;
        }

        if (params instanceof IBaseEntity) {
            return getListByEntity((IBaseEntity) params);
        } else if (params instanceof Map<?, ?>) {
            return getListByMap((Map<?, ?>) params);
        } else {
            throw new RuntimeException("未知的Param类型");
        }
    }

    public static List<NameValuePair> getListByEntity(IBaseEntity baseEntity) {
        if (EmptyValidate.isNull(baseEntity)) {
            return null;
        }

        List<Field> fieldList = FieldUtil.queryFieldList(baseEntity.getClass());
        if (EmptyValidate.isEmpty(fieldList)) {
            return null;
        }

        List<NameValuePair> pairList = new ArrayList<>();
        fieldList.stream().filter(field -> !"serialVersionUID".equals(field.getName())).forEach(field -> {
            Object fieldValue = FieldUtil.readField(baseEntity, field);
            if (EmptyValidate.isNotNull(fieldValue)) {
                pairList.add(new BasicNameValuePair(field.getName(), fieldValue.toString()));
            }
        });
        return pairList;
    }

    public static List<NameValuePair> getListByMap(Map<?, ?> map) {
        if (EmptyValidate.isEmpty(map)) {
            return null;
        }

        List<NameValuePair> pairList = new ArrayList<>();
        map.forEach((key, value) -> {
            if (EmptyValidate.isNotNull(key) && EmptyValidate.isNotNull(value)) {
                pairList.add(new BasicNameValuePair(key.toString(), value.toString()));
            }
        });
        return pairList;
    }
}
