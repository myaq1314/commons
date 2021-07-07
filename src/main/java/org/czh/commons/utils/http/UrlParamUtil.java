package org.czh.commons.utils.http;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.util.EntityUtils;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-07-07
 * email 916419307@qq.com
 */
@Slf4j
public final class UrlParamUtil {

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
        System.out.println(getUrlByParam("www.baidu.com", baseEntity));

        Map<String, Object> map = new HashMap<>();
        map.put("name", "Map");
        map.put("age", 1);
        System.out.println(getUrlByParam("www.baidu.com?aaa=123", map));
    }

    public static <UrlParam> String getUrlByParam(@NotBlankTag final String url, final UrlParam urlParam) {
        return getUrlByPairList(url, NameValuePairUtil.getListByParam(urlParam));
    }

    private static String getUrlByPairList(@NotBlankTag final String url, List<NameValuePair> pairList) {
        EmptyAssert.isNotNull(url);
        if (EmptyValidate.isEmpty(pairList)) {
            return url;
        }

        try {
            String paramUrl = EntityUtils.toString(new UrlEncodedFormEntity(pairList, StandardCharsets.UTF_8));
            if (EmptyValidate.isBlank(paramUrl)) {
                return url;
            }
            if (url.contains("?")) {
                return url + "&" + paramUrl;
            } else {
                return url + "?" + paramUrl;
            }
        } catch (IOException e) {
            log.error("拼接参数链接失败", e);
            throw new RuntimeException("拼接参数链接失败");
        }
    }
}
