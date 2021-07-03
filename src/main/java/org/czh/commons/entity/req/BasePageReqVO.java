package org.czh.commons.entity.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlTransient;

/**
 * @author : czh
 * description :
 * date : 2021-04-30
 * email 916419307@qq.com
 */
@Data
@XmlTransient
@ToString
@EqualsAndHashCode
@ApiModel(value = "基本分页请求实体")
public class BasePageReqVO implements IBaseReqVO {

    private static final long serialVersionUID = 2526293126859769876L;

    @ApiModelProperty(value = "当前页数（必填）", required = true, example = "1")
    @NotNull(message = "当前页数 必填")
    @Min(value = 1, message = "当前页数 最小值为 1")
    protected Integer pageNo;

    @ApiModelProperty(value = "每页显示行数（必填）", required = true, example = "10")
    @NotNull(message = "每页显示行数 必填")
    @Min(value = 1, message = "每页显示行数 最小值为 1")
    protected Integer pageSize;

}
