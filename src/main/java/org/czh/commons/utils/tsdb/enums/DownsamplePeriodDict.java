package org.czh.commons.utils.tsdb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons_core.parent.enums.IKeyEnum;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum DownsamplePeriodDict implements IKeyEnum<String> {

    SECOND("s"), // 秒
    MINUTE("m"), // 分钟
    HOUR("h"), // 小时
    DAY("d"), // 天
    ALL("all"), // 总

    // 占位符
    ;

    public final String key;
}