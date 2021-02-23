package com.zenghui.easywrite.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
 * @author HUT-zenghui
 */
@ApiModel("统一返回分页Dto")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Accessors(chain = true)
public class PageResult<T> implements Serializable {

    @ApiModelProperty("页码")
    private Integer page;

    @ApiModelProperty("页大小")
    private Integer size;

    @ApiModelProperty("总数")
    private Long total;

    @ApiModelProperty("数据")
    private List<T> data;

}