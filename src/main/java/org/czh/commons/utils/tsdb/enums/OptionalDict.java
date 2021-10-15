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
public enum OptionalDict implements IKeyEnum<String> {

    SUMMARY("summary", Boolean.FALSE), // 返回结果是否显示success, failed, timeouts等统计信息。
    DETAILS("details", Boolean.FALSE), // 会显示更详细的错误信息在errors里，包括具体是哪个数据点写入出错。建议开启此参数，这样能定位到哪些数据点写入出错。
    SYNC("sync", Boolean.FALSE), // 是否使用同步写入模式。非同步模式性能会略好于同步模式。但是非同步模式由于写入是立即返回成功的，实际数据并不一定真的写入成功。这个参数根据实际需要可以选择开启。

    // 占位符
    ;

    public final String key;
    public final Boolean defaultValue;
}
