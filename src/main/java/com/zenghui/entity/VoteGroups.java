package com.zenghui.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 投票对象实体(VoteGroups)实体类
 *
 * @author makejava
 * @since 2021-02-09 20:53:00
 */
@Entity
@ApiModel("投票对象实体，比如说某个投票里的某个具体的人")
@Table(name = "vote_groups")
//在转化成json的时候，fasterxml.jackson将对象转换为json报错，发现有字段为null。需要加这个注解
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteGroups implements Serializable {
    private static final long serialVersionUID = -63556680993886140L;

    @Id
    @ApiModelProperty("主键")
    @Column(name = "id")
    private String id;

    @ApiModelProperty("活动编号(逻辑外键)")
    @Column(name = "activity_id")
    private String activityId;

    @ApiModelProperty("对象组名")
    @Column(name = "group_name")
    private String groupName;


    @ApiModelProperty("对象简介")
    @Column(name = "group_profile")
    private String groupProfile;

    @ApiModelProperty("获票数")
    @Column(name = "group_votes")
    private Integer groupVotes;

    @ApiModelProperty("投票对象图片")
    @Column(name = "group_pic")
    private String groupPic;

    @ApiModelProperty("创建时间")
    @Column(name = "create_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createAt;

    @ApiModelProperty("创建人员")
    @Column(name = "create_by")
    private String createBy;

    @ApiModelProperty("是否删除")
    @Column(name = "is_del")
    private Boolean isDel;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_at")
    private Date updateAt;

    @ApiModelProperty("更新人员")
    @Column(name = "update_by")
    private String updateBy;


}
