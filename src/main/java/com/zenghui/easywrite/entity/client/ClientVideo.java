package com.zenghui.easywrite.entity.client;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 这张表是官网放视频的 视频数据表(ClientVideo)实体类
 *
 * @author makejava
 * @since 2021-02-09 14:12:32
 */
@Entity
//自动插入时间字段要添加的注解
@EntityListeners(AuditingEntityListener.class)
@ApiModel("官网视频")
@Table(name = "client_video")
//在转化成json的时候，fasterxml.jackson将对象转换为json报错，发现有字段为null。需要加这个注解
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientVideo implements Serializable {
    private static final long serialVersionUID = -21495759508811676L;

    @Id
    @ApiModelProperty("主键")
    @Column(name = "id")
    private String id;

    @ApiModelProperty("视频标题")
    @Column(name = "video_title")
    private String videoTitle;

    @ApiModelProperty("视频介绍")
    @Column(name = "video_profile")
    private String videoProfile;

    @ApiModelProperty("视频url")
    @Column(name = "video_url")
    private String videoUrl;

    @ApiModelProperty("视频cid")
    @Column(name = "video_cid")
    private String videoCid;

    @ApiModelProperty("视频图片")
    @Column(name = "video_pic")
    private String videoPic;

    @ApiModelProperty("视频日期")
    @Column(name = "video_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date videoDate;

    @ApiModelProperty("视频状态")
    @Column(name = "video_status")
    private String videoStatus;

    @ApiModelProperty("视频作者")
    @Column(name = "video_author")
    private String videoAuthor;

    @ApiModelProperty("视频审核人")
    @Column(name = "video_reviewer")
    private String videoReviewer;

    @ApiModelProperty("创建时间")
    @Column(name = "create_at")
    @CreatedDate
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
    @LastModifiedDate
    private Date updateAt;

    @ApiModelProperty("更新人员")
    @Column(name = "update_by")
    private String updateBy;


}
