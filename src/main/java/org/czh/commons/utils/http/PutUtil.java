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
public final class PutUtil {

    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ext_order_no", "12345678901234567890");

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("User-Agent", HttpURLConnection.userAgent);
        headerMap.put("Accept", "application/json");


        try {
            System.out.println(doPutJson("http://127.0.0.1:443/v1/siip/order/createAndPayPut"));
            System.out.println(doPutJson("http://127.0.0.1:443/v1/siip/order/createAndPayPut", paramMap));
            System.out.println(doPutJson("http://127.0.0.1:443/v1/siip/order/createAndPayPut", paramMap, headerMap));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpPoolUtil.shutdown();
    }

    public static String doPutJson(@NotBlankTag String url) {
        return doPutJson(url, null);
    }

    public static <RequestParam> String doPutJson(@NotBlankTag String url, RequestParam requestParam) {
        return doPutJson(url, requestParam, null);
    }

    public static <RequestParam, HeaderParam> String doPutJson(@NotBlankTag String url,
                                                               RequestParam requestParam,
                                                               HeaderParam headerParam) {
        return doPutJson(url, null, requestParam, headerParam);
    }

    public static <UrlParam, RequestParam, HeaderParam> String doPutJson(@NotBlankTag String url,
                                                                         UrlParam urlParam,
                                                                         RequestParam requestParam,
                                                                         HeaderParam headerParam) {
        return HttpClientUtil.doJsonTxt(EnclosingEnum.PUT, url, urlParam, requestParam, headerParam);
    }

    @SuppressWarnings("unused")
    public static String doUrlencodedTxt(@NotBlankTag String url) {
        return doUrlencodedTxt(url, null);
    }

    public static <RequestParam> String doUrlencodedTxt(@NotBlankTag String url, RequestParam requestParam) {
        return doUrlencodedTxt(url, requestParam, null);
    }

    public static <RequestParam, HeaderParam> String doUrlencodedTxt(@NotBlankTag String url,
                                                                     RequestParam requestParam,
                                                                     HeaderParam headerParam) {
        return doUrlencodedTxt(url, null, requestParam, headerParam);
    }

    public static <UrlParam, RequestParam, HeaderParam> String doUrlencodedTxt(@NotBlankTag String url,
                                                                               UrlParam urlParam,
                                                                               RequestParam requestParam,
                                                                               HeaderParam headerParam) {
        return HttpClientUtil.doUrlencodedTxt(EnclosingEnum.PUT, url, urlParam, requestParam, headerParam);
    }
}
