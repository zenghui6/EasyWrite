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
 * 官网轮播图(ClientSwiper)实体类
 *
 * @author makejava
 * @since 2021-02-09 19:31:51
 */
@Entity
@ApiModel("官网轮播图")
@Table(name = "client_swiper")
//在转化成json的时候，fasterxml.jackson将对象转换为json报错，发现有字段为null。需要加这个注解
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSwiper implements Serializable {
    private static final long serialVersionUID = 502626890853301177L;

    @Id
    @ApiModelProperty("主键")
    @Column(name = "id")
    private String id;

    @ApiModelProperty("轮播图图片地址")
    @Column(name = "swiper_pic")
    private String swiperPic;

    @ApiModelProperty("轮播图名称")
    @Column(name = "swiper_name")
    private String swiperName;

    @ApiModelProperty("轮播图状态")
    @Column(name = "swiper_status")
    private String swiperStatus;

    @ApiModelProperty("轮播图审核者")
    @Column(name = "swiper_reviewer")
    private String swiperReviewer;

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
