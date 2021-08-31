package org.czh.commons.utils.http;

import org.czh.commons.annotations.tag.NotBlankTag;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-30
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class GetUtil {

    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ext_order_no", "12345678901234567890");

        Map<String, Object> headerMap = new HashMap<>();
//        headerMap.put("User-Agent", HttpURLConnection.userAgent);
        headerMap.put("Accept", "application/json");

        try {
            System.out.println(doGetText("http://127.0.0.1:443/v1/siip/order/createAndPay"));
            System.out.println(doGetText("http://127.0.0.1:443/v1/siip/order/createAndPay", paramMap));
            System.out.println(doGetText("http://127.0.0.1:443/v1/siip/order/createAndPay", paramMap, headerMap));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpPoolUtil.shutdown();
    }

    public static String doGetText(@NotBlankTag final String url) {
        return doGetText(url, null);
    }

    public static <UrlParam> String doGetText(@NotBlankTag final String url,
                                              final UrlParam urlParam) {
        return doGetText(url, urlParam, null);
    }

    public static <UrlParam, HeaderParam> String doGetText(@NotBlankTag final String url,
                                                           final UrlParam urlParam,
                                                           final HeaderParam headerParam) {
        return HttpClientUtil.doGetText(url, urlParam, headerParam);
    }
}
