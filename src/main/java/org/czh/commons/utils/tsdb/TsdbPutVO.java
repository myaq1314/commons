package org.czh.commons.utils.tsdb;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.parent.entity.IBaseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TsdbPutVO implements IBaseEntity {

    private static final long serialVersionUID = -5591502260017961597L;

    /**
     * 度量（metric）
     * <p>
     * 你所需要记录的一种数据项。
     * 随着时间的流失，会不断地在这个数据项中记录数据。
     * 比如你想监控某台机器的磁盘IO情况，
     * 那么可以定义metric = server01.disk.iops。
     */
    private String metric;

    /**
     * 标签（tag）
     * <p>
     * 每个度量都至少需要一个标签，最多8个（建议1～3个），标签由{tagK,tagV}构成。
     * 还是使用上面的例子，
     * 假设其实你有多个磁盘, 那么你可以这样设计：
     * server01.disk.iops{dev=vdb},
     * server01.disk.iops{dev=vdc}
     * <p>
     * 时间序列（Time Series）
     * 度量 + 一组标签 的组合即为时间序列。也可以叫**时间线**。
     */
    private Map<String, String> tags;

    /**
     * 时间戳
     * <p>
     * 分辨率（resolution）
     * 数据点会包含时间戳，这个时间戳可以支持毫米级和秒级两种分辨率。
     */
    @JsonIgnore
    private Long timestamp;

    /**
     * 值
     * Integer
     * Long
     * BigDecimal
     * <p>
     * 数据点（DataPoint）
     * 一条时间线上，是可以不断地插入新数据点，数据点就是时间戳+值。
     */
    private Number value;

    public TsdbPutVO addTag(String key, String value) {
        EmptyAssert.allNotBlank(key, value);

        if (this.tags == null) {
            this.tags = new HashMap<>();
        }

        this.tags.put(key, value);

        return this;
    }
}
