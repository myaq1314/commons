package org.czh.commons.utils.http;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.validate.EmptyAssert;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-30
 * email 916419307@qq.com
 */
public final class PostUtil {

    public static void main(String[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ext_order_no", "12345678901234567890");

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("User-Agent", HttpURLConnection.userAgent);
        headerMap.put("Accept", "application/json");


        try {
            System.out.println(doPost("http://127.0.0.1:443/v1/siip/order/preCreate"));
            System.out.println(doPost("http://127.0.0.1:443/v1/siip/order/preCreate", paramMap));
            System.out.println(doPost("http://127.0.0.1:443/v1/siip/order/preCreate", paramMap, headerMap));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpPoolUtil.shutdown();
    }

    public static String doPost(@NotBlankTag String url) {
        return HttpClientUtil.doJson(HttpClientUtil.EntityMethodDict.POST, url);
    }

    public static String doPost(@NotBlankTag String url, Map<String, Object> jsonParam) {
        return doPost(url, jsonParam, null);
    }

    public static String doPost(@NotBlankTag String url,
                                Map<String, Object> jsonParam,
                                Map<String, Object> headerParam) {
        return HttpClientUtil.doJson(HttpClientUtil.EntityMethodDict.POST, url, null, jsonParam, headerParam);
    }

    public static String doPost(@NotBlankTag String url, IBaseEntity jsonParam) {
        return doPost(url, jsonParam, null);
    }

    public static String doPost(@NotBlankTag String url, IBaseEntity jsonParam, Map<String, Object> headerParam) {
        return HttpClientUtil.doJson(HttpClientUtil.EntityMethodDict.POST, url, null, jsonParam, headerParam);
    }

    public static String doPost(@NotBlankTag String url, @NotBlankTag String reqJson) {
        return doPost(url, reqJson, null);
    }

    public static String doPost(@NotBlankTag String url,
                                @NotBlankTag String reqJson,
                                Map<String, Object> headerParam) {
        EmptyAssert.isNotBlank(reqJson);
        return HttpClientUtil.doJson(HttpClientUtil.EntityMethodDict.POST, url, null, reqJson, headerParam);
    }

    public static String doPost(@NotBlankTag String url, List<?> list) {
        return doPost(url, list, null);
    }

    public static String doPost(@NotBlankTag String url, List<?> list, Map<String, Object> headerParam) {
        return HttpClientUtil.doJson(HttpClientUtil.EntityMethodDict.POST, url, null, list, headerParam);
    }
}
