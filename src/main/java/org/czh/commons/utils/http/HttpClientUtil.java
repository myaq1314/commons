package org.czh.commons.utils.http;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.entity.IBaseEntity;
import org.czh.commons.enums.parent.IBaseEnum;
import org.czh.commons.utils.FieldUtil;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public static String doGet(@NotBlankTag final String url) {
        return doGet(url, null);
    }

    public static <UrlParam> String doGet(@NotBlankTag final String url, final UrlParam urlParam) {
        return doGet(url, urlParam, null);
    }

    public static <UrlParam, HeaderParam> String doGet(@NotBlankTag final String url,
                                                       final UrlParam urlParam,
                                                       final HeaderParam headerParam) {
        HttpGet httpGet = new HttpGet(getParamUrl(url, urlParam));
        configHeaderParam(httpGet, headerParam);
        return invoke(httpGet);
    }

    /*
      -----------------------------post/put/delete dict-------------------------------
     */

    /*
      -----------------------------post/put/delete json request-------------------------------
     */
    public static String doJson(@NotNullTag final EntityMethodDict entityMethodDict,
                                @NotBlankTag final String url) {
        return doJson(entityMethodDict, url, "{}");
    }

    public static <JsonParam> String doJson(@NotNullTag final EntityMethodDict entityMethodDict,
                                            @NotBlankTag final String url,
                                            JsonParam jsonParam) {
        return doJson(entityMethodDict, url, jsonParam, null);
    }

    public static <JsonParam, HeaderParam> String doJson(@NotNullTag final EntityMethodDict entityMethodDict,
                                                         @NotBlankTag final String url,
                                                         JsonParam jsonParam,
                                                         HeaderParam headerParam) {
        return doJson(entityMethodDict, url, null, jsonParam, headerParam);
    }

    public static <UrlParam, JsonParam, HeaderParam> String doJson(@NotNullTag final EntityMethodDict entityMethodDict,
                                                                   @NotBlankTag String url,
                                                                   UrlParam urlParam,
                                                                   JsonParam jsonParam,
                                                                   HeaderParam headerParam) {
        EmptyAssert.isNotNull(entityMethodDict);
        EmptyAssert.isNotBlank(url);

        url = getParamUrl(url, urlParam);
        HttpEntityEnclosingRequestBase enclosingRequest;
        switch (entityMethodDict) {
            case POST:
                enclosingRequest = new HttpPost(url);
                break;
            case PUT:
                enclosingRequest = new HttpPut(url);
                break;
            case DELETE:
                enclosingRequest = new HttpDeleteWithBody(url);
                break;
            default:
                throw new RuntimeException("无效的 Method");
        }
        configJsonParam(enclosingRequest, jsonParam);
        configHeaderParam(enclosingRequest, headerParam);
        return invoke(enclosingRequest);
    }

    public static <HeaderParam> void configHeaderParam(@NotNull final HttpRequestBase baseRequest,
                                                       final HeaderParam headerParam) {
        EmptyAssert.isNotNull(baseRequest);

        if (EmptyValidate.isNull(headerParam)) {
            return;
        }

        if (headerParam instanceof IBaseEntity) {
            List<Field> fieldList = FieldUtil.queryFieldList(headerParam.getClass());
            if (EmptyValidate.isEmpty(fieldList)) {
                return;
            }
            fieldList.stream().filter(field -> !"serialVersionUID".equals(field.getName())).forEach(field -> {
                Object fieldValue = FieldUtil.readField(headerParam, field);
                if (EmptyValidate.isNotNull(fieldValue)) {
                    baseRequest.setHeader(field.getName(), fieldValue.toString());
                }
            });
        } else if (headerParam instanceof Map) {
            if (EmptyValidate.isEmpty((Map<?, ?>) headerParam)) {
                return;
            }
            ((Map<?, ?>) headerParam).forEach((key, value) -> {
                if (EmptyValidate.isNull(key) || EmptyValidate.isNull(value)) {
                    return;
                }
                baseRequest.setHeader(key.toString(), value.toString());
            });
        } else {
            throw new RuntimeException("未知的HeaderParam类型");
        }
    }

    /*
      -----------------------------config header-------------------------------
     */

    public static <JsonParam> void configJsonParam(@NotNull final HttpEntityEnclosingRequestBase enclosingRequest,
                                                   final JsonParam jsonParam) {
        EmptyAssert.isNotNull(enclosingRequest);

        String paramJson;
        if (EmptyValidate.isNull(jsonParam)) {
            paramJson = "{}";
        } else if (jsonParam instanceof String) {
            paramJson = (String) jsonParam;
        } else {
            paramJson = JSONObject.toJSONString(jsonParam, SerializerFeature.DisableCircularReferenceDetect);
        }
        StringEntity entity = new StringEntity(paramJson, StandardCharsets.UTF_8);
        entity.setContentType("application/json");
        enclosingRequest.setEntity(entity);
    }

    /*
      -----------------------------config json-------------------------------
     */

    public static <UrlParam> String getParamUrl(@NotBlankTag final String url, final UrlParam urlParam) {
        EmptyAssert.isNotBlank(url);
        if (EmptyValidate.isNull(urlParam)) {
            return url;
        }

        List<BasicNameValuePair> pairList = new ArrayList<>();
        if (urlParam instanceof IBaseEntity) {
            List<Field> fieldList = FieldUtil.queryFieldList(urlParam.getClass());
            if (EmptyValidate.isEmpty(fieldList)) {
                return url;
            }
            fieldList.stream().filter(field -> !"serialVersionUID".equals(field.getName())).forEach(field -> {
                Object fieldValue = FieldUtil.readField(urlParam, field);
                if (EmptyValidate.isNotNull(fieldValue)) {
                    pairList.add(new BasicNameValuePair(field.getName(), fieldValue.toString()));
                }
            });
        } else if (urlParam instanceof Map) {
            //noinspection rawtypes
            if (EmptyValidate.isEmpty((Map) urlParam)) {
                return url;
            }
            //noinspection unchecked,rawtypes,rawtypes
            ((Map) urlParam).forEach((key, value) -> {
                if (EmptyValidate.isNotNull(key) && EmptyValidate.isNotNull(value)) {
                    pairList.add(new BasicNameValuePair(key.toString(), value.toString()));
                }
            });
        } else {
            throw new RuntimeException("未知的UrlParam类型");
        }
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

    public static String invoke(@NotNull final HttpRequestBase baseRequest) {
        EmptyAssert.isNotNull(baseRequest);

        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        try {
            final RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(2000)
                    .setConnectTimeout(5000)
                    .setSocketTimeout(5000)
                    .build();
            baseRequest.setConfig(requestConfig);

            response = HttpPoolUtil.getHttpClient().execute(baseRequest, HttpClientContext.create());
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

    public enum EntityMethodDict implements IBaseEnum {
        POST,
        PUT,
        DELETE
    }
}
