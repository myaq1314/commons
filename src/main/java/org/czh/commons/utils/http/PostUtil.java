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
public final class PostUtil {

    @SuppressWarnings("DuplicatedCode")
    public static void main(String[] args) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("ext_order_no", "12345678901234567890");

        Map<String, Object> headerMap = new HashMap<>();
//        headerMap.put("User-Agent", HttpURLConnection.userAgent);
        headerMap.put("Accept", "application/json");

        try {
            System.out.println(doPostJson("http://127.0.0.1:443/v1/siip/order/createAndPayPost"));
            System.out.println(doPostJson("http://127.0.0.1:443/v1/siip/order/createAndPayPost", paramMap));
            System.out.println(doPostJson("http://127.0.0.1:443/v1/siip/order/createAndPayPost", paramMap, headerMap));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HttpPoolUtil.shutdown();
    }

    public static String doPostJson(@NotBlankTag String url) {
        return doPostJson(url, null);
    }

    public static <RequestParam> String doPostJson(@NotBlankTag String url, RequestParam requestParam) {
        return doPostJson(url, requestParam, null);
    }

    public static <RequestParam, HeaderParam> String doPostJson(@NotBlankTag String url,
                                                                RequestParam requestParam,
                                                                HeaderParam headerParam) {
        return doPostJson(url, null, requestParam, headerParam);
    }

    public static <UrlParam, RequestParam, HeaderParam> String doPostJson(@NotBlankTag String url,
                                                                          UrlParam urlParam,
                                                                          RequestParam requestParam,
                                                                          HeaderParam headerParam) {
        return HttpClientUtil.doJsonText(EnclosingEnum.POST, url, urlParam, requestParam, headerParam);
    }

    public static String doUrlencodedText(@NotBlankTag String url) {
        return doUrlencodedText(url, null);
    }

    public static <RequestParam> String doUrlencodedText(@NotBlankTag String url, RequestParam requestParam) {
        return doUrlencodedText(url, requestParam, null);
    }

    public static <RequestParam, HeaderParam> String doUrlencodedText(@NotBlankTag String url,
                                                                      RequestParam requestParam,
                                                                      HeaderParam headerParam) {
        return doUrlencodedText(url, null, requestParam, headerParam);
    }

    public static <UrlParam, RequestParam, HeaderParam> String doUrlencodedText(@NotBlankTag String url,
                                                                                UrlParam urlParam,
                                                                                RequestParam requestParam,
                                                                                HeaderParam headerParam) {
        return HttpClientUtil.doUrlencodedText(EnclosingEnum.POST, url, urlParam, requestParam, headerParam);
    }
}
