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
 * 小程序轮播图(VoteSwipers)实体类
 *
 * @author makejava
 * @since 2021-02-09 21:55:38
 */
@Entity
@ApiModel("小程序轮播图")
@Table(name = "vote_swipers")
//在转化成json的时候，fasterxml.jackson将对象转换为json报错，发现有字段为null。需要加这个注解
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteSwipers implements Serializable {
    private static final long serialVersionUID = -29728167675069863L;

    @Id
    @ApiModelProperty("主键")
    @Column(name = "id")
    private String id;

    @ApiModelProperty("活动编号(逻辑主键)")
    @Column(name = "activity_id")
    private String activityId;

    @ApiModelProperty("轮播图存储地址")
    @Column(name = "swiper_pic")
    private String swiperPic;

    @ApiModelProperty("轮播图名称")
    @Column(name = "swiper_name")
    private String swiperName;

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
