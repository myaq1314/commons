package org.czh.commons.utils.tsdb.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.czh.commons.enums.parent.IKeyEnum;

/**
 * @author : czh
 * description :
 * date : 2021-07-01
 * email 916419307@qq.com
 */
@Getter
@AllArgsConstructor
public enum AggregatorDict implements IKeyEnum<String> {

    AVG("avg"), //数据点平均值；插值：线性插值
    COUNT("count"), //集合中原始数据点的数量；插值：0替换缺失值
    DEV("dev"), //计算标准差；插值：线性插值
    EP50R3("ep50r3"), //使用R-3方法计算估计的50%；插值：线性插值
    EP50R7("ep50r7"), //使用R-7方法计算估计的50%；插值：线性插值
    EP75R3("ep75r3"), //使用R-3方法计算估计的75%；插值：线性插值
    EP75R7("ep75r7"), //使用R-7方法计算估计的75%；插值：线性插值
    EP90R3("ep90r3"), //使用R-3方法计算估计的90%；插值：线性插值
    EP90R7("ep90r7"), //使用R-7方法计算估计的90%；插值：线性插值
    EP95R3("ep95r3"), //使用R-3方法计算估计的95%；插值：线性插值
    EP95R7("ep95r7"), //使用R-7方法计算估计的95%；插值：线性插值
    EP99R3("ep99r3"), //使用R-3方法计算估计的99%；插值：线性插值
    EP99R7("ep99r7"), //使用R-7方法计算估计的99%；插值：线性插值
    EP999R3("ep999r3"), //使用R-3方法计算估计的999%；插值：线性插值
    EP999R7("ep999r7"), //使用R-7方法计算估计的999%；插值：线性插值
    FIRST("first"), //返回集合中的第一个数据点。仅仅对降采样有用，对聚合无用；插值：不定
    LAST("last"), //返回集合中的最后一个数据点。仅仅对降采样有用，对聚合无用；插值：不定
    MIMMIN("mimmin"), //筛选最小的数据点；插值：线性插值
    MIMMAX("mimmax"), //筛选最大的数据点；插值：线性插值
    MIN("min"), //筛选最小的数据点；插值：线性插值
    MAX("max"), //筛选最大的数据点；插值：线性插值
    NONE("none"), //通过所有时间序列的聚合跳过组；插值：0替换缺失值
    P50("p50"), //计算50%；插值：线性插值
    P75("p75"), //计算75%；插值：线性插值
    P90("p90"), //计算90%；插值：线性插值
    P95("p95"), //计算95%；插值：线性插值
    P99("p99"), //计算99%；插值：线性插值
    P999("p999"), //计算999%；插值：线性插值
    SUM("sum"), //将数据点一起求和；插值：线性插值
    ZIMSUM("zimsum"), //将数据点一起求和；插值：0替换缺失值

    MULT("mult"), //新增聚合函数，待确认
    PFSUM("pfsum"), //新增聚合函数，待确认
    DIFF("diff"), //新增聚合函数，待确认
    MEDIAN("median"), //新增聚合函数，待确认
    SQUARESUM("squareSum"), //新增聚合函数，待确认

    // 占位符
    ;

    public final String key;

}
