package org.czh.commons.utils.tsdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.parent.entity.IBaseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class TsdbQualityVO implements IBaseEntity {

    private static final long serialVersionUID = -2839546443038551846L;

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
     * 聚合方式(如avg、max、min、last、sum等)
     * <p>
     * aggregator为不同时间线间数据的聚合方式
     */
    private String aggregator;

    /**
     * 一个可选的向下采样函数，用于减少返回的数据量。
     */
    private String downsample;

    /**
     * 返回之前是否应该将数据转换为增量。
     * 如果度量是一个连续递增的计数器，并且您想查看数据点之间的变化率，那么这是有用的。
     * <p>
     * 默认为false
     */
    private Boolean rate = false;

    private Map<String, String> rateOptions;

    /**
     * 过滤器
     * <p>
     * 目前只对tag values有效
     */
    private List<TsdbFilterVO> filters;

    public TsdbQualityVO addTag(String key, String value) {
        EmptyAssert.allNotBlank(key, value);

        if (this.tags == null) {
            this.tags = new HashMap<>();
        }
        this.tags.put(key, value);

        return this;
    }

    public TsdbQualityVO addRateOption(String key, String value) {
        EmptyAssert.allNotBlank(key, value);

        if (this.rateOptions == null) {
            this.rateOptions = new HashMap<>();
        }
        this.rateOptions.put(key, value);

        return this;
    }

    public TsdbQualityVO addFilter(TsdbFilterVO filterVO) {
        EmptyAssert.isNotNull(filterVO);

        if (this.filters == null) {
            this.filters = new ArrayList<>();
        }
        this.filters.add(filterVO);

        return this;
    }
}
