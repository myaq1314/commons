package org.czh.commons.utils.tsdb;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.czh.commons.utils.http.EnclosingEnum;
import org.czh.commons.utils.http.HttpClientUtil;
import org.czh.commons.utils.tsdb.enums.OptionalDict;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.asserts.NumAssert;
import org.czh.commons_core.utils.date.DateUtil;
import org.czh.commons_core.validate.EmptyValidate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@Slf4j
public class TsdbClient {

    public static String put(@NotBlank String url,
                             @NotNull TsdbPutVO vo) {
        return put(url, OptionalDict.SUMMARY, vo);
    }

    public static String put(@NotBlank String url,
                             OptionalDict optionalDict,
                             @NotNull TsdbPutVO vo) {
        EmptyAssert.isNotBlank(url);
        EmptyAssert.isNotNull(vo);

        return put(url, optionalDict, Collections.singletonList(vo));
    }

    public static String put(@NotBlank String url,
                             @NotEmpty List<TsdbPutVO> voList) {
        return put(url, OptionalDict.SUMMARY, voList);
    }

    public static String put(@NotBlank String url,
                             OptionalDict optionalDict,
                             @NotEmpty List<TsdbPutVO> voList) {
        EmptyAssert.isNotBlank(url);
        EmptyAssert.isNotEmpty(voList);

        try {
            if (EmptyValidate.isNull(optionalDict)) {
                return HttpClientUtil.doJsonText(EnclosingEnum.POST, url + "/api/put", voList);
            }
            return HttpClientUtil.doJsonText(EnclosingEnum.POST, url + "/api/put?" + optionalDict.key, voList);
        } catch (Exception e) {
            log.error("opentsdb put api wrong, reason:{}", e.getMessage());
            throw new RuntimeException("opentsdb put data wrong");
        }
    }

    public static String query(@NotBlank String url,
                               @NotNull TsdbQueryVO vo) {
        return query(url, OptionalDict.SUMMARY, vo);
    }

    public static String query(@NotBlank String url,
                               OptionalDict optionalDict,
                               @NotNull TsdbQueryVO vo) {
        EmptyAssert.isNotBlank(url);
        EmptyAssert.isNotNull(vo);

        try {
            if (EmptyValidate.isNull(optionalDict)) {
                return HttpClientUtil.doJsonText(EnclosingEnum.POST, url + "/api/query", vo);
            }
            return HttpClientUtil.doJsonText(EnclosingEnum.POST, url + "/api/query?" + optionalDict.key, vo);
        } catch (Exception e) {
            log.error("opentsdb query api wrong, reason:{}", e.getMessage());
            throw new RuntimeException("opentsdb query data wrong");
        }
    }

    public static void addCouponConfigLog(Long businessId, Long shopId, Long couponConfigId, Integer num) {
        NumAssert.isPositiveLong(businessId);
        NumAssert.isPositiveLong(shopId);
        NumAssert.isPositiveLong(couponConfigId);
        NumAssert.isPositiveInt(num);

        TsdbPutVO reqVO = new TsdbPutVO();
        reqVO.setMetric("metric-coupon-config");
        reqVO.addTag("businessId", String.valueOf(businessId));
        reqVO.addTag("shopId", String.valueOf(shopId));
        reqVO.addTag("couponConfigId", String.valueOf(couponConfigId));
        reqVO.setValue(num);
        reqVO.setTimestamp(DateUtil.getNowDate().getTime());
        String putResp = TsdbClient.put("http://127.0.0.1:4242", OptionalDict.SUMMARY, reqVO);
        System.out.printf("resp:%s;req:%s\n", putResp, JSON.toJSONString(reqVO));
    }
}
