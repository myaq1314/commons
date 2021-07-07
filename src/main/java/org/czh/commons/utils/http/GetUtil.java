package org.czh.commons.utils.http;

import org.czh.commons.annotations.tag.NotBlankTag;
import sun.net.www.protocol.http.HttpURLConnection;

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
        headerMap.put("User-Agent", HttpURLConnection.userAgent);
        headerMap.put("Accept", "application/json");

        try {
            System.out.println(doGetTxt("http://127.0.0.1:443/v1/siip/order/createAndPay"));
            System.out.println(doGetTxt("http://127.0.0.1:443/v1/siip/order/createAndPay", paramMap));
            System.out.println(doGetTxt("http://127.0.0.1:443/v1/siip/order/createAndPay", paramMap, headerMap));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpPoolUtil.shutdown();
    }

    public static String doGetTxt(@NotBlankTag final String url) {
        return doGetTxt(url, null);
    }

    public static <UrlParam> String doGetTxt(@NotBlankTag final String url,
                                             final UrlParam urlParam) {
        return doGetTxt(url, urlParam, null);
    }

    public static <UrlParam, HeaderParam> String doGetTxt(@NotBlankTag final String url,
                                                          final UrlParam urlParam,
                                                          final HeaderParam headerParam) {
        return HttpClientUtil.doGetTxt(url, urlParam, headerParam);
    }
}
