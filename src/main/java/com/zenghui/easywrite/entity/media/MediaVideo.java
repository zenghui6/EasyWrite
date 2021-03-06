package com.zenghui.easywrite.entity.media;

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
 * 这个表是分发表，用来记录视频分发(MediaVideo)实体类
 *
 * @author zenghui
 * @since 2021-02-09 20:27:11
 */
@Entity
//自动插入时间字段要添加的注解
@EntityListeners(AuditingEntityListener.class)
@ApiModel("分发媒体视频")
@Table(name = "media_video")
//在转化成json的时候，fasterxml.jackson将对象转换为json报错，发现有字段为null。需要加这个注解
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaVideo implements Serializable {
    private static final long serialVersionUID = 779705838440080720L;

    @Id
    @ApiModelProperty("主键")
    @Column(name = "id")
    private String id;

    @ApiModelProperty("视频标题")
    @Column(name = "video_title")
    private String videoTitle;

    @ApiModelProperty("视频描述")
    @Column(name = "video_profile")
    private String videoProfile;

    @ApiModelProperty("视频cid")
    @Column(name = "video_cid")
    private String videoCid;

    @ApiModelProperty("视频aid")
    @Column(name = "video_aid")
    private String videoAid;

    @ApiModelProperty("视频aid")
    @Column(name = "video_bvid")
    private String videoBvid;


    @ApiModelProperty("视频url")
    @Column(name = "video_url")
    private String videoUrl;

    @ApiModelProperty("视频图片地址")
    @Column(name = "video_pic")
    private String videoPic;

    @ApiModelProperty("视频状态")
    @Column(name = "video_status")
    private String videoStatus;

    @ApiModelProperty("视频归类")
    @Column(name = "video_class")
    private String videoClass;

    @ApiModelProperty("视频标签")
    @Column(name = "video_tag")
    private String videoTag;

    @ApiModelProperty("视频审核人")
    @Column(name = "video_reviewer")
    private String videoReviewer;

    @ApiModelProperty("视频地址")
    @Column(name = "video_path")
    private String videoPath;

    @ApiModelProperty("视频作者")
    @Column(name = "video_author")
    private String videoAuthor;

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
