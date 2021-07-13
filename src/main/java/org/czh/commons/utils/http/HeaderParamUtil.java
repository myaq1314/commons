package org.czh.commons.utils.http;

import lombok.Getter;
import lombok.Setter;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author : czh
 * description :
 * date : 2021-07-07
 * email 916419307@qq.com
 */
public final class HeaderParamUtil {

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
        HttpGet httpGet = new HttpGet("www.baidu.com");
        addHeaderByParam(httpGet, baseEntity);
        Map<String, String> httpGetHeaderMap = getHeaderMap(httpGet);
        System.out.println(httpGetHeaderMap);
        System.out.println();

        HttpEntityEnclosingRequestBase httpPost = EnclosingEnum.getEnclosingRequest(EnclosingEnum.POST, "www.baidu.com?aaa=123");
        addHeaderByEntity(httpPost, baseEntity);
        Map<String, String> httpPostHeaderMap = getHeaderMap(httpPost);
        System.out.println(httpPostHeaderMap);
        System.out.println();

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Map");
        map.put("age", 1);
        HttpEntityEnclosingRequestBase httpPut = EnclosingEnum.getEnclosingRequest(EnclosingEnum.PUT, "www.baidu.com");
        addHeaderByParam(httpPut, map);
        Map<String, String> httpPutHeaderMap = getHeaderMap(httpPut);
        System.out.println(httpPutHeaderMap);
        System.out.println();

        HttpEntityEnclosingRequestBase httpDelete = EnclosingEnum.getEnclosingRequest(EnclosingEnum.DELETE, "www.baidu.com?aaa=123");
        addHeaderByMap(httpDelete, map);
        Map<String, String> httpDeleteHeaderMap = getHeaderMap(httpDelete);
        System.out.println(httpDeleteHeaderMap);
        System.out.println();

    }

    public static <HeaderParam> void addHeaderByParam(@NotNull final HttpRequestBase baseRequest,
                                                      final HeaderParam headerParam) {
        EmptyAssert.isNotNull(baseRequest);
        if (EmptyValidate.isNull(headerParam)) {
            return;
        }

        if (headerParam instanceof IBaseEntity) {
            addHeaderByEntity(baseRequest, (IBaseEntity) headerParam);
        } else if (headerParam instanceof Map) {
            addHeaderByMap(baseRequest, (Map<?, ?>) headerParam);
        } else {
            throw new RuntimeException("未知的HeaderParam类型");
        }
    }

    public static void addHeaderByEntity(@NotNullTag final HttpRequestBase baseRequest,
                                         final IBaseEntity baseEntity) {
        EmptyAssert.isNotNull(baseRequest);
        if (EmptyValidate.isNull(baseEntity)) {
            return;
        }

        List<Field> fieldList = FieldUtil.queryFieldList(baseEntity.getClass());
        if (EmptyValidate.isEmpty(fieldList)) {
            return;
        }

        fieldList.stream().filter(field -> !"serialVersionUID".equals(field.getName())).forEach(field -> {
            Object fieldValue = FieldUtil.readField(baseEntity, field);
            if (EmptyValidate.isNotNull(fieldValue)) {
                addHeader(baseRequest, field.getName(), fieldValue);
            }
        });
    }

    public static void addHeaderByMap(@NotNullTag final HttpRequestBase baseRequest, final Map<?, ?> map) {
        EmptyAssert.isNotNull(baseRequest);
        if (EmptyValidate.isEmpty(map)) {
            return;
        }

        map.forEach((key, value) -> {
            if (EmptyValidate.isNull(key) || EmptyValidate.isNull(value)) {
                return;
            }
            addHeader(baseRequest, key, value);
        });
    }

    public static void addHeader(@NotNull final HttpRequestBase baseRequest,
                                 @NotBlankTag final Object key,
                                 @NotBlankTag final Object value) {
        EmptyAssert.allNotNull(baseRequest, key, value);
        if (value instanceof Collection<?>) {
            ((Collection<?>) value).forEach(each -> baseRequest.addHeader(key.toString(), each.toString()));
        } else if (value instanceof IBaseEntity) {
            addHeaderByEntity(baseRequest, (IBaseEntity) value);
        } else if (value instanceof Map<?, ?>) {
            addHeaderByMap(baseRequest, (Map<?, ?>) value);
        } else {
            baseRequest.addHeader(key.toString(), value.toString());
        }
    }

    public static Map<String, String> getHeaderMap(@NotNull final HttpRequestBase baseRequest) {
        EmptyAssert.isNotNull(baseRequest);

        Map<String, String> map = new HashMap<>();
        Header[] headers = baseRequest.getAllHeaders();
        if (EmptyValidate.isEmpty(headers)) {
            return map;
        }

        map = Arrays.stream(headers).collect(Collectors.toMap(NameValuePair::getName, NameValuePair::getValue, (a, b) -> b));
        return map;
    }
}
