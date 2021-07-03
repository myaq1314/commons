package org.czh.commons.annotations.tag.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons.annotations.tag.ChildLengthTag;
import org.czh.commons.annotations.tag.ChildNotBlankTag;
import org.czh.commons.annotations.tag.ChildNotNullTag;
import org.czh.commons.annotations.tag.ChildValueTag;
import org.czh.commons.annotations.tag.IntervalTag;
import org.czh.commons.annotations.tag.LengthTag;
import org.czh.commons.annotations.tag.NotBlankTag;
import org.czh.commons.annotations.tag.NotEmptyTag;
import org.czh.commons.annotations.tag.NotNullTag;
import org.czh.commons.annotations.tag.PatternTag;
import org.czh.commons.annotations.tag.ValueTag;
import org.czh.commons.entity.IBaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-07-03
 * email 916419307@qq.com
 */
@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TagTestEO implements IBaseEntity {

    private static final long serialVersionUID = 7325196276513370959L;

    @ChildLengthTag(min = 11, max = 11) // 每一个 phone 元素，长度等于11
    @ChildNotBlankTag // 每一个 phone 元素，不能为空白字符串
    @NotEmptyTag // phoneList 属性，不能为空，空元素
    private List<String> phoneList;

    @ChildValueTag(min = 0, max = 100) // 每一个 score 元素，最大值不超过100，最小值不小于0
    @ChildNotNullTag // 每一个 score 元素，不能为空
    private List<Integer> scoreList;

    @ValueTag(min = 0, max = 1) // start 元素，要求大于等于0，小于等于1
    @IntervalTag(match = "end", min = 1, max = 100) // start 与 end 属性关联，两者需要同一个类型，两者之间差距，1 <= end - start >= 100
    private long start;

    private long end;

    @NotBlankTag // name 属性，不能为空白字符串
    @LengthTag(min = 32, max = 64) // name 属性，长度限制在 32 到 64 之间（包含32和64）
    private String name;

    @NotNullTag // height 属性，不能为空
    private BigDecimal height;

    @PatternTag(regexp = "yyyy-MM-dd HH:mm:ss") // dateText 表达式描述
    private String dateText;

}
