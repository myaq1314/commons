package org.czh.commons.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.czh.commons.validate.FlagAssert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : czh
 * description :
 * date : 2021-06-18
 * email 916419307@qq.com
 */
@Getter
@ToString
@EqualsAndHashCode
@SuppressWarnings("unused")
public final class Page<T> implements IBaseEntity {

    private static final long serialVersionUID = -4734585599213252148L;

    @JSONField
    @ApiModelProperty(value = "当前页，最小为1", example = "1")
    private final int currentPage;

    @ApiModelProperty(value = "每页显示记录数", example = "10")
    private final int pageSize;

    @Setter
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private boolean pageEnable;

    @ApiModelProperty(value = "总页数", example = "10")
    private int totalPage;

    @ApiModelProperty(value = "总数量", example = "100")
    private int totalResult;

    @ApiModelProperty(value = "当前记录起始行，最小为0", example = "0")
    private int currentResult;

    @Setter
    @ApiModelProperty(value = "数据集合")
    private List<T> list;

    public Page() {
        this(PageHelper.CURRENT_PAGE, PageHelper.PAGE_SIZE);
    }

    public Page(int currentPage, int pageSize) {
        this(currentPage, pageSize, true);
    }

    public Page(int currentPage, int pageSize, boolean pageEnable) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.pageEnable = pageEnable;
    }

    public static <T> Page<T> zeroPage(int currentPage, int pageSize) {
        Page<T> page = new Page<>(currentPage, pageSize);
        page.setTotalResult(0);
        page.list = new ArrayList<>(0);
        return page;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
        if (totalResult == 0) {
            this.totalPage = 0;
            return;
        }
        FlagAssert.isTrue(this.pageSize > 0);
        this.totalPage = totalResult / this.pageSize + totalResult % this.pageSize == 0 ? 0 : 1;
        this.currentResult = this.currentPage > 0 ? (this.currentPage - 1) * this.pageSize : 0;
    }
}
