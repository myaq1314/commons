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
public enum DownsamplePolicesDict implements IKeyEnum<String> {

    NONE("none"), // 默认行为，在序列化期间不发出缺失值，并在聚合序列时执行线性插值(或其他指定的插值)。
    NaN("nan"), // 当序列中缺少所有值时，在序列化输出中发出NaN。当值丢失时跳过聚合中的级数，而不是将整个组(通过计算)转换为NaN。
    NULL("null"), // 与NaN相同的行为，只是在序列化期间它发出的是Null而不是NaN。
    ZERO("zero"), // 在缺少时间戳时替换一个0。零值将被合并到聚合结果中。

    // 占位符
    ;

    public final String key;
}
