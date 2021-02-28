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
 * (ClientArticle)实体类
 *
 * @author makejava
 * @since 2021-02-09 19:28:41
 */
@Entity
//自动插入时间字段要添加的注解
@EntityListeners(AuditingEntityListener.class)
@ApiModel("文章实体")
@Table(name = "client_article")
//在转化成json的时候，fasterxml.jackson将对象转换为json报错，发现有字段为null。需要加这个注解
@JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
//lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientArticle implements Serializable {
    private static final long serialVersionUID = 803611910474747972L;

    @Id
    @ApiModelProperty("主键")
    @Column(name = "id")
    private String id;

    @ApiModelProperty("文章内容")
    @Column(name = "article")
    private String article;

    @ApiModelProperty("文章标题")
    @Column(name = "title")
    private String title;

    @ApiModelProperty("文章作者")
    @Column(name = "author")
    private String author;

    @ApiModelProperty("文章审核人")
    @Column(name = "reviewer")
    private String reviewer;

    @ApiModelProperty("文章阅读量")
    @Column(name = "readings")
    private Integer readings;

    @ApiModelProperty("文章状态")
    @Column(name = "status")
    private String status;

    @ApiModelProperty("文章图片")
    @Column(name = "pic")
    private String pic;

    @ApiModelProperty("创建时间")
    @Column(name = "create_at")
    @CreatedDate
    @JsonFormat(locale="zh", pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
