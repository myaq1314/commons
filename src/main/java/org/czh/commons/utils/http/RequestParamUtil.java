package org.czh.commons.utils.http;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.StringEntity;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.validate.EmptyAssert;
import org.czh.commons.validate.EmptyValidate;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-07-07
 * email 916419307@qq.com
 */
public final class RequestParamUtil {

    public static <RequestParam> void setJsonParam(@NotNullTag final HttpEntityEnclosingRequestBase enclosingRequest,
                                                   final RequestParam requestParam) {
        EmptyAssert.isNotNull(enclosingRequest);

        String paramJson;
        if (EmptyValidate.isNull(requestParam)) {
            paramJson = "{}";
        } else if (requestParam instanceof String) {
            paramJson = (String) requestParam;
        } else {
            paramJson = JSONObject.toJSONString(requestParam, SerializerFeature.DisableCircularReferenceDetect);
        }
        StringEntity entity = new StringEntity(paramJson, StandardCharsets.UTF_8);
        entity.setContentType("application/json");
        enclosingRequest.setEntity(entity);
    }


    public static <RequestParam> void setUrlencodedParam(@NotNullTag final HttpEntityEnclosingRequestBase enclosingRequest,
                                                         final RequestParam requestParam) {
        EmptyAssert.isNotNull(enclosingRequest);
        HeaderParamUtil.setHeader(enclosingRequest, "Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        List<NameValuePair> pairList = NameValuePairUtil.getListByParam(requestParam);
        if (EmptyValidate.isEmpty(pairList)) {
            return;
        }

        enclosingRequest.setEntity(new UrlEncodedFormEntity(pairList, StandardCharsets.UTF_8));

    }
}
