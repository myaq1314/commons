package org.czh.commons.utils.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-06-30
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
public final class DeleteUtil {

    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ext_order_no", "12345678901234567890");

        Map<String, Object> headerMap = new HashMap<>();
//        headerMap.put("User-Agent", HttpURLConnection.userAgent);
        headerMap.put("Accept", "application/json");

        try {
            System.out.println(doDeleteJson("http://127.0.0.1:443/v1/siip/order/createAndPayDelete"));
            System.out.println(doDeleteJson("http://127.0.0.1:443/v1/siip/order/createAndPayDelete", paramMap));
            System.out.println(doDeleteJson("http://127.0.0.1:443/v1/siip/order/createAndPayDelete",
                                            paramMap,
                                            headerMap
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpPoolUtil.shutdown();
    }

    public static String doDeleteJson(String url) {
        return doDeleteJson(url, null);
    }

    public static <RequestParam> String doDeleteJson(String url, RequestParam requestParam) {
        return doDeleteJson(url, requestParam, null);
    }

    public static <RequestParam, HeaderParam> String doDeleteJson(String url,
                                                                  RequestParam requestParam,
                                                                  HeaderParam headerParam) {
        return doDeleteJson(url, null, requestParam, headerParam);
    }

    public static <UrlParam, RequestParam, HeaderParam> String doDeleteJson(String url,
                                                                            UrlParam urlParam,
                                                                            RequestParam requestParam,
                                                                            HeaderParam headerParam) {
        return HttpClientUtil.doJsonText(EnclosingEnum.DELETE, url, urlParam, requestParam, headerParam);
    }

    public static String doUrlencodedText(String url) {
        return doUrlencodedText(url, null);
    }

    public static <RequestParam> String doUrlencodedText(String url, RequestParam requestParam) {
        return doUrlencodedText(url, requestParam, null);
    }

    public static <RequestParam, HeaderParam> String doUrlencodedText(String url,
                                                                      RequestParam requestParam,
                                                                      HeaderParam headerParam) {
        return doUrlencodedText(url, null, requestParam, headerParam);
    }

    public static <UrlParam, RequestParam, HeaderParam> String doUrlencodedText(String url,
                                                                                UrlParam urlParam,
                                                                                RequestParam requestParam,
                                                                                HeaderParam headerParam) {
        return HttpClientUtil.doUrlencodedText(EnclosingEnum.DELETE, url, urlParam, requestParam, headerParam);
    }
}
