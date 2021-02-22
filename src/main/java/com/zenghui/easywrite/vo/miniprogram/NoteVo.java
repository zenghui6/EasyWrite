package com.zenghui.easywrite.vo.miniprogram;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 功能描述：小程序公告栏Vo实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("小程序公告栏Vo实体")
public class NoteVo {

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

    @ApiModelProperty("公告项目名称")
    private String noteName;

    @ApiModelProperty("公告展示时间")
    private Date noteDisplayTime;

    @ApiModelProperty("公告正文")
    private String noteDetails;

    @ApiModelProperty("活动名称")
    private String activityName;
}