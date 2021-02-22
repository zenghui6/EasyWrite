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
 * 功能描述：小程序投票对象Vo实体
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("小程序投票对象Vo实体")
public class GroupVo {

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

    @ApiModelProperty("组名")
    private String groupName;

    @ApiModelProperty("简介")
    private String groupProfile;

    @ApiModelProperty("获票数")
    private Integer groupVotes;

    @ApiModelProperty("缩略图")
    private String groupPic;

    @ApiModelProperty("高清图")
    private String groupPicHd;

    @ApiModelProperty("活动名称")
    private String activityName;

}