package org.czh.commons.utils.tsdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons_core.parent.entity.IBaseEntity;

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
public class TsdbFilterVO implements IBaseEntity {

    private static final long serialVersionUID = -8347582099974578334L;

    /**
     * 过滤器的类型，
     * 可以访问 /api/config/filters 接口查看支持的所有类型
     * 例如1：literal_or
     * 例如2：regexp
     *
     * @see org.czh.commons.utils.tsdb.enums.FilterTypeDict
     */
    private String type;

    /**
     * 指定过滤的key
     * 目前只对tagKey values有效
     * 例如：此处为标签名host
     */
    private String tagk;

    /**
     * 和相type对应，多个使用|连接
     * 例如1：web01|web02
     * 例如2：web [0-9] + .lax.mysite.com
     */
    private String filter;

    /**
     * 是否对匹配到的数据进行分组
     * 默认为false
     */
    private Boolean groupBy = false;

}