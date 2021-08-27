package org.czh.commons.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.util.EntityUtils;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author : czh
 * description :
 * date : 2021-06-30
 * email 916419307@qq.com
 */
@Slf4j
public final class HttpClientUtil {

    /*
      -----------------------------get request-------------------------------
     */

    public static <UrlParam, HeaderParam> String doGetText(@NotBlankTag final String url,
                                                           final UrlParam urlParam) {
        return doGetText(url, urlParam, null);
    }

    public static <UrlParam, HeaderParam> String doGetText(@NotBlankTag final String url,
                                                           final UrlParam urlParam,
                                                           final HeaderParam headerParam) {
        return invokeText(doGetResp(url, urlParam, headerParam));
    }

    public static <UrlParam, HeaderParam> CloseableHttpResponse doGetResp(@NotBlankTag String url,
                                                                          final UrlParam urlParam) {
        return doGetResp(url, urlParam, null);
    }

    public static <UrlParam, HeaderParam> CloseableHttpResponse doGetResp(@NotBlankTag String url,
                                                                          final UrlParam urlParam,
                                                                          final HeaderParam headerParam) {
        String tmpUrl = UrlParamUtil.getUrlByParam(url, urlParam);
        HttpGet httpGet = new HttpGet(tmpUrl);
        HeaderParamUtil.addHeaderByParam(httpGet, headerParam);
        return invokeResp(httpGet);
    }

    /*
      -----------------------------post/put/delete x-www.form-urlencoded request-------------------------------
     */

    public static <RequestParam> String doUrlencodedText(@NotNull final EnclosingEnum enclosingEnum,
                                                         @NotBlankTag final String url,
                                                         RequestParam requestParam) {
        return doUrlencodedText(enclosingEnum, url, null, requestParam, null);
    }

    public static <UrlParam, RequestParam, HeaderParam> String doUrlencodedText(@NotNull final EnclosingEnum enclosingEnum,
                                                                                @NotBlankTag final String url,
                                                                                UrlParam urlParam,
                                                                                RequestParam requestParam,
                                                                                HeaderParam headerParam) {
        return invokeText(doUrlencodedResp(enclosingEnum, url, urlParam, requestParam, headerParam));
    }

    public static <RequestParam> CloseableHttpResponse doUrlencodedResp(@NotNull final EnclosingEnum enclosingEnum,
                                                                        @NotBlankTag final String url,
                                                                        RequestParam requestParam) {
        return doUrlencodedResp(enclosingEnum, url, null, requestParam, null);
    }

    public static <UrlParam, RequestParam, HeaderParam> CloseableHttpResponse doUrlencodedResp(@NotNull final EnclosingEnum enclosingEnum,
                                                                                               @NotBlankTag final String url,
                                                                                               UrlParam urlParam,
                                                                                               RequestParam requestParam,
                                                                                               HeaderParam headerParam) {
        String tmpUrl = UrlParamUtil.getUrlByParam(url, urlParam);
        HttpEntityEnclosingRequestBase enclosingRequest = EnclosingEnum.getEnclosingRequest(enclosingEnum, tmpUrl);
        RequestParamUtil.setUrlencodedParam(enclosingRequest, requestParam);
        HeaderParamUtil.addHeaderByParam(enclosingRequest, headerParam);
        return invokeResp(enclosingRequest);
    }

    /*
      -----------------------------post/put/delete json request-------------------------------
     */

    public static <UrlParam, RequestParam, HeaderParam> String doJsonText(@NotNullTag final EnclosingEnum enclosingEnum,
                                                                          @NotBlankTag String url,
                                                                          RequestParam requestParam) {
        return doJsonText(enclosingEnum, url, null, requestParam, null);
    }

    public static <UrlParam, RequestParam, HeaderParam> String doJsonText(@NotNullTag final EnclosingEnum enclosingEnum,
                                                                          @NotBlankTag String url,
                                                                          UrlParam urlParam,
                                                                          RequestParam requestParam,
                                                                          HeaderParam headerParam) {
        return invokeText(doJsonResp(enclosingEnum, url, urlParam, requestParam, headerParam));
    }

    public static <UrlParam, RequestParam, HeaderParam> CloseableHttpResponse doJsonResp(@NotNullTag final EnclosingEnum enclosingEnum,
                                                                                         @NotBlankTag String url,
                                                                                         RequestParam requestParam) {
        return doJsonResp(enclosingEnum, url, null, requestParam, null);
    }

    public static <UrlParam, RequestParam, HeaderParam> CloseableHttpResponse doJsonResp(@NotNullTag final EnclosingEnum enclosingEnum,
                                                                                         @NotBlankTag String url,
                                                                                         UrlParam urlParam,
                                                                                         RequestParam requestParam,
                                                                                         HeaderParam headerParam) {
        String tmpUrl = UrlParamUtil.getUrlByParam(url, urlParam);
        HttpEntityEnclosingRequestBase enclosingRequest = EnclosingEnum.getEnclosingRequest(enclosingEnum, tmpUrl);
        RequestParamUtil.setJsonParam(enclosingRequest, requestParam);
        HeaderParamUtil.addHeaderByParam(enclosingRequest, headerParam);
        return invokeResp(enclosingRequest);
    }

    /*
      -----------------------------set request-------------------------------
     */

    public static String invokeText(@NotNull final HttpRequestBase baseRequest) {
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            response = invokeResp(baseRequest);
            entity = response.getEntity();
            return EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("HTTP请求发送失败", e);
            throw new RuntimeException("HTTP请求发送失败");
        } finally {
            try {
                if (EmptyValidate.isNotNull(entity)) {
                    EntityUtils.consume(entity);
                }
                if (EmptyValidate.isNotNull(response)) {
                    response.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

    public static String invokeText(@NotNullTag final CloseableHttpResponse response) {
        EmptyAssert.isNotNull(response);

        HttpEntity entity = null;
        try {
            entity = response.getEntity();
            return EntityUtils.toString(entity, StandardCharsets.UTF_8);
        } catch (IOException e) {
            log.error("HTTP请求发送失败", e);
            throw new RuntimeException("HTTP请求发送失败");
        } finally {
            try {
                if (EmptyValidate.isNotNull(entity)) {
                    EntityUtils.consume(entity);
                }
                if (EmptyValidate.isNotNull(response)) {
                    response.close();
                }
            } catch (IOException ignored) {
            }
        }
    }

    public static CloseableHttpResponse invokeResp(@NotNull final HttpRequestBase baseRequest) {
        EmptyAssert.isNotNull(baseRequest);

        try {
            final RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(2000)
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .build();
            baseRequest.setConfig(requestConfig);

            return HttpPoolUtil.getHttpClient().execute(baseRequest, HttpClientContext.create());
        } catch (IOException e) {
            log.error("HTTP请求发送失败", e);
            throw new RuntimeException("HTTP请求发送失败");
        }
    }
}
