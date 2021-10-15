package org.czh.commons.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons_core.parent.enums.IDictEnum;

/**
 * @author : czh
 * description :
 * date : 2021-04-28
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum CommonStatusDict implements IDictEnum<Integer, String> {

    // 大于 0 为 正常响应，附带文本

    // 等于 0 为 成功响应，文本固定为：SUCCESS
    SUCCESS(0, "SUCCESS"), // 成功

    // 小于 0 为 异常响应，附带文本
    BAD_PARAM(-10, "BAD_PARAM"), // 非法参数
    INVALID_USER(-11, "INVALID_USER"), // 非法用户
    BUSINESS_EXCEPTION(-30, "BUSINESS_EXCEPTION"), // 业务异常
    DATA_EXCEPTION(-40, "DATA_EXCEPTION"), // 数据异常
    SYSTEM_EXCEPTION(-50, "SYSTEM_EXCEPTION"), // 系统异常
    SYSTEM_BUSY(-51, "SYSTEM_BUSY"), // 系统繁忙
    HTTP_STATUS_ERROR(-60, "HTTP_STATUS_ERROR"), // 调用http异常

    FAIL(-99, "FAIL"), // 失败
    SHOW(-100, "SHOW"), // 提醒异常

    // 扩展位
    ;

    public final Integer key;
    public final String value;

}
