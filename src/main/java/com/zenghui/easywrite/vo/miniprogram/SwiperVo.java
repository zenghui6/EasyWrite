package com.zenghui.easywrite.vo.miniprogram;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 功能描述：小程序轮播图
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("小程序轮播图Vo实体")
public class SwiperVo{

    private String id;

    private String createBy;

    private String updateBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateAt;

    private Boolean isDel = Boolean.FALSE;

    @ApiModelProperty("活动id")
    private String activityId;

    @ApiModelProperty("轮播图名称")
    private String swiperName;

    @ApiModelProperty("轮播图地址")
    private String swiperPic;

    @ApiModelProperty("活动名称")
    private String activityName;
}