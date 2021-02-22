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
 * 功能描述：
 *
 * @author Hocassian
 * @date 2019-11-28 10:35
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel("小程序投票记录Vo实体")
public class RecordVo {

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

    @ApiModelProperty("投票对象id")
    private String groupId;

    @ApiModelProperty("用户昵称")
    private String recordNickname;

    @ApiModelProperty("用户openid")
    private String recordOpenid;

    @ApiModelProperty("投票时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date recordDate;

    @ApiModelProperty("组名")
    private String groupName;
}