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
public final class GetUtil {

    public static void main(String[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ext_order_no", "12345678901234567890");

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("User-Agent", HttpURLConnection.userAgent);
        headerMap.put("Accept", "application/json");

        try {
            System.out.println(doGet("http://127.0.0.1:443/v1/siip/order/createAndPay"));
            System.out.println(doGet("http://127.0.0.1:443/v1/siip/order/createAndPay", paramMap));
            System.out.println(doGet("http://127.0.0.1:443/v1/siip/order/createAndPay", paramMap, headerMap));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpPoolUtil.shutdown();
    }

    public static String doGet(@NotBlankTag String url) {
        return HttpClientUtil.doGet(url);
    }

    public static String doGet(@NotBlankTag String url, Map<String, Object> urlParam) {
        return doGet(url, urlParam, null);
    }

    public static String doGet(@NotBlankTag String url,
                               Map<String, Object> urlParam,
                               Map<String, Object> headerParam) {
        return HttpClientUtil.doGet(url, urlParam, headerParam);
    }

    public static String doGet(@NotBlankTag String url, IBaseEntity urlParam) {
        return doGet(url, urlParam, null);
    }

    public static String doGet(@NotBlankTag String url, IBaseEntity urlParam, Map<String, Object> headerParam) {
        return HttpClientUtil.doGet(url, urlParam, headerParam);
    }
}
