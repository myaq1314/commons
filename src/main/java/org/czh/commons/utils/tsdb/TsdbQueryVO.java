package org.czh.commons.utils.tsdb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.parent.entity.IBaseEntity;

import java.util.ArrayList;
import java.util.List;

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
public class TsdbQueryVO implements IBaseEntity {

    private static final long serialVersionUID = 5206882318958466952L;

    private long start;
    private long end;
    private List<TsdbQualityVO> queries;

    private Boolean noAnnotations = false;
    private Boolean globalAnnotations = false;
    private Boolean msResolution = false;
    private Boolean showTSUIDs = false;
    private Boolean showSummary = false;
    private Boolean showQuery = false;
    private Boolean delete = false;

    public TsdbQueryVO addQuality(TsdbQualityVO qualityVO) {
        EmptyAssert.isNotNull(qualityVO);

        if (this.queries == null) {
            this.queries = new ArrayList<>();
        }
        this.queries.add(qualityVO);

        return this;
    }
}
