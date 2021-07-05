package org.czh.commons.utils.http;

import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.entity.IBaseEntity;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-30
 * email 916419307@qq.com
 */
public final class PutUtil {

    public static void main(String[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ext_order_no", "12345678901234567890");

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("User-Agent", HttpURLConnection.userAgent);
        headerMap.put("Accept", "application/json");


        try {
            System.out.println(doPut("http://127.0.0.1:443/v1/siip/order/createAndPayPut"));
            System.out.println(doPut("http://127.0.0.1:443/v1/siip/order/createAndPayPut", paramMap));
            System.out.println(doPut("http://127.0.0.1:443/v1/siip/order/createAndPayPut", paramMap, headerMap));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpPoolUtil.shutdown();
    }

    public static String doPut(@NotBlankTag String url) {
        return HttpClientUtil.doJson(HttpClientUtil.EntityMethodDict.PUT, url);
    }

    public static String doPut(@NotBlankTag String url, Map<String, Object> jsonParam) {
        return doPut(url, jsonParam, null);
    }

    public static String doPut(@NotBlankTag String url,
                               Map<String, Object> jsonParam,
                               Map<String, Object> headerParam) {
        return HttpClientUtil.doJson(HttpClientUtil.EntityMethodDict.PUT, url, null, jsonParam, headerParam);
    }

    public static String doPut(@NotBlankTag String url, IBaseEntity jsonParam) {
        return doPut(url, jsonParam, null);
    }

    public static String doPut(@NotBlankTag String url, IBaseEntity jsonParam, Map<String, Object> headerParam) {
        return HttpClientUtil.doJson(HttpClientUtil.EntityMethodDict.PUT, url, null, jsonParam, headerParam);
    }
}
